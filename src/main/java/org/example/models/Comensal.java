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

    public double getX() {
        return mesaAsignada != null ? mesaAsignada.getX() : 0;
    }

    public double getY() {
        return mesaAsignada != null ? mesaAsignada.getY() : 0;
    }

    public synchronized void recibirComida() {
        comidaEntregada = true;
        notify();
    }

    @Override
    public void run() {
        try {
            System.out.println("Comensal " + id + " ha llegado al restaurante.");
            CountDownLatch latch = new CountDownLatch(1);

            synchronized (restaurante) {
                restaurante.agregarComensalEnEspera(this);

                while ((mesaAsignada = restaurante.asignarMesa()) == null) {
                    System.out.println("Comensal " + id + " esperando mesa disponible.");
                    restaurante.wait();
                }
            }

            System.out.println("Comensal " + id + " se le asigna una mesa en la posición: " + mesaAsignada.getPosicion());

            
            SpriteComensal.moverSprite(recepcionView, id, latch);
            latch.await();
           
            SpriteComensal.multiPosicion(id, 0, 100, mesaAsignada.getX(), mesaAsignada.getY(), recepcionView, comedorView, 1, latch);

            
            synchronized (this) {
                while (!comidaEntregada) {
                    wait();
                }
            }

            Thread.sleep(5000); 
            System.out.println("Comensal " + id + " está comiendo...");

            CountDownLatch latch1 = new CountDownLatch(1);
            synchronized (restaurante) {
                SpriteComensal.multiPosicion(id, mesaAsignada.getX(), mesaAsignada.getY(), 0, 100, recepcionView, comedorView, 2, latch1);
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
