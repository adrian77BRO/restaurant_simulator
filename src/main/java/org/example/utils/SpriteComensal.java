package org.example.utils;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import java.util.List;
import java.util.Random;
import org.example.components.comedor.Mesa;
import org.example.models.Comensal;
import org.example.sprites.SpriteCaminante;
import org.example.views.RecepcionView;
import com.almasb.fxgl.dsl.FXGL;

import org.example.models.ObservadorEntrada;

public class SpriteComensal {

    private static final double SEPARACION = 50;
    private static final double INICIO_Y = 450;
    private static final double ENTRADA = 100;
    private static int cantidadSprites = 0;

    private static ObservadorEntrada observadorEntrada;

    // Este método permite configurar el observador desde fuera (p.ej., en Restaurante)
    public static void setObservadorEntrada(ObservadorEntrada observador) {
        observadorEntrada = observador;
    }

    private static void notificarObservador(int idComensal) {
        // Notificar al observador cuando un comensal llega
        if (observadorEntrada != null) {
            observadorEntrada.notificarComensalLlegado(idComensal);
        }
    }


    public static void moverSprite(RecepcionView recepcionView, Comensal comensal) {
        int numeroSprite = cantidadSprites;
        int idComensal = comensal.getId();
        System.out.println(idComensal);

        SpriteCaminante spriteCaminante = new SpriteCaminante(
            ImagePaths.CLIENTE_IMAGEN_1, 
            ImagePaths.CLIENTE_IMAGEN_2 
        );
        spriteCaminante.getImageView().setLayoutX(30);
        spriteCaminante.getImageView().setLayoutY(INICIO_Y);

        recepcionView.addSprite(spriteCaminante);

        double destinoY = 100 + (numeroSprite * SEPARACION);  

        new AnimationTimer() {
            private double currentY = INICIO_Y;
            private double speed = 2;         

            @Override
            public void handle(long now) {
                if (currentY > destinoY) {
                    currentY -= speed;
                    spriteCaminante.getImageView().setLayoutY(currentY); 

                    if (currentY <= ENTRADA) {
                        notificarObservador(idComensal); 
                        stop();
                    }

                } else {
                    stop(); 
                }
            }
        }.start();

        cantidadSprites++;
    }

    //el que este en primero va medienate esto a una mesa
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
