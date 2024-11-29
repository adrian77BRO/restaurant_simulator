package org.example.models;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import org.example.Restaurante;
import org.example.utils.SpriteMesero;
import org.example.views.ComedorView;


public class Mesero implements Runnable {
    private static final AtomicInteger contador = new AtomicInteger(1);
    private final int id;
    private final Restaurante restaurante;
    private final ComedorView comedorView;

    public Mesero(Restaurante restaurante, ComedorView comedorView) {
        this.restaurante = restaurante;
        this.id = contador.getAndIncrement();
        this.comedorView = comedorView;
    }

    public int getId() {
        return id;
    }

    @Override
public void run() {
    while (true) {
        try {
            Comensal comensal;
            synchronized (restaurante) {
                comensal = restaurante.obtenerComensal();
                if (comensal == null) {
                    restaurante.wait();
                    continue;
                }
            }

            double xc = comensal.getX();
            double yc = comensal.getY();

            CountDownLatch latch = new CountDownLatch(1);
            SpriteMesero.moverSprite(comedorView, id, 670, 200, xc, yc,1, latch);
            latch.await();

            System.out.println("Mesero " + id + " genera la orden para el comensal " + comensal.getId());

            
            CountDownLatch latch1 = new CountDownLatch(1);
            SpriteMesero.moverSprite(comedorView, id, xc, yc, 670, 200,2, latch1);
            latch1.await();

            System.out.println("Mesero " + id + " se desplaza a la cocina para entregar la orden.");


            Orden orden = new Orden(comensal);
            restaurante.getBufferOrdenes().agregarOrden(orden);

            Orden comidaLista = restaurante.getBufferComidas().tomarComida();
            if (comidaLista.getEstado() == Orden.EstadoOrden.LISTO) {
                CountDownLatch latch2 = new CountDownLatch(1);
                SpriteMesero.moverSprite(comedorView, id, xc, yc, 670, 200,3, latch2);
                latch2.await();

                System.out.println("Mesero " + id + " recoge la comida de la orden " + comidaLista.getId());
               
                CountDownLatch latch3 = new CountDownLatch(1);
                SpriteMesero.moverSprite(comedorView, id, 670, 200, xc, yc,4, latch3);
                latch3.await();
    
                synchronized (comensal) {
                    comensal.recibirComida();
                }
            }

            Thread.sleep((long) (Math.random() * 2000));

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            break;
        }
    }
}

    @Override
    public String toString() {
        return "Mesero{id=" + id + "}";
    }
}
