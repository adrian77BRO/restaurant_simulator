package org.example;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import org.example.core.Restaurante;

public class Main extends GameApplication {
    private Restaurante restaurante;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Simulador de Restaurante");
        settings.setWidth(800);
        settings.setHeight(600);
    }

    @Override
    protected void initGame() {
        restaurante = new Restaurante();
        // Agregar gráficos de mesas, meseros y cocineros aquí
    }

    @Override
    protected void onUpdate(double tpf) {
        // Actualizar el estado de la simulación en la interfaz
    }

    public static void main(String[] args) {
        launch(args);
    }
}

