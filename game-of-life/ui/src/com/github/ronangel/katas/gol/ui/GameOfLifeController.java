package com.github.ronangel.katas.gol.ui;

import com.github.ronangel.katas.gol.core.Cell;
import com.github.ronangel.katas.gol.core.CellLocation;
import com.github.ronangel.katas.gol.core.Grid;
import com.github.ronangel.katas.gol.core.exceptions.InvalidCellLocationException;
import com.github.ronangel.katas.gol.core.rendering.GridRenderer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class GameOfLifeController {

    @FXML
    private Button button;

    @FXML
    private Canvas canvas;

    private Grid grid;

    private GridRenderer renderer;

    public GridRenderer getRenderer() {
        return renderer;
    }

    public void init()
    {
        renderer = new CanvasGridRenderer(canvas);
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    @FXML
    void pressButton(ActionEvent event) {

        grid.nextTurn();
        
        renderGrid();
    }

    @FXML
    void gridMouseClick(MouseEvent event) {

        double canvasX = event.getX();
        double canvasY = event.getY();

        double width = canvas.getWidth();
        double height = canvas.getHeight();

        int gridX = (int) (canvasX / (width / grid.getWidth()));
        int gridY = (int) (canvasY / (height / grid.getHeight()));

        CellLocation cellLocation = new CellLocation(gridX, gridY);
        Cell currentCell = grid.getCell(cellLocation);
        currentCell = currentCell.equals(Cell.ALIVE) ? Cell.DEAD : Cell.ALIVE;

        try {
            grid.setCell(currentCell, cellLocation);
        } catch (InvalidCellLocationException e) {
            e.printStackTrace();
        }

        renderGrid();
    }

    private void renderGrid() {
        try {
            renderer.render(grid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
