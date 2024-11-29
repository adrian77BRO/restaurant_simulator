package org.example.models;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import org.example.utils.SpriteCocinero;
import org.example.views.CocinaView;

import org.example.Restaurante;

public class Cocinero implements Runnable {
    private static final AtomicInteger contador = new AtomicInteger(1);
    private final int id;
    private final Restaurante restaurante;
    private final CocinaView cocinaView;

    public Cocinero(Restaurante restaurante, CocinaView cocinaView) {
        this.restaurante = restaurante;
        this.id = contador.getAndIncrement();
        this.cocinaView = cocinaView;
    }

    public int getId() {
        return id;
    }

    @Override
    public void run() {
        double[][] posiciones = {
            {70, 50},
            {120, 50},
            {70, 350},
            {120, 200},
            {120, 100},
            {120, 350}      
        };
    
        while (true) {
            try {
                int indice = (int) (Math.random() * posiciones.length);
                double[] coordenadas = posiciones[indice];
                CountDownLatch latch = new CountDownLatch(1);

                SpriteCocinero.multiPosicion(id, coordenadas[0], coordenadas[1], 0, 200, cocinaView, 1, latch);
                latch.await();
                 

                Orden orden = restaurante.getBufferOrdenes().tomarOrden();
                System.out.println("Cocinero " + id + " recibió la orden " + orden.getId() + ".");

                CountDownLatch latch1 = new CountDownLatch(1);
                SpriteCocinero.multiPosicion(id, 0, 200, coordenadas[0], coordenadas[1], cocinaView, 2, latch1);
                latch1.await();

                System.out.println("Cocinero " + id + " está preparando la orden " + orden.getId() + ".");
                Thread.sleep((long) (Math.random() * 2000));
    
    
                orden.setEstado(Orden.EstadoOrden.LISTO);
                restaurante.getBufferComidas().agregarComida(orden);
    
                System.out.println("Cocinero " + id + " entregó la orden " + orden.getId() + ".");
    
                CountDownLatch latch2 = new CountDownLatch(1);
                SpriteCocinero.multiPosicion(id, coordenadas[0], coordenadas[1], 0, 200, cocinaView, 3, latch2);
                latch2.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
    

    @Override
    public String toString() {
        return "Cocinero{id=" + id + "}";
    }

}
