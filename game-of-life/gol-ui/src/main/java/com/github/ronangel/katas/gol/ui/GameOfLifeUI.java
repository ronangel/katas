package com.github.ronangel.katas.gol.ui;

import com.github.ronangel.katas.gol.core.Grid;
import com.github.ronangel.katas.gol.core.rendering.GridRenderer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameOfLifeUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Grid grid = new Grid(15, 10);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../../../../../resources/GameOfLifeUI.fxml"));
        Parent root = loader.load();

        GameOfLifeController controller = loader.getController();
        controller.setGrid(grid);
        controller.init();

        GridRenderer renderer = controller.getRenderer();
        renderer.render(grid);

        primaryStage.setTitle("Conway's Game of Life");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
