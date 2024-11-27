package org.example.components.comedor;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.List;

public class ContenedorMesas {
    private GridPane grid;
    private List<Mesa> mesas; // Lista para almacenar las mesas

    public ContenedorMesas(int rows, int cols) {
        grid = new GridPane();
        grid.setPadding(new Insets(40));
        grid.setHgap(40);
        grid.setVgap(40);
        mesas = new ArrayList<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (row * cols + col >= 40) break; 
                Mesa mesa = new Mesa();
                grid.add(mesa.getMesa(), col, row);

                // Establecer las coordenadas de cada mesa y guardarlas en la lista
                mesa.setX(col * 100);  // Ajustar como desees
                mesa.setY(row * 100);  // Ajustar como desees
                mesas.add(mesa);  // Agregar la mesa a la lista
            }
        }
    }

    public GridPane getContenedorMesas() {
        return grid;
    }

    // Método para obtener las mesas
    public List<Mesa> getMesas() {
        return mesas;
    }
}
