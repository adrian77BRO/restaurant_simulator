package org.example.utils;

import javafx.animation.AnimationTimer;

import org.example.sprites.SpriteCaminante;
import org.example.views.ComedorView;
import org.example.views.RecepcionView;
import java.util.concurrent.CountDownLatch;



public class SpriteComensal {

    public static void moverSprite(RecepcionView recepcionView, int id, CountDownLatch latch) {
        System.out.println("Comensal " + id + " está en la recepción.");
        SpriteCaminante spriteCaminante = new SpriteCaminante(
            ImagePaths.CLIENTE_IMAGEN_1, 
            ImagePaths.CLIENTE_IMAGEN_2 
        );
        spriteCaminante.getImageView().setLayoutX(10);
        spriteCaminante.getImageView().setLayoutY(450);

        recepcionView.addSprite(id+"col", spriteCaminante);

        double destinoY = 100;  

        new AnimationTimer() {
            private double currentY = 450;
            private double speed = 2;         

            @Override
            public void handle(long now) {
                if (currentY > destinoY) {
                    currentY -= speed;
                    spriteCaminante.getImageView().setLayoutY(currentY); 

                } else {
                    stop();
                    if(currentY==100){
                        latch.countDown();
                    }
                    
                }
            }
        }.start();
    }

    public static void multiPosicion(int id,double x, double y, double a, double b, RecepcionView recepcionView, ComedorView comedorView, int caso, CountDownLatch latch) {
        if(caso==1){
            recepcionView.removeSpriteById(id+"col");
        }
        if(caso==2){
            comedorView.removeSpriteById(id+"ent");
        }
        if(caso==3){
            comedorView.removeSpriteById(id+"sal");
        }
        
        SpriteCaminante spriteCaminante = new SpriteCaminante(
            ImagePaths.CLIENTE_IMAGEN_1, 
            ImagePaths.CLIENTE_IMAGEN_2
        );
        
        spriteCaminante.getImageView().setLayoutX(x);
        spriteCaminante.getImageView().setLayoutY(y);

        if(caso==1){
            comedorView.addSprite(id+"ent", spriteCaminante);
        }
        if(caso==2){
            comedorView.addSprite(id+"sal", spriteCaminante);
        }
        if(caso==3){
            recepcionView.addSprite(id+"huye", spriteCaminante);
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
                    if (caso==2){
                        if(currentY==100){
                            latch.countDown();
                        }
                    }
                    if(caso ==3){
                        recepcionView.removeSpriteById(id+"huye");
                        
                    } 
                }
            }
        }.start();
    }
    
}
