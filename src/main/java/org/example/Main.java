package org.example;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.layout.HBox;
import org.example.views.CocinaView;
import org.example.views.ComedorView;
import org.example.views.RecepcionView;

public class Main extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Gestión de Áreas");
        settings.setVersion("1.0");
        settings.setWidth(1000);
        settings.setHeight(450);
    }

    @Override
    protected void initGame() {
        RecepcionView recepcionView = new RecepcionView();
        ComedorView comedorView = new ComedorView();
        CocinaView cocinaView = new CocinaView();
        new Thread(() -> Simulacion.createSimulacion(recepcionView, comedorView, cocinaView)).start();

        HBox root = new HBox();
        root.setPrefSize(1000, 600);

        root.getChildren().addAll(
                recepcionView.getView(),
                comedorView.getView(),
                cocinaView.getView()
        );

        FXGL.getGameScene().addUINode(root);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
