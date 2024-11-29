package org.example.monitors;

import org.example.models.Orden;

import java.util.LinkedList;
import java.util.Queue;

public class BufferOrdenes {
    private final Queue<Orden> ordenes = new LinkedList<>();

    public synchronized void agregarOrden(Orden orden) {
        ordenes.add(orden);
        notifyAll();
    }

    public synchronized Orden tomarOrden() {
        while (ordenes.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return ordenes.poll();
    }
}
