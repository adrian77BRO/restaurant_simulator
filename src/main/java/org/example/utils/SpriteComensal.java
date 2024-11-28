package org.example.utils;

import javafx.animation.AnimationTimer;
import org.example.sprites.SpriteCaminante;
import org.example.views.ComedorView;
import org.example.views.RecepcionView;
import org.example.models.Comensal;



public class SpriteComensal {

    private static final double SEPARACION = 50;
    private static final double INICIO_Y = 450;
    private static int cantidadSprites = 0;

    public static void moverSprite(RecepcionView recepcionView, int id) {
        System.out.println("Comensal " + id + " está en la recepción.");
        int numeroSprite = cantidadSprites;
        SpriteCaminante spriteCaminante = new SpriteCaminante(
            ImagePaths.CLIENTE_IMAGEN_1, 
            ImagePaths.CLIENTE_IMAGEN_2 
        );
        spriteCaminante.getImageView().setLayoutX(30);
        spriteCaminante.getImageView().setLayoutY(INICIO_Y);

        recepcionView.addSprite(id+"col", spriteCaminante);

        double destinoY = 100 + (numeroSprite * SEPARACION);  

        new AnimationTimer() {
            private double currentY = INICIO_Y;
            private double speed = 2;         

            @Override
            public void handle(long now) {
                if (currentY > destinoY) {
                    currentY -= speed;
                    spriteCaminante.getImageView().setLayoutY(currentY); 

                } else {
                    stop(); 
                    Comensal.colaComensal();
                }
            }
        }.start();

        cantidadSprites++;
    }

    public static void multiPosicion(int id,double x, double y, double a, double b, RecepcionView recepcionView, ComedorView comedorView, int caso) {
        if(caso==1){
            recepcionView.removeSpriteById(id+"col");
        }
        if(caso==2){
            comedorView.removeSpriteById(id+"ent");
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
