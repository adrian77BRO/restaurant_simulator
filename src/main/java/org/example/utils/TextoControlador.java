package org.example.utils;

import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;
import com.almasb.fxgl.dsl.FXGL;

public class TextoControlador {

    private Rectangle rectangulo;
    private Text texto;

    public TextoControlador(double width, double height, double layoutX, double layoutY) {
        rectangulo = new Rectangle(width, height);
        rectangulo.setFill(javafx.scene.paint.Color.BLACK);
        rectangulo.setLayoutX(layoutX);
        rectangulo.setLayoutY(layoutY);

        texto = new Text("Texto Inicial");
        texto.setFill(javafx.scene.paint.Color.WHITE);
        texto.setLayoutX(layoutX + 20);
        texto.setLayoutY(layoutY + height / 2);


        FXGL.getGameScene().addUINode(rectangulo);
        FXGL.getGameScene().addUINode(texto);
    }
    public void actualizarTexto(String nuevoTexto) {
        texto.setText(nuevoTexto);
    }

    public Rectangle getRectangulo() {
        return rectangulo;
    }

    public Text getTexto() {
        return texto;
    }
}
