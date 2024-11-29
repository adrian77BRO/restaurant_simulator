package org.example.views;

import org.example.components.cocina.Mosaico;
import org.example.sprites.SpriteCaminante;
import org.example.components.cocina.Isla;

import javafx.application.Platform;
import javafx.scene.layout.Pane;

public class CocinaView {
    public Pane view;

    public CocinaView() {
        view = new Pane();
        view.setPrefSize(200, 450);
        view.setStyle("-fx-background-color: white;");
        Mosaico mosaico = new Mosaico(18, 8, 25);
        Isla isla = new Isla();
       
        view.getChildren().addAll(mosaico.getMosaico(), isla.getIsla());
    }

    public Pane getView() {
        return view;
    }

    public void addSprite(String id, SpriteCaminante spriteCaminante) {
        Platform.runLater(() -> {
            spriteCaminante.getImageView().setId("sprite-" + id);
            view.getChildren().add(spriteCaminante.getImageView());
            spriteCaminante.iniciarAnimacion();
        });
    }
    
    public void deshabilitarSprite(String id) {
        Platform.runLater(() -> {
            String nodeId = "sprite-" + id;
            var node = view.lookup("#" + nodeId);
            if (node != null) {
                node.setVisible(false);
            }
        });
    }

    public void removeSpriteById(String id) {
        Platform.runLater(() -> {
            String nodeId = "sprite-" + id;
            var node = view.lookup("#" + nodeId);
            if (node != null) {
                view.getChildren().remove(node);
            }
        });
    }


}
