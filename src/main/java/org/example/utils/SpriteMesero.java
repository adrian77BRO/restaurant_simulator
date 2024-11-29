package org.example.utils;

import javafx.animation.AnimationTimer;

import java.util.concurrent.CountDownLatch;

import org.example.sprites.SpriteCaminante;
import org.example.views.ComedorView;


public class SpriteMesero {

    public static void moverSprite(ComedorView comedorView, int id, double x, double y, double a, double b, int caso, CountDownLatch latch) {
        // Eliminar los sprites según el caso
        if (caso == 1) {
            comedorView.removeSpriteById(id + "mes2");
            comedorView.removeSpriteById(id + "mes3");
            comedorView.removeSpriteById(id + "mes4");
        } else if (caso == 2) {
            comedorView.removeSpriteById(id + "mes1");
            comedorView.removeSpriteById(id + "mes3");
            comedorView.removeSpriteById(id + "mes4");
        } else if (caso == 3) {
            comedorView.removeSpriteById(id + "mes1");
            comedorView.removeSpriteById(id + "mes2");
            comedorView.removeSpriteById(id + "mes4");
        } else if (caso == 4) {
            comedorView.removeSpriteById(id + "mes1");
            comedorView.removeSpriteById(id + "mes2");
            comedorView.removeSpriteById(id + "mes3");
        }
  
        SpriteCaminante spriteCaminante;
        if (caso == 4) {
            spriteCaminante = new SpriteCaminante(
                ImagePaths.MESERO_3,
                ImagePaths.MESERO_4
            );
        } else {
            spriteCaminante = new SpriteCaminante(
                ImagePaths.MESERO_1,
                ImagePaths.MESERO_2
            );
        }
    
    
        spriteCaminante.getImageView().setLayoutX(x);
        spriteCaminante.getImageView().setLayoutY(y);
    
        if (caso == 1) {
            comedorView.addSprite(id + "mes1", spriteCaminante);
        } else if (caso == 2) {
            comedorView.addSprite(id + "mes2", spriteCaminante);
        } else if (caso == 3) {
            comedorView.addSprite(id + "mes3", spriteCaminante);
        } else if (caso == 4) {
            comedorView.addSprite(id + "mes4", spriteCaminante);
        }
       
        double destinoX = a;  
        double destinoY = b;  
        
        new AnimationTimer() {
            private double currentX = x;
            private double currentY = y;
            private double speed = 2;  
        
            @Override
            public void handle(long now) {
                if (Math.abs(currentX - destinoX) > 1) {
                    currentX += (currentX < destinoX) ? speed : -speed;
                    spriteCaminante.getImageView().setLayoutX(currentX);
                }
    
                if (Math.abs(currentY - destinoY) > 1) {
                    currentY += (currentY < destinoY) ? speed : -speed;
                    spriteCaminante.getImageView().setLayoutY(currentY);
                }
    
                if (Math.abs(currentX - destinoX) <= 1 && Math.abs(currentY - destinoY) <= 1) {
                    stop();
                        latch.countDown();
                }
            }
        }.start();
    }
    
}
