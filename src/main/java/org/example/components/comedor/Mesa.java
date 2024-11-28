package org.example.components.comedor;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Mesa {
    private StackPane mesa;
    private boolean ocupada;

    public Mesa() {
        mesa = new StackPane();
        ocupada = false;

        Image mesaImage = new Image(getClass().getResource("/org/example/assets/mesa.png").toExternalForm());
        ImageView imageView = new ImageView(mesaImage);
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);
        mesa.getChildren().add(imageView);
    }

    public StackPane getMesa() {
        return mesa;
    }

    public double getX() {
        return mesa.getLayoutX();
    }

    public double getY() {
        return mesa.getLayoutY();
    }

    public void setX(double x) {
        mesa.setLayoutX(x);
    }

    public void setY(double y) {
        mesa.setLayoutY(y);
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public String getPosicion() {
        return "(" + getX() + ", " + getY() + ")";
    }
}
