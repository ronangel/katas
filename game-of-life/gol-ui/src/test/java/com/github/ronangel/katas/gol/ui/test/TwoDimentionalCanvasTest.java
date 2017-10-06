package com.github.ronangel.katas.gol.ui.test;

import com.github.ronangel.katas.gol.ui.TwoDimentionalCanvas;
import javafx.scene.canvas.Canvas;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CodeWithPrivateMethod.class)
public class TwoDimentionalCanvasTest {

    @Test
    public void shouldGetHeightDimentionFromCanvas() {
        Canvas canvas = mock(Canvas.class);
        when(canvas.getHeight()).thenReturn(42.0);

        TwoDimentionalCanvas canvasWrapper = new TwoDimentionalCanvas(canvas);

        double height = canvasWrapper.getHeight();

        verify(canvas).getHeight();

        assertEquals(42.0, height);
    }

    @Test
    public void shouldGetWidthDimentionFromCanvas() {
        Canvas canvas = mock(Canvas.class);
//        when(canvas.getWidth()).thenReturn(84.0);

        TwoDimentionalCanvas canvasWrapper = new TwoDimentionalCanvas(canvas);

        double width = canvasWrapper.getWidth();
    }
}
