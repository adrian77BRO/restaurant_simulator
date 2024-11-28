package org.example.models;

import java.util.concurrent.atomic.AtomicInteger;

import org.example.Restaurante;
import org.example.components.comedor.Mesa;
import org.example.utils.SpriteComensal;
import org.example.views.ComedorView;
import org.example.views.RecepcionView;

public class Comensal implements Runnable {
    private static final AtomicInteger contador = new AtomicInteger(1);
    private final int id;
    private final Restaurante restaurante;
    private Mesa mesaAsignada;
    private boolean comidaEntregada = false;
    private final ComedorView comedorView;
    private final RecepcionView recepcionView;
    private static final Object lock = new Object(); 

    public Comensal(Restaurante restaurante, RecepcionView recepcionView, ComedorView comedorView) {
        this.restaurante = restaurante;
        this.id = contador.getAndIncrement();
        this.comedorView = comedorView;
        this.recepcionView = recepcionView;
    }

    public int getId() {
        return id;
    }

    public synchronized void recibirComida() {
        comidaEntregada = true;
        notify();
    }

    @Override
    public void run() {
        try {
            System.out.println("Comensal " + id + " ha llegado al restaurante.");
            SpriteComensal.moverSprite(recepcionView, id);

            synchronized (lock) {
                lock.wait();
            }

            synchronized (restaurante) {
                restaurante.agregarComensalEnEspera(this);
                while (!restaurante.asignarMesa()) {
                    System.out.println("Comensal " + id + " esperando mesa disponible.");
                    restaurante.wait();
                }
                
                mesaAsignada = restaurante.getMesas().stream().filter(mesa -> !mesa.isOcupada()).findFirst().orElse(null);
                
                if (mesaAsignada != null) {
                    System.out.println("Comensal " + id + " se le asigna una mesa en la posición: " + mesaAsignada.getPosicion());
                    int caso = 1;
                    double a = mesaAsignada.getX();
                    double b = mesaAsignada.getY();
                    double x = 30;
                    double y=100;
                    SpriteComensal.multiPosicion(id, x, y, a, b, recepcionView, comedorView, caso);
                    
                } else {
                    System.out.println("Comensal " + id + " no pudo ser asignado a ninguna mesa.");
                }
            }

            synchronized (this) {
                while (!comidaEntregada) {
                    wait();
                }
            }

            Thread.sleep((long) (Math.random() * 5000));
            System.out.println("Comensal " + id + " comiendo...");

            synchronized (restaurante) {
                restaurante.liberarMesa(mesaAsignada);
                // SpriteComensal.desocuparMesa(id, mesaAsignada, comedorView);
                int caso = 2;
                    double x = mesaAsignada.getX();
                    double y = mesaAsignada.getY();
                    double a = 30;
                    double b=100;
                    SpriteComensal.multiPosicion(id, x, y, a, b, recepcionView, comedorView, caso);
                restaurante.notify();
            }
            System.out.println("Comensal " + id + " ha abandonado el restaurante.");
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public String toString() {
        return "Comensal{id=" + id + "}";
    }

    public static void colaComensal() {
        synchronized (lock) {
            lock.notify();
        }
    }
}
