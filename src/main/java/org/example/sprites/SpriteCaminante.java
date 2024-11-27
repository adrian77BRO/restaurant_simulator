package org.example.sprites;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SpriteCaminante {

    private ImageView imageView;
    private Image image1;
    private Image  image2;
    private long lastFrameChangeTime;
    private static final long ANIMATION_DELAY =  100_000_000;

    public SpriteCaminante() {
        image1 = new Image(getClass().getResource("/org/example/assets/mesero/2.png").toExternalForm());
        image2 = new Image(getClass().getResource("/org/example/assets/mesero/3.png").toExternalForm());
        imageView = new ImageView(image1);
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);
    }

    public void iniciarAnimacion() {
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
    }

    public ImageView getImageView() {
        return imageView;
    }
}