package org.example.views;

import org.example.components.comedor.ContenedorMesas;
import org.example.components.comedor.Mesa;
import javafx.scene.layout.VBox;

import java.util.List;

public class ComedorView {
    private VBox view;
    private ContenedorMesas contenedorMesas;

    public ComedorView() {
        view = new VBox();
        view.setPrefSize(700, 450);
        view.setStyle("-fx-background-color: lightgreen;");

        // Crear el contenedor de mesas con filas y columnas
        contenedorMesas = new ContenedorMesas(5, 8);

        // Agregar el contenedor de mesas a la vista
        view.getChildren().addAll(contenedorMesas.getContenedorMesas());
    }

    public VBox getView() {
        return view;
    }

    // Método para obtener el contenedor de mesas
    public ContenedorMesas getContenedorMesas() {
        return contenedorMesas;
    }

    // Método para obtener todas las mesas del contenedor
    public List<Mesa> getMesas() {
        return contenedorMesas.getMesas();
    }
}
