package org.example.utils;

import javafx.animation.AnimationTimer;

import org.example.sprites.SpriteCaminante;
import org.example.views.ComedorView;
import org.example.views.RecepcionView;

public class SpriteMesero {

    public static void multiPosicion(int id,double x, double y, double a, double b, RecepcionView recepcionView, ComedorView comedorView, int caso) {
        if(caso==1){
            recepcionView.removeSpriteById(id+"aten");
        }
        if(caso==2){
            comedorView.removeSpriteById(id+"desp");
        }
        if(caso==3){
            comedorView.removeSpriteById(id+"pedir");
        }
        
        SpriteCaminante spriteCaminante = new SpriteCaminante(
            ImagePaths.CLIENTE_IMAGEN_1, 
            ImagePaths.CLIENTE_IMAGEN_2
        );
        
        spriteCaminante.getImageView().setLayoutX(x);
        spriteCaminante.getImageView().setLayoutY(y);

        if(caso==1){
            comedorView.addSprite(id+"ent", spriteCaminante);
            System.out.println("animacion sentarse id " + id);
        }
        if(caso==2){
            comedorView.addSprite(id+"sal", spriteCaminante);
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
                    if(caso==1){
                        
                    }
                    if (caso==2){
                        
                    }
                }
            }
        }.start();
    }
    
}
