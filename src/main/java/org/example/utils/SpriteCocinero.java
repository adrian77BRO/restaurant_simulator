package org.example.utils;
import javafx.animation.AnimationTimer;
import org.example.sprites.SpriteCaminante;
import org.example.views.CocinaView;
import javafx.application.Platform;

public class SpriteCocinero {

    public static void multiPosicion(int id, double x, double y, double a, double b, CocinaView cocinaView, int caso) {
        System.out.println("Cocinero ID: " + id);
        
        if (caso == 1) {
            cocinaView.deshabilitarSprite(id + "coci");
        } else if (caso == 2) {
            cocinaView.deshabilitarSprite(id + "ped");
        }

        SpriteCaminante spriteCaminante = new SpriteCaminante(ImagePaths.COCINERO_1, ImagePaths.COCINERO_2);
        spriteCaminante.getImageView().setLayoutX(x);
        spriteCaminante.getImageView().setLayoutY(y);

        if (caso == 1) {
            cocinaView.addSprite(id + "ped", spriteCaminante);
        } else if (caso == 2) {
            cocinaView.addSprite(id + "coci", spriteCaminante);
        }

        // Coordenadas destino
        double destinoX = a;
        double destinoY = b;

        // Animación
        new AnimationTimer() {
            private double currentX = x;
            private double currentY = y;
            private final double speed = 2;

            @Override
            public void handle(long now) {
                boolean movimientoCompleto = true;

                // Movimiento en X
                if (Math.abs(currentX - destinoX) > 1) {
                    currentX += (currentX < destinoX) ? speed : -speed;
                    spriteCaminante.getImageView().setLayoutX(currentX);
                    movimientoCompleto = false;
                }

                // Movimiento en Y
                if (Math.abs(currentY - destinoY) > 1) {
                    currentY += (currentY < destinoY) ? speed : -speed;
                    spriteCaminante.getImageView().setLayoutY(currentY);
                    movimientoCompleto = false;
                }

                // Finalizar animación si llegó al destino
                if (movimientoCompleto) {
                    stop();
                    Platform.runLater(() -> System.out.println("Movimiento completado para el cocinero " + id));
                }
            }
        }.start();
    }
}
