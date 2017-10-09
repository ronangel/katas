package com.github.ronangel.katas.gol.ui.test;

import com.github.ronangel.katas.gol.core.Grid;
import com.github.ronangel.katas.gol.core.rendering.GridRenderer;
import com.github.ronangel.katas.gol.ui.GameOfLifeController;
import com.github.ronangel.katas.gol.ui.mechanics.GridOverlay;
import com.github.ronangel.katas.gol.ui.timer.GameProgressTimer;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.swing.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GameOfLifeControllerTest
{
    private GameOfLifeController controller;

    @Mock
    private GameProgressTimer gameProgressTimer;

    @Mock
    private ActionEvent actionEvent;

    @Mock
    private MouseEvent mouseEvent;

    @Mock
    private GridOverlay gridOverlay;

    @Mock
    private GridRenderer gridRenderer;

    @Mock
    private Label turnLabel;

    @Mock
    private Grid grid;

    @Before
    public void setup() {
        controller = new GameOfLifeController();
        controller.setGameProgressTimer(gameProgressTimer);
        controller.setGridOverlay(gridOverlay);
        controller.setGridRenderer(gridRenderer);
        controller.setTurnLabel(turnLabel);
        controller.setGrid(grid);
        mouseEvent = new MouseEvent(MouseEvent.MOUSE_CLICKED, 42.0, 84.0, 42.0, 84.0,
                MouseButton.PRIMARY, 0, false, false, false,
                false, false, false, false,
                false, false, false, null);
    }

    @Test
    public void shouldStartGameProgressTimerWhenStartButtonIsPressed() {
        controller.startButtonPressed(actionEvent);

        verify(gameProgressTimer).start();
    }

    @Test
    public void shouldStopGameProgressTimerWhenStopButtonPressed() {
        controller.stopButtonPressed(actionEvent);

        verify(gameProgressTimer).stop();
    }

    @Test
    public void shouldUseGridOverlayToFlipCellOnGridMouseClick() throws Exception {
        controller.gridMouseClick(mouseEvent);

        verify(gridOverlay).flipCell(42.0, 84.0);
    }

    @Test
    public void shouldNotFlipCellIfTimerIsStarted() throws Exception {
        when(gameProgressTimer.isStarted()).thenReturn(true);

        controller.gridMouseClick(mouseEvent);

        verify(gridOverlay, never()).flipCell(anyDouble(), anyDouble());
    }

    @Test
    public void shouldDelegateToGridRendererOnRender() throws Exception {

        controller.setGrid(grid);
        controller.setGridRenderer(gridRenderer);

        controller.render();

        verify(gridRenderer).render(grid);
    }

    @Test
    public void shouldRenderAfterCellClick() throws Exception {
        controller.gridMouseClick(mouseEvent);

        verify(gridRenderer).render(any());
    }

    @Test
    public void shouldStartWithTurnNumberZero() throws Exception {
        int turnNumber = controller.getTurnNumber();

        assertEquals(0, turnNumber);
    }

    @Test
    public void shouldProperlyIncrementTurnNumber() throws Exception {
        controller.incrementTurnNumber();

        int turnNumber = controller.getTurnNumber();

        assertEquals(1, turnNumber);
    }

    @Test
    public void shouldUpdateLabelForTurnNumberOnIncrement() {
        controller.incrementTurnNumber();

        assertEquals("1", turnLabel.getText());
    }

    @Test
    public void shouldResetGridOnResetButtonPressed() throws Exception {
        controller.resetButtonPressed();

        verify(grid).reset();
    }

    @Test
    public void shouldRenderOnResetButtonPressed() throws Exception {
        controller.resetButtonPressed();

        verify(gridRenderer).render(grid);
    }

    @Test
    public void shouldStopTimerOnResetButtonPressed() throws Exception {
        controller.resetButtonPressed();

        verify(gameProgressTimer).stop();
    }

    @Test
    public void shouldResetTurnCounterToZeroOnResetButtonPressed() throws Exception {
        controller.resetButtonPressed();

        assertEquals("0", turnLabel.getText());
    }

    @BeforeClass
    public static void initToolkit()
            throws InterruptedException
    {
        final CountDownLatch latch = new CountDownLatch(1);
        SwingUtilities.invokeLater(() -> {
            new JFXPanel(); // initializes JavaFX environment
            latch.countDown();
        });

        // That's a pretty reasonable delay... Right?
        if (!latch.await(5L, TimeUnit.SECONDS))
            throw new ExceptionInInitializerError();
    }
}
