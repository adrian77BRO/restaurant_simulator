package org.example.core.models;

import org.example.core.Restaurante;

import java.util.concurrent.atomic.AtomicInteger;

public class Mesero implements Runnable {
    private static final AtomicInteger contador = new AtomicInteger(1);
    private final int id;
    private final Restaurante restaurante;

    public Mesero(Restaurante restaurante) {
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
                Comensal comensal;
                synchronized (restaurante) {
                    comensal = restaurante.obtenerComensal();
                    if (comensal == null) {
                        restaurante.wait();
                        continue;
                    }
                }

                Orden orden = new Orden(comensal);
                System.out.println("Mesero " + id + " genera la orden " + orden.getId() + " para el comensal " + comensal.getId() + ".");
                restaurante.getBufferOrdenes().agregarOrden(orden);

                Orden comidaLista = restaurante.getBufferComidas().tomarComida();
                if (comidaLista.getEstado() == Orden.EstadoOrden.LISTO) {
                    System.out.println("Mesero " + id + " entrega la comida de la orden " + comidaLista.getId() + " al comensal " + comensal.getId() + ".");
                }

                // Simular tiempo de entrega
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

