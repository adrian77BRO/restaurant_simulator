package org.example;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.example.utils.Cola;
import org.example.utils.AccionesCliente;
import org.example.views.RecepcionView;
import org.example.views.ComedorView;
import org.example.views.CocinaView;
import org.example.components.comedor.Mesa;

import java.util.List;

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
        HBox root = new HBox();
        root.setPrefSize(1000, 600);

        ComedorView comedorView = new ComedorView();
        root.getChildren().addAll(
                new RecepcionView().getView(),
                comedorView.getView(),
                new CocinaView().getView()
        );

        FXGL.getGameScene().addUINode(root);

        Cola.iniciarCaminarEnCadena(10, 30, 450, 50, 500);
         Rectangle rectangulo = new Rectangle(1000, 150);
         rectangulo.setFill(Color.BLACK);

         rectangulo.setLayoutX(0);
         rectangulo.setLayoutY(450);

         FXGL.getGameScene().addUINode(rectangulo);
         

        Button btnEntrar = new Button("Entrar");
        btnEntrar.setLayoutX(50);
        btnEntrar.setLayoutY(550);

        btnEntrar.setOnAction(event -> {
            Cola.entrar();
            PauseTransition pausa = new PauseTransition(Duration.seconds(0.55));
            pausa.setOnFinished(e -> {
                List<Mesa> mesas = comedorView.getMesas();
                AccionesCliente.buscarLugar(mesas);
            });
            pausa.play();
        });

        FXGL.getGameScene().addUINode(btnEntrar);

         Button btnAtender = new Button("Atender");
         btnAtender.setLayoutX(150);
         btnAtender.setLayoutY(550);

         btnAtender.setOnAction(event -> {
             System.out.println("Nuevo proceso iniciado, reiniciando cola...");
         });
 
         FXGL.getGameScene().addUINode(btnAtender);

         Button btnPedido = new Button("Pedido");
         btnPedido.setLayoutX(250);
         btnPedido.setLayoutY(550);

         btnPedido.setOnAction(event -> {
             System.out.println("Nuevo proceso iniciado, reiniciando cola...");
         });
 
         FXGL.getGameScene().addUINode(btnPedido);

        
    }

    @Override
    protected void initUI() {
        // más cosas por agregar si es necesario
    }

    public static void main(String[] args) {
        launch(args);
    }
}
