package org.example;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.text.Text;

public class Main extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("FXGL Hola Mundo");
        settings.setVersion("1.0");
        settings.setWidth(800);
        settings.setHeight(600);
    }

    @Override
    protected void initGame() {
        // Establece un fondo negro
        FXGL.getGameScene().setBackgroundColor(javafx.scene.paint.Color.BLACK);

        // Crea texto azul
        Text holaMundo = FXGL.getUIFactoryService().newText("Hola Mundo", javafx.scene.paint.Color.BLUE, 32);
        holaMundo.setTranslateX(300); // Posición en X
        holaMundo.setTranslateY(300); // Posición en Y
        FXGL.getGameScene().addUINode(holaMundo); // Agrega el texto a la escena
    }

    public static void main(String[] args) {
        launch(args);
    }
}
