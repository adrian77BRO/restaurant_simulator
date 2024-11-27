package org.example.utils;

import com.almasb.fxgl.dsl.FXGL;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.example.sprites.SpriteCaminante;

import java.util.LinkedList;
import java.util.Queue;

public class Cola {

    private static final double VELOCIDAD = 2; // Velocidad del movimiento
    private static Queue<SpriteCaminante> colaSprites = new LinkedList<>(); // Cola para almacenar los sprites

    public static void moverSprite(ImageView sprite, double destinoX, double destinoY) {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                double posX = sprite.getX();
                double posY = sprite.getY();

                double deltaX = destinoX - posX;
                double deltaY = destinoY - posY;

                if (Math.abs(deltaX) < VELOCIDAD && Math.abs(deltaY) < VELOCIDAD) {
                    sprite.setX(destinoX);
                    sprite.setY(destinoY);
                    stop();
                    return;
                }
                double magnitud = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
                double direccionX = deltaX / magnitud;
                double direccionY = deltaY / magnitud;

                sprite.setX(posX + direccionX * VELOCIDAD);
                sprite.setY(posY + direccionY * VELOCIDAD);
            }
        }.start();
    }

    public static void iniciarCaminarEnCadena(
            int cantidadSprites,
            double posicionInicialX,
            double posicionInicialY,
            double separacion,
            int delayEntreSprites) {

        Timeline timeline = new Timeline();

        for (int i = 0; i < cantidadSprites; i++) {
            int indice = i;
            timeline.getKeyFrames().add(
                new KeyFrame(
                    Duration.millis(i * delayEntreSprites),
                    e -> {
                        SpriteCaminante sprite = new SpriteCaminante();
                        sprite.iniciarAnimacion(); // Activar animación si es necesario

                        sprite.getImageView().setX(posicionInicialX);
                        sprite.getImageView().setY(posicionInicialY);

                        FXGL.getGameScene().addUINode(sprite.getImageView());

                        double destinoY = posicionInicialY - ((9 - indice) * separacion) + 100;

                        moverSprite(sprite.getImageView(), posicionInicialX, destinoY);

                        colaSprites.add(sprite);
                    }
                )
            );
        }

        timeline.play(); 
    }

    public static void entrar() {
        if (!colaSprites.isEmpty()) {
            SpriteCaminante sprite = colaSprites.poll();
            ImageView imageView = sprite.getImageView();

            moverSprite(imageView, 100, imageView.getY());

            new AnimationTimer() {
                @Override
                public void handle(long now) {
                    if (imageView.getX() == 100) {
                        FXGL.getGameScene().removeUINode(imageView);
                        stop();
                        reordenarCola(); 
                    }
                }
            }.start();
        }
    }

    private static void reordenarCola() {
        int posicionY = 100; 
        for (SpriteCaminante sprite : colaSprites) {
            double nuevoDestinoY = posicionY;
            posicionY += 50; 
            moverSprite(sprite.getImageView(), sprite.getImageView().getX(), nuevoDestinoY);
        }
    }
}
