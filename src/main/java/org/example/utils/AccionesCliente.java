package org.example.utils;

import com.almasb.fxgl.dsl.FXGL;
import javafx.animation.AnimationTimer;
import org.example.components.comedor.Mesa;
import org.example.sprites.SpriteCaminante;
import javafx.scene.image.ImageView;
import java.util.List;
import java.util.Random;

public class AccionesCliente {

    public static void buscarLugar(List<Mesa> mesas) {
        SpriteCaminante cliente = new SpriteCaminante(null, null);
        cliente.iniciarAnimacion();

        ImageView clienteView = cliente.getImageView();
        clienteView.setLayoutX(100);
        clienteView.setLayoutY(100);
        
        FXGL.getGameScene().addUINode(clienteView);

        Random rand = new Random();
        Mesa mesaElegida = mesas.get(rand.nextInt(mesas.size()));
        double destinoX = mesaElegida.getMesa().getLayoutX()+100;
        double destinoY = mesaElegida.getMesa().getLayoutY();
        movimientoLugar(clienteView, destinoX, destinoY);
    }

    private static void movimientoLugar(ImageView clienteView, double destinoX, double destinoY) {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                double posX = clienteView.getLayoutX();
                double posY = clienteView.getLayoutY();

                double deltaX = destinoX - posX;
                double deltaY = destinoY - posY;

                if (Math.abs(deltaX) < 1 && Math.abs(deltaY) < 1) {
                    clienteView.setLayoutX(destinoX);
                    clienteView.setLayoutY(destinoY);
                    stop();
                } else {
                    double magnitud = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
                    double direccionX = deltaX / magnitud;
                    double direccionY = deltaY / magnitud;

                    clienteView.setLayoutX(posX + direccionX * 2); 
                    clienteView.setLayoutY(posY + direccionY * 2);
                }
            }
        }.start();
    }
}
