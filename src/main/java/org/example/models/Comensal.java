package org.example.models;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.example.Restaurante;
import org.example.components.comedor.Mesa;
import org.example.utils.SpriteComensal;
import org.example.views.ComedorView;
import org.example.views.RecepcionView;

public class Comensal implements Runnable {
    private static final AtomicInteger contador = new AtomicInteger(1);
    private final int id;
    private final Restaurante restaurante;
    private Mesa mesaAsignada;
    private boolean comidaEntregada = false;
    private final ComedorView comedorView;
    private final RecepcionView recepcionView;

    public Comensal(Restaurante restaurante, RecepcionView recepcionView, ComedorView comedorView) {
        this.restaurante = restaurante;
        this.id = contador.getAndIncrement();
        this.comedorView = comedorView;
        this.recepcionView = recepcionView;
    }

    public int getId() {
        return id;
    }

    public synchronized void recibirComida() {
        comidaEntregada = true;
        notify();
    }

    @Override
    public void run() {
        try {
            System.out.println("Comensal" + id + " ha llegado al restaurante.");
             CountDownLatch latch = new CountDownLatch(1);            
            
            synchronized (restaurante) {
                restaurante.agregarComensalEnEspera(this);
                while (!restaurante.asignarMesa()) {
                    System.out.println("Comensal  " + id + " esperando mesa disponible.");
                    restaurante.wait();
                }
                SpriteComensal.moverSprite(recepcionView, id, latch);
                latch.await();
                

                mesaAsignada = restaurante.getMesas().stream().filter(mesa -> !mesa.isOcupada()).findFirst().orElse(null);
                
                if (mesaAsignada != null) {
                    System.out.println("Comensa " + id + " se le asigna una mesa en la posición: " + mesaAsignada.getPosicion());
                    double a = mesaAsignada.getX();
                    double b = mesaAsignada.getY();
                    SpriteComensal.multiPosicion(id, 0, 100, a, b, recepcionView, comedorView, 1, latch);
                    
                } else {
                    System.out.println("Comen sal " + id + " no pudo ser asignado a ninguna mesa.");
                }
            }

            synchronized (this) {
                while (!comidaEntregada) {
                    wait();
                }
            }
            

            Thread.sleep(50);
            System.out.println("Comensal " + id + " comiendo...");
            CountDownLatch latch1 = new CountDownLatch(1);  

            synchronized (restaurante) {
                double x = mesaAsignada.getX();
                double y = mesaAsignada.getY();
                SpriteComensal.multiPosicion(id, x, y, 0, 100, recepcionView, comedorView, 2, latch1);
                latch1.await();
                restaurante.liberarMesa(mesaAsignada);
            }

            System.out.println("Comensal " + id + " ha abandonado el restaurante.");
            SpriteComensal.multiPosicion(id, 40, 100, 40, 450, recepcionView, comedorView, 3, latch1);
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public String toString() {
        return "Comensal{id=" + id + "}";
    }
}
