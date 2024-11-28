package org.example.utils;
import javafx.animation.AnimationTimer;
import org.example.sprites.SpriteCaminante;
import org.example.views.CocinaView;
public class SpriteCocinero {

     public static void multiPosicion(int id,double x, double y, double a, double b, CocinaView cocinaView, int caso) {
        if(caso==1){
            cocinaView.removeSpriteById(id+"coci");
        }
        if(caso==2){
            cocinaView.removeSpriteById(id+"ped");
        }
        
        SpriteCaminante spriteCaminante = new SpriteCaminante(
            ImagePaths.COCINERO_1, 
            ImagePaths.COCINERO_2
        );
        
        spriteCaminante.getImageView().setLayoutX(x);
        spriteCaminante.getImageView().setLayoutY(y);

        if(caso==1){
            cocinaView.addSprite(id+"ped", spriteCaminante);
        }
        if(caso==2){
            cocinaView.addSprite(id+"coci", spriteCaminante);
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
                }
            }
        }.start();
    }
    
    
}
