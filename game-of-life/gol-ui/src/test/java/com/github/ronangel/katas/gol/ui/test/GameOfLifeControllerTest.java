package com.github.ronangel.katas.gol.ui.test;

import com.github.ronangel.katas.gol.core.Grid;
import com.github.ronangel.katas.gol.core.rendering.GridRenderer;
import com.github.ronangel.katas.gol.ui.*;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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

    @Before
    public void setup() {
        controller = new GameOfLifeController();
        controller.setGameProgressTimer(gameProgressTimer);
        controller.setGridOverlay(gridOverlay);
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

        GridRenderer gridRenderer = mock(GridRenderer.class);
        Grid grid = mock(Grid.class);

        controller.setGrid(grid);
        controller.setGridRenderer(gridRenderer);

        controller.render();

        verify(gridRenderer).render(grid);
    }
}
