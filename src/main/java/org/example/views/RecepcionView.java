package org.example.views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RecepcionView {
    private Pane view;

    public RecepcionView() {
        view = new Pane();
        view.setPrefSize(100, 450);
        view.setStyle("-fx-background-color: red;");

        Rectangle rectangulo1 = new Rectangle(10, 60); 
        rectangulo1.setFill(Color.WHITE);
        rectangulo1.setLayoutX(90);
        rectangulo1.setLayoutY(90);

        Rectangle rectangulo2 = new Rectangle(10, 450); 
        rectangulo2.setFill(Color.BLACK);
        rectangulo2.setLayoutX(95);
        rectangulo2.setLayoutY(0); 

        view.getChildren().addAll(rectangulo1, rectangulo2);

        Image image = new Image(getClass().getResource("/org/example/assets/rec.png").toExternalForm());

        ImageView imageView = new ImageView(image);
        imageView.setLayoutX(20);
        imageView.setLayoutY(20);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50); 

        view.getChildren().add(imageView);
    }

    public Pane getView() {
        return view;
    }
}
