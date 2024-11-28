package org.example.sprites;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SpriteCaminante {

    private ImageView imageView;
    private Image image1;
    private Image image2;
    private long lastFrameChangeTime;
    private static final long ANIMATION_DELAY =  100_000_000; 

    public SpriteCaminante(String rutaImagen1, String rutaImagen2) {
        image1 = new Image(getClass().getResource(rutaImagen1).toExternalForm());
        image2 = new Image(getClass().getResource(rutaImagen2).toExternalForm());
        imageView = new ImageView(image1);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
    }

    public void iniciarAnimacion() {
        Platform.runLater(() -> {
            new AnimationTimer() {
                @Override
                public void handle(long now) {
                    if (now - lastFrameChangeTime >= ANIMATION_DELAY) {
                        if (imageView.getImage() == image1) {
                            imageView.setImage(image2);
                        } else {
                            imageView.setImage(image1);
                        }
                        lastFrameChangeTime = now;
                    }
                }
            }.start();
        });
    }


    public ImageView getImageView() {
        return imageView;
    }
}
