package org.example;

import org.example.models.Comensal;
import org.example.monitors.BufferComidas;
import org.example.monitors.BufferOrdenes;
import org.example.components.comedor.Mesa;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Collections;
import java.util.List;

public class Restaurante {
    private final int capacidadMesas = 40;
    private int mesasDisponibles = capacidadMesas;
    private final BufferOrdenes bufferOrdenes = new BufferOrdenes();
    private final BufferComidas bufferComidas = new BufferComidas();
    private final Queue<Comensal> comensalesEnEspera = new ConcurrentLinkedQueue<>();
    private final List<Mesa> mesas;

    public Restaurante(List<Mesa> mesas) {
        this.mesas = mesas;
    }

    public synchronized Mesa asignarMesa() {
        while (mesasDisponibles <= 0) {
            try {
                wait(); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        Collections.shuffle(mesas);
        for (Mesa mesa : mesas) {
            if (!mesa.isOcupada()) {
                mesasDisponibles--;
                mesa.setOcupada(true);
                notifyAll();
                return mesa;
            }
        }
        return null;
    }

    public synchronized void liberarMesa(Mesa mesa) {
        if (mesa != null && mesa.isOcupada()) {
            mesa.setOcupada(false);
            mesasDisponibles++;
            notifyAll();
        }
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
