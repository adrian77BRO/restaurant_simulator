package org.example.components.cocina;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Mosaico {
    private GridPane grid;

    public Mosaico(int rows, int cols, double tileSize) {
        grid = new GridPane();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Rectangle tile = new Rectangle(tileSize, tileSize);
                if ((row + col) % 2 == 0) {
                    tile.setFill(Color.LIGHTGRAY);
                } else {
                    tile.setFill(Color.DARKGRAY);
                }
                grid.add(tile, col, row);
            }
        }
    }

    public GridPane getMosaico() {
        return grid;
    }
}
