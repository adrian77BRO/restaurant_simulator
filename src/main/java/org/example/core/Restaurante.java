package org.example.core;

import org.example.core.models.Comensal;
import org.example.core.monitors.BufferComidas;
import org.example.core.monitors.BufferOrdenes;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Restaurante {
    private final int capacidadMesas = 40;
    private int mesasDisponibles = capacidadMesas;
    private final BufferOrdenes bufferOrdenes = new BufferOrdenes();
    private final BufferComidas bufferComidas = new BufferComidas();
    private final Queue<Comensal> comensalesEnEspera = new ConcurrentLinkedQueue<>();

    public synchronized boolean asignarMesa() {
        if (mesasDisponibles > 0) {
            mesasDisponibles--;
            return true;
        }
        return false;
    }

    public synchronized void liberarMesa() {
        mesasDisponibles++;
        notifyAll();
    }

    public synchronized Comensal obtenerComensal() {
        return comensalesEnEspera.poll();
    }

    public void agregarComensalEnEspera(Comensal comensal) {
        comensalesEnEspera.add(comensal);
        synchronized (this) {
            notifyAll();
        }
    }

    public BufferOrdenes getBufferOrdenes() {
        return bufferOrdenes;
    }

    public BufferComidas getBufferComidas() {
        return bufferComidas;
    }
}
