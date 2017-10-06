package com.github.ronangel.katas.gol.ui;

import javafx.scene.canvas.Canvas;

public class TwoDimentionalCanvas {

    private final Canvas canvas;

    public TwoDimentionalCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public double getHeight() {
        return canvas.getHeight();
    }

    public double getWidth() {
        return 0.0;
    }
}
