package org.example.components.cocina;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        
        Rectangle left = new Rectangle(5, 450);
        left.setFill(Color.BLACK);
        left.setTranslateX(0); 
        left.setTranslateY(0);   

        Rectangle door = new Rectangle(10, 100);
        door.setFill(Color.WHITE);
        door.setTranslateX(0); 
        door.setTranslateY(180); 

        isla.getChildren().addAll(top, right, bottom, left, door);

        Image image1 = new Image(getClass().getResource("/org/example/assets/refri.png").toExternalForm());
        ImageView imageView1 = new ImageView(image1);
        imageView1.setLayoutX(-10); 
        imageView1.setLayoutY(0); 
        imageView1.setFitWidth(100);  
        imageView1.setFitHeight(50); 
        imageView1.setRotate(180); 

        ImageView imageView5 = new ImageView(image1);
        imageView5.setLayoutX(-10); 
        imageView5.setLayoutY(400); 
        imageView5.setFitWidth(100);  
        imageView5.setFitHeight(50); 

        Image image2 = new Image(getClass().getResource("/org/example/assets/estufa.png").toExternalForm());
        ImageView imageView2 = new ImageView(image2);
        imageView2.setLayoutX(70);  
        imageView2.setLayoutY(-10); 
        imageView2.setFitWidth(70);  
        imageView2.setFitHeight(70);

        ImageView imageView6 = new ImageView(image2);
        imageView6.setLayoutX(140);  
        imageView6.setLayoutY(150); 
        imageView6.setFitWidth(70);  
        imageView6.setFitHeight(70);

        ImageView imageView7 = new ImageView(image2);
        imageView7.setLayoutX(140);  
        imageView7.setLayoutY(220); 
        imageView7.setFitWidth(70);  
        imageView7.setFitHeight(70);


        ImageView imageView8 = new ImageView(image2);
        imageView8.setLayoutX(70);  
        imageView8.setLayoutY(390); 
        imageView8.setFitWidth(70);  
        imageView8.setFitHeight(70);

        imageView6.setRotate(90);
        imageView7.setRotate(90);
        imageView8.setRotate(180);
        

        Image image3 = new Image(getClass().getResource("/org/example/assets/lavadero.png").toExternalForm());
        ImageView imageView3 = new ImageView(image3);
        imageView3.setLayoutX(120);  
        imageView3.setLayoutY(60); 
        imageView3.setFitWidth(100);
        imageView3.setFitHeight(80);

        ImageView imageView4 = new ImageView(image3);
        imageView4.setLayoutX(120);  
        imageView4.setLayoutY(300); 
        imageView4.setFitWidth(100);
        imageView4.setFitHeight(80);


       
        imageView3.setRotate(90); 
        imageView4.setRotate(90); 

        isla.getChildren().addAll(imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8);
    }

    public Pane getIsla() {
        return isla;
    }
}
