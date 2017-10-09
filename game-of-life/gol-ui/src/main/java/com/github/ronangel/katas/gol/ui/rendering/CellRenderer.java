package com.github.ronangel.katas.gol.ui.rendering;

import javafx.scene.canvas.GraphicsContext;

public class CellRenderer {

    public void render(GraphicsContext graphicsContext, double x, double y, double width, double height) {
        graphicsContext.fillRect(x + 1, y + 1, width - 2, height - 2);
    }
}
