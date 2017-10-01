package com.github.ronangel.katas.gol.ui;

import com.github.ronangel.katas.gol.core.Cell;
import com.github.ronangel.katas.gol.core.CellLocation;
import com.github.ronangel.katas.gol.core.Grid;
import com.github.ronangel.katas.gol.core.exceptions.InvalidCellLocationException;
import com.github.ronangel.katas.gol.core.rendering.GridRenderer;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.util.concurrent.TimeUnit;

public class GameOfLifeController {

    @FXML
    private Button startButton;

    @FXML
    private Button stopButton;

    @FXML
    private Label turnNumberLabel;

    @FXML
    private Canvas canvas;

    private Grid grid;

    private GridRenderer renderer;

    private GameProgressTimer gameTimer;

    private int turnsPerSecond;

    private int lastTurn;

    public GridRenderer getRenderer() {
        return renderer;
    }

    public void init()
    {
        renderer = new CanvasGridRenderer(canvas);
        turnsPerSecond = 1;
        lastTurn = 0;
        gameTimer = new GameProgressTimer();
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    @FXML
    void pressStartButton(ActionEvent event) {
        gameTimer.start();
    }

    @FXML
    void pressStopButton(ActionEvent event) {
        gameTimer.stop();
    }

        @FXML
    void gridMouseClick(MouseEvent event) {

        // can only click on cells while the game is not progressing
        if (gameTimer.isRunning())
            return;

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
            turnNumberLabel.setText(Integer.toString(lastTurn));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private long gameTick(long start, long now) {

        long elapsedNanos = now - start;
        long nanosPerTurn = TimeUnit.SECONDS.toNanos(1) / turnsPerSecond;
        long lastTurnNano = start;

        int turnsAdvanced = 0;
        while (elapsedNanos > nanosPerTurn) {
            grid.nextTurn();

            elapsedNanos -= nanosPerTurn;
            lastTurnNano += nanosPerTurn;
            turnsAdvanced++;
        }

        lastTurn += turnsAdvanced;

        if (turnsAdvanced > 0) {
            renderGrid();
        }

        return lastTurnNano;
    }

    private class GameProgressTimer extends AnimationTimer {

        private long start;
        private boolean isRunning;

        public GameProgressTimer() {
//            start = System.nanoTime();
        }

        @Override
        public void handle(long now) {
            start = gameTick(start, now);
        }

        @Override
        public void start() {
            start = System.nanoTime();
            isRunning = true;

            super.start();
        }

        @Override
        public void stop() {
            isRunning = false;

            super.stop();
        }

        public boolean isRunning() {
            return isRunning;
        }
    }
}
