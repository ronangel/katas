package com.github.ronangel.katas.gol.ui.mechanics;

import javafx.scene.canvas.Canvas;

public class TwoDimentionalCanvas implements TwoDimentional {

    private final Canvas canvas;

    public TwoDimentionalCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public double getHeight() {
        return canvas.getHeight();
    }

    @Override
    public double getWidth() { return canvas.getWidth(); }
}
