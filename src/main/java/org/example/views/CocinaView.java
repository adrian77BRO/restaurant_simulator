package org.example.views;

import org.example.components.cocina.Mosaico;
import org.example.components.cocina.Isla;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CocinaView {
    private StackPane view;

    public CocinaView() {
        view = new StackPane();
        view.setPrefSize(200, 450);
        view.setStyle("-fx-background-color: white;");
        // Crear el fondo de mosaicos
        Mosaico mosaico = new Mosaico(18, 8, 25);
        Isla isla = new Isla();
       
        view.getChildren().addAll(mosaico.getMosaico(), isla.getIsla());
    }

    public StackPane getView() {
        return view;
    }
}
