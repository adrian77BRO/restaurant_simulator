package org.example.components.cocina;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Isla {
    private Pane isla;

    public Isla() {
        isla = new Pane();

        Rectangle top = new Rectangle(200, 50);
        top.setFill(Color.rgb(210, 180, 140)); 
        top.setTranslateX(0);
        top.setTranslateY(0);   

        Rectangle right = new Rectangle(50, 450);
        right.setFill(Color.rgb(210, 180, 140));
        right.setTranslateX(150); 
        right.setTranslateY(0);       

        Rectangle bottom = new Rectangle(200, 55);
        bottom.setFill(Color.rgb(210, 180, 140));  
        bottom.setTranslateX(0);   
        bottom.setTranslateY(400);   

        isla.getChildren().addAll(top, right, bottom);
    }

    public Pane getIsla() {
        return isla;
    }
}
