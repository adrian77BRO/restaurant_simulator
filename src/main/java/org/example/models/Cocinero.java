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
        double[][] posiciones = {
            {70, 50},
            {120, 50},
            {70, 350},
            {120, 200},
            {120, 100},
            {120, 350}      
        };
    
        while (true) {
            try {
                // Elegir una posición aleatoria
                int indice = (int) (Math.random() * posiciones.length);
                double[] coordenadas = posiciones[indice];

                SpriteCocinero.multiPosicion(id, coordenadas[0], coordenadas[1], 0, 200, cocinaView, 1);
                esperarMovimientoCompleto();

                // Tomar la orden
                Orden orden = restaurante.getBufferOrdenes().tomarOrden();
                System.out.println("Cocinero " + id + " recibió la orden " + orden.getId() + ".");
    
                SpriteCocinero.multiPosicion(id, 0, 200, coordenadas[0], coordenadas[1], cocinaView, 2);
                esperarMovimientoCompleto();

                System.out.println("Cocinero " + id + " está preparando la orden " + orden.getId() + ".");
                Thread.sleep((long) (Math.random() * 5000));
    
    
                orden.setEstado(Orden.EstadoOrden.LISTO);
                restaurante.getBufferComidas().agregarComida(orden);
    
                System.out.println("Cocinero " + id + " entregó la orden " + orden.getId() + ".");
    
                // Retorno a la posición inicial (ejemplo: posición fija)
                SpriteCocinero.multiPosicion(id, coordenadas[0], coordenadas[1], 0, 200, cocinaView, 1);
                esperarMovimientoCompleto();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
    
private void esperarMovimientoCompleto() {
    try {
        Thread.sleep(500);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}

    @Override
    public String toString() {
        return "Cocinero{id=" + id + "}";
    }

}
