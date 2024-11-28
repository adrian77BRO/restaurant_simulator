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

    public synchronized boolean asignarMesa() {
        if(mesasDisponibles>0){
            Collections.shuffle(mesas);
            for (Mesa mesa : mesas) {
                if (!mesa.isOcupada()) {
                    mesasDisponibles--;
                    mesa.setOcupada(true);
                    return true;
                }
            }
        }
         
        return false;
    }

    public synchronized void liberarMesa(Mesa mesa) {
        mesa.setOcupada(false);
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

    public List<Mesa> getMesas() {
        return mesas;
    }

    public BufferOrdenes getBufferOrdenes() {
        return bufferOrdenes;
    }

    public BufferComidas getBufferComidas() {
        return bufferComidas;
    }

    
    public Mesa getMesaAsignada() {
        for (Mesa mesa : mesas) {
            if (mesa.isOcupada()) {
                return mesa;
            }
        }
        return null;
    }
}
