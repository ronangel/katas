package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import java.util.Random;

public class Controller {

    @FXML
    private Button button;

    @FXML
    private Canvas canvas;

    @FXML
    void pressButton(ActionEvent event) {

        System.out.println("button pressed!");

        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.setStroke(Color.BLACK);

        final int x = new Random().nextInt(400);
        final int y = new Random().nextInt(300);
        final int width = 10;
        final int height = 10;

        graphicsContext.fillRect(x, y, width, height);



    }

}
