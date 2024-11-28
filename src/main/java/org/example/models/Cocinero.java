package org.example.models;

import java.util.concurrent.atomic.AtomicInteger;
import org.example.utils.SpriteCocinero;
import org.example.views.CocinaView;

import org.example.Restaurante;

public class Cocinero implements Runnable {
    private static final AtomicInteger contador = new AtomicInteger(1);
    private final int id;
    private final Restaurante restaurante;
    private final CocinaView cocinaView;

    public Cocinero(Restaurante restaurante, CocinaView cocinaView) {
        this.restaurante = restaurante;
        this.id = contador.getAndIncrement();
        this.cocinaView = cocinaView;
    }

    public int getId() {
        return id;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Orden orden = restaurante.getBufferOrdenes().tomarOrden();
                SpriteCocinero.multiPosicion(id, 0, 0, 0, 0, cocinaView, 1);

                System.out.println("Cocinero " + id + " está preparando la orden " + orden.getId() + ".");
                Thread.sleep((long) (Math.random() * 5000));

                orden.setEstado(Orden.EstadoOrden.LISTO);
                System.out.println("Cocinero " + id + " terminó la orden " + orden.getId() + ".");
                SpriteCocinero.multiPosicion(id, 0, 100, 100, 70, cocinaView, 2);
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

    // public double coordenadas(int id){
    //     if(id==1){

    //     }
    // }
}
