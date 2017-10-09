package com.github.ronangel.katas.gol.ui;

import com.github.ronangel.katas.gol.core.Grid;
import com.github.ronangel.katas.gol.core.rendering.GridRenderer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

public class GameOfLifeUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../../../../../resources/GameOfLifeUI.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Conway's Game of Life");
        primaryStage.setScene(scene);
        primaryStage.show();

        Canvas canvas = (Canvas) scene.lookup("#canvas");
        Label turnLabel = (Label) scene.lookup("#turnNumberLabel");

        TwoDimentional canvasDimentions = new TwoDimentionalCanvas(canvas);

        Grid grid = new Grid(100, 50);
        GridLocationResolver gridLocationResolver = new GridLocationResolver(grid, canvasDimentions);
        GridOverlay gridOverlay = new GridOverlay(grid, gridLocationResolver);

        GameOfLifeController controller = loader.getController();
        GridRenderer gridRenderer = new CanvasGridRenderer(canvas);
        ProgressTurnGameTickHandler timerHandler = new ProgressTurnGameTickHandler(grid);
        timerHandler.setController(controller);
        timerHandler.setTurnIntervalNanos(TimeUnit.MILLISECONDS.toNanos(10));

        RegisterableAnimationTimer animationTimer = new RegisterableAnimationTimer();
        animationTimer.registerHandler(timerHandler);
        animationTimer.setExceptionHandler(this::handleTimerException);

        GameProgressTimer gameProgressTimer = new GameProgressTimer(animationTimer);
        gameProgressTimer.setStartTimeCallback(timerHandler::setStartTimeNanos);

        controller.setGameProgressTimer(gameProgressTimer);
        controller.setGridOverlay(gridOverlay);

        controller.setGrid(grid);

        controller.setGridRenderer(gridRenderer);

        controller.setTurnLabel(turnLabel);

        controller.render();
    }

    private void handleTimerException(Exception e) {
        e.printStackTrace(System.out);
    }
}
