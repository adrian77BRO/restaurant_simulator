package org.example.core.models;

import org.example.core.Restaurante;

import java.util.concurrent.atomic.AtomicInteger;

public class Cocinero implements Runnable {
    private static final AtomicInteger contador = new AtomicInteger(1);
    private final int id;
    private final Restaurante restaurante;

    public Cocinero(Restaurante restaurante) {
        this.restaurante = restaurante;
        this.id = contador.getAndIncrement();
    }

    public int getId() {
        return id;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Orden orden = restaurante.getBufferOrdenes().tomarOrden();
                System.out.println("Cocinero " + id + " está preparando la orden " + orden.getId() + ".");
                Thread.sleep((long) (Math.random() * 5000));

                orden.setEstado(Orden.EstadoOrden.LISTO);
                System.out.println("Cocinero " + id + " terminó la orden " + orden.getId() + ".");
                restaurante.getBufferComidas().agregarComida(orden);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public String toString() {
        return "Cocinero{id=" + id + "}";
    }
}
