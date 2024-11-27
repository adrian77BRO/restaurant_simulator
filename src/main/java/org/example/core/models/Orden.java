package org.example.core.models;

import java.util.concurrent.atomic.AtomicInteger;

public class Orden {
    private static final AtomicInteger contador = new AtomicInteger(1);
    private final int id;
    private EstadoOrden estado;
    private final Comensal comensal;

    public enum EstadoOrden {
        EN_PROCESO,
        LISTO
    }

    // Constructor
    public Orden(Comensal comensal) {
        this.id = contador.getAndIncrement();
        this.estado = EstadoOrden.EN_PROCESO;
        this.comensal = comensal;
    }

    public int getId() {
        return id;
    }

    public EstadoOrden getEstado() {
        return estado;
    }

    public void setEstado(EstadoOrden estado) {
        this.estado = estado;
    }

    public Comensal getComensal() {
        return comensal;
    }

    @Override
    public String toString() {
        return "Orden{id=" + id + ", estado=" + estado + '}';
    }
}
