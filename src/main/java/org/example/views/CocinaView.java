package org.example.views;

import org.example.components.cocina.Mosaico;
import org.example.components.cocina.Isla;
import javafx.scene.layout.StackPane;

public class CocinaView {
    private StackPane view;

    public CocinaView() {
        view = new StackPane();
        view.setPrefSize(200, 450);
        view.setStyle("-fx-background-color: white;");

        // Crear el fondo de mosaicos
        Mosaico mosaico = new Mosaico(18, 8, 25);

        // Crear la isla
        Isla isla = new Isla();

        // Agregar mosaicos y la isla al StackPane
        view.getChildren().addAll(mosaico.getMosaico(), isla.getIsla());
    }

    public StackPane getView() {
        return view;
    }
}
