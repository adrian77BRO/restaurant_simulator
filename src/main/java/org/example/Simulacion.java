package org.example;

import org.example.models.Cocinero;
import org.example.models.Comensal;
import org.example.models.Mesero;
import org.example.utils.SpriteComensal;
import org.example.views.RecepcionView;

public class Simulacion {

    public static void createSimulacion(RecepcionView recepcionView) {
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
            crearComensal(restaurante, recepcionView);
            try {
                Thread.sleep((long) (Math.random() * 3000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static void crearComensal(Restaurante restaurante, RecepcionView recepcionView) {
        Comensal comensal = new Comensal(restaurante);
        new Thread(comensal).start();
        SpriteComensal.moverSprite(recepcionView, comensal);
    }
}
