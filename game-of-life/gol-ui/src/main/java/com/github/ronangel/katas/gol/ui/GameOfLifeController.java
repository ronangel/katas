package com.github.ronangel.katas.gol.ui;

import com.github.ronangel.katas.gol.core.Grid;
import com.github.ronangel.katas.gol.core.exceptions.InvalidCellLocationException;
import com.github.ronangel.katas.gol.core.rendering.GridRenderer;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class GameOfLifeController {

    private GameProgressTimer gameProgressTimer;
    private GridOverlay gridOverlay;
    private Grid grid;
    private GridRenderer gridRenderer;
    private int turnNumber;
    private Label turnLabel;

    public GameOfLifeController() {

    }

    public void setGameProgressTimer(GameProgressTimer gameProgressTimer) {
        this.gameProgressTimer = gameProgressTimer;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public void setGridRenderer(GridRenderer gridRenderer) {
        this.gridRenderer = gridRenderer;
    }

    public void setGridOverlay(GridOverlay gridOverlay) {
        this.gridOverlay = gridOverlay;
    }

    public void startButtonPressed(ActionEvent event) {
        gameProgressTimer.start();
    }

    public void stopButtonPressed(ActionEvent event) {
        gameProgressTimer.stop();
    }

    public void gridMouseClick(MouseEvent mouseEvent) throws Exception {
        if (!gameProgressTimer.isStarted()) {
            gridOverlay.flipCell(mouseEvent.getX(), mouseEvent.getY());
            gridRenderer.render(grid);
        }
    }

    public void render() throws Exception {
        gridRenderer.render(grid);
    }

    public void incrementTurnNumber() {
        turnNumber++;

        turnLabel.setText(Integer.toString(turnNumber));
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public void setTurnLabel(Label turnLabel) {
        this.turnLabel = turnLabel;
    }
}
