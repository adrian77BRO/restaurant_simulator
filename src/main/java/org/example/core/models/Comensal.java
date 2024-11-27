package org.example.core.models;

import org.example.core.Restaurante;

import java.util.concurrent.atomic.AtomicInteger;

public class Comensal implements Runnable {
    private static final AtomicInteger contador = new AtomicInteger(1);
    private final int id;
    private final Restaurante restaurante;
    private boolean comidaEntregada = false;

    public Comensal(Restaurante restaurante) {
        this.restaurante = restaurante;
        this.id = contador.getAndIncrement();
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
            System.out.println("Comensal " + id + " ha llegado al restaurante.");

            // Esperar mesa disponible
            synchronized (restaurante) {
                restaurante.agregarComensalEnEspera(this);
                while (!restaurante.asignarMesa()) {
                    System.out.println("Comensal " + id + " esperando mesa disponible.");
                    restaurante.wait();
                }
                System.out.println("Comensal " + id + " se le asigna una mesa.");
            }

            synchronized (this) {
                while (!comidaEntregada) {
                    wait();
                }
            }

            // Simular tiempo de estancia en el restaurante
            Thread.sleep((long) (Math.random() * 5000)); // Tiempo en el restaurante
            System.out.println("Comensal " + id + " comiendo...");

            synchronized (restaurante) {
                restaurante.liberarMesa();
                restaurante.notify();
            }
            System.out.println("Comensal " + id + " ha abandonado el restaurante.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public String toString() {
        return "Comensal{id=" + id + "}";
    }
}
