package org.example.core;

import org.example.core.models.Cocinero;
import org.example.core.models.Comensal;
import org.example.core.models.Mesero;

public class Main {
    public static void main(String[] args) {
        Restaurante restaurante = new Restaurante();

        final int numMeseros = 4;
        final int numCocineros = 6;

        for (int i = 0; i < numMeseros; i++) {
            new Thread(new Mesero(restaurante)).start();
        }

        for (int i = 0; i < numCocineros; i++) {
            new Thread(new Cocinero(restaurante)).start();
        }

        while (true) {
            new Thread(new Comensal(restaurante)).start();
            try {
                Thread.sleep((long) (Math.random() * 3000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
