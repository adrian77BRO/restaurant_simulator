package org.example.views;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class RecepcionView {
    private VBox view;

    public RecepcionView() {
        view = new VBox();
        view.setPrefSize(100, 450);
        view.setStyle("-fx-background-color: lightblue;");

        Text title = new Text("Recepción");
        title.setFill(Color.BLACK);
        title.setStyle("-fx-font-size: 24px;");

        view.getChildren().add(title);
    }

    public VBox getView() {
        return view;
    }
}

