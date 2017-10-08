package com.github.ronangel.katas.gol.ui.test;

import com.github.ronangel.katas.gol.ui.TwoDimentionalCanvas;
import javafx.scene.canvas.Canvas;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TwoDimentionalCanvasTest {

    private Canvas canvas;

    private TwoDimentionalCanvas canvasWrapper;

    @Before
    public void setup() {
        canvas = new Canvas(84.0, 42.0);
        canvasWrapper = new TwoDimentionalCanvas(canvas);
    }

    @Test
    public void shouldGetHeightDimentionFromCanvas() {
        double height = canvasWrapper.getHeight();

        assertEquals(42.0, height);
    }

    @Test
    public void shouldGetWidthDimentionFromCanvas() {
        double width = canvasWrapper.getWidth();

        assertEquals(84.0, width);
    }
}
