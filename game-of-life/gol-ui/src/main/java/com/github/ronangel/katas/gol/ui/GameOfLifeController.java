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

    private RegisterableAnimationTimer registerableAnimationTimer;

    public GridRenderer getRenderer() {
        return renderer;
    }

    public void init()
    {
        renderer = new CanvasGridRenderer(canvas);
        turnsPerSecond = 1;
        lastTurn = 0;
        registerableAnimationTimer = new RegisterableAnimationTimer();
        gameTimer = new GameProgressTimer(registerableAnimationTimer, ((start, now) -> gameTick(start, now)));
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
        if (gameTimer.isStarted())
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

    private long lastTurnNano = 0;

    private long gameTick(long start, long now) {

        if (lastTurnNano == 0)
            lastTurnNano = start;

        long elapsedNanos = now - lastTurnNano;
        long nanosPerTurn = TimeUnit.SECONDS.toNanos(1) / turnsPerSecond;

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
}
