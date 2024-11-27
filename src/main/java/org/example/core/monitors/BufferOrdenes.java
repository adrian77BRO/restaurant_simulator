package org.example.core.monitors;

import org.example.core.models.Orden;

import java.util.LinkedList;
import java.util.Queue;

public class BufferOrdenes {
    private final Queue<Orden> ordenes = new LinkedList<>();

    public synchronized void agregarOrden(Orden orden) {
        ordenes.add(orden);
        notify();
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
