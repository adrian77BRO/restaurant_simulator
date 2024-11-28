package org.example;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.example.views.CocinaView;
import org.example.views.ComedorView;
import org.example.views.RecepcionView;

public class Main extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Gestión de Áreas");
        settings.setVersion("1.0");
        settings.setWidth(1000);
        settings.setHeight(600);
    }

    @Override
    protected void initGame() {
        RecepcionView recepcionView = new RecepcionView();
        ComedorView comedorView = new ComedorView();
        CocinaView cocinaView = new CocinaView();

        new Thread(() -> Simulacion.createSimulacion(recepcionView)).start(); 

        HBox root = new HBox();
        root.setPrefSize(1000, 600);

        
        root.getChildren().addAll(
                recepcionView.getView(),
                comedorView.getView(), 
                cocinaView.getView() 
        );

        FXGL.getGameScene().addUINode(root);

        Rectangle rectangulo = new Rectangle(1000, 150);
        rectangulo.setFill(Color.BLACK);
        rectangulo.setLayoutX(0);
        rectangulo.setLayoutY(450);

        FXGL.getGameScene().addUINode(rectangulo);
    }

    @Override
    protected void initUI() {

    }

    public static void main(String[] args) {
        launch(args);
    }
}
