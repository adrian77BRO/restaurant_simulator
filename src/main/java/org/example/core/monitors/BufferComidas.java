package org.example.core.monitors;

import org.example.core.models.Orden;

import java.util.LinkedList;
import java.util.Queue;

public class BufferComidas {
    private final Queue<Orden> comidas = new LinkedList<>();

    public synchronized void agregarComida(Orden comida) {
        comidas.add(comida);
        notifyAll();
    }

    public synchronized Orden tomarComida() {
        while (comidas.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return comidas.poll();
    }
}