package org.example;

import org.example.components.comedor.Mesa;
import org.example.models.Cocinero;
import org.example.models.Comensal;
import org.example.models.Mesero;
import org.example.views.CocinaView;
import org.example.views.ComedorView;
import org.example.views.RecepcionView;
import java.util.List;
import org.example.utils.PoissonDelay;


public class Simulacion {

    public static void createSimulacion(RecepcionView recepcionView, ComedorView comedorView, CocinaView cocinaView) {
         List<Mesa> mesas = comedorView.getMesas();
        Restaurante restaurante = new Restaurante(mesas);
        

        final int numMeseros = 4;
        final int numCocineros = 6;

        for (int i = 0; i < numMeseros; i++) {
            new Thread(new Mesero(restaurante, comedorView)).start();
        }

        for (int i = 0; i < numCocineros; i++) {
            new Thread(new Cocinero(restaurante, cocinaView)).start();
        }

        while (true) {
            Comensal comensal = new Comensal(restaurante, recepcionView, comedorView);
            new Thread(comensal).start();
            try {
                Thread.sleep(PoissonDelay.generatePoissonDelay(1.5));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
