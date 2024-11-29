package org.example.utils;

import javafx.animation.AnimationTimer;
import java.util.concurrent.CountDownLatch;
import org.example.sprites.SpriteCaminante;
import org.example.views.CocinaView;

public class SpriteCocinero {

    public static void multiPosicion(int id, double x, double y, double a, double b, CocinaView cocinaView, int caso, CountDownLatch latch) {
        if (caso == 1) {
            cocinaView.removeSpriteById(id + "coci2");
            cocinaView.removeSpriteById(id + "coci3");
        } 
        if (caso == 2) {
            cocinaView.removeSpriteById(id + "coci2");
        }
        if(caso==3){
            cocinaView.removeSpriteById(id + "coci2");
            cocinaView.removeSpriteById(id + "coci1");
        }

        SpriteCaminante spriteCaminante;
        spriteCaminante = new SpriteCaminante(ImagePaths.COCINERO_1, ImagePaths.COCINERO_2);

        spriteCaminante.getImageView().setLayoutX(x);
        spriteCaminante.getImageView().setLayoutY(y);

        if (caso == 1) {
            cocinaView.addSprite(id + "coci1", spriteCaminante);
        } else if (caso == 2) {
            cocinaView.addSprite(id + "coci2", spriteCaminante);
        } else if(caso == 3){
            cocinaView.addSprite(id + "coci3", spriteCaminante);
        }

        double destinoX = a;
        double destinoY = b;

        new AnimationTimer() {
            private double currentX = x;
            private double currentY = y;
            private final double speed = 2;

            @Override
            public void handle(long now) {
                boolean movimientoCompleto = true;

                if (Math.abs(currentX - destinoX) > 1) {
                    currentX += (currentX < destinoX) ? speed : -speed;
                    spriteCaminante.getImageView().setLayoutX(currentX);
                    movimientoCompleto = false;
                }

                if (Math.abs(currentY - destinoY) > 1) {
                    currentY += (currentY < destinoY) ? speed : -speed;
                    spriteCaminante.getImageView().setLayoutY(currentY);
                    movimientoCompleto = false;
                }

                if (movimientoCompleto) {
                    stop();

                    latch.countDown();
                }
            }
        }.start();
    }
}
