package com.github.ronangel.katas.gol.ui;

import com.github.ronangel.katas.gol.core.Grid;
import com.github.ronangel.katas.gol.core.rendering.GridRenderer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

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
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        Canvas canvas = (Canvas) scene.lookup("#canvas");

        TwoDimentional canvasDimentions = new TwoDimentionalCanvas(canvas);

        Grid grid = new Grid(15, 10);
        GridLocationResolver gridLocationResolver = new GridLocationResolver(grid, canvasDimentions);
        GridOverlay gridOverlay = new GridOverlay(grid, gridLocationResolver);

        ProgressTurnGameTickHandler timerHandler = new ProgressTurnGameTickHandler(grid);
        RegisterableAnimationTimer animationTimer = new RegisterableAnimationTimer();
        animationTimer.registerHandler(timerHandler);

        GameProgressTimer gameProgressTimer = new GameProgressTimer(animationTimer);

        GameOfLifeController controller = loader.getController();
        controller.setGameProgressTimer(gameProgressTimer);
        controller.setGridOverlay(gridOverlay);

        controller.setGrid(grid);

        GridRenderer gridRenderer = new CanvasGridRenderer(canvas);
        controller.setGridRenderer(gridRenderer);

        controller.render();

    }
}
