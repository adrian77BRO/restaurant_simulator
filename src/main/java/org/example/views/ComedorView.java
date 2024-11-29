package org.example.views;

import org.example.components.comedor.ContenedorMesas;
import org.example.components.comedor.Mesa;
import org.example.sprites.SpriteCaminante;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.List;

public class ComedorView {
    private Pane view;
    private ContenedorMesas contenedorMesas;

    public ComedorView() {
        view = new Pane();
        view.setPrefSize(700, 450);
        view.setStyle("-fx-background-color: red;");

        contenedorMesas = new ContenedorMesas(5, 8);
        view.getChildren().addAll(contenedorMesas.getContenedorMesas());
    }

    public Pane getView() {
        return view;
    }

    public ContenedorMesas getContenedorMesas() {
        return contenedorMesas;
    }

    public List<Mesa> getMesas() {
        return contenedorMesas.getMesas();
    }

    public void addSprite(String id, SpriteCaminante spriteCaminante) {
        Platform.runLater(() -> {
            spriteCaminante.getImageView().setId("sprite-" + id);
            view.getChildren().add(spriteCaminante.getImageView());
            spriteCaminante.iniciarAnimacion();
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

    public void deshabilitarSprite(String id) {
        Platform.runLater(() -> {
            String nodeId = "sprite-" + id;
            var node = view.lookup("#" + nodeId);
            if (node != null) {
                node.setVisible(false);
            }
        });
    }
    
    public boolean containsSprite(String id) {
        String nodeId = "sprite-" + id;
        var node = view.lookup("#" + nodeId);
        return node != null;
    }

    
    
    

}