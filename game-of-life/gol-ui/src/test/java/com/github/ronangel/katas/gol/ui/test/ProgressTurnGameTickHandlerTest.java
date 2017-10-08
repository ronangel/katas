package com.github.ronangel.katas.gol.ui.test;

import com.github.ronangel.katas.gol.core.Grid;
import com.github.ronangel.katas.gol.ui.GameOfLifeController;
import com.github.ronangel.katas.gol.ui.ProgressTurnGameTickHandler;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.*;

public class ProgressTurnGameTickHandlerTest {

    private Grid grid;

    private ProgressTurnGameTickHandler handler;
    private GameOfLifeController controller;

    @Before
    public void setup() {
        grid = mock(Grid.class);
        handler = new ProgressTurnGameTickHandler(grid);
        handler.setTurnIntervalNanos(500);
        handler.setStartTimeNanos(0);

        controller = mock(GameOfLifeController.class);
        handler.setController(controller);
    }

    @Test
    public void shouldNotProgressTurnBeforeFirstIntervalMet() throws Exception {
        handler.handle(250);

        verify(grid, never()).nextTurn();
    }

    @Test
    public void shouldProgressTurnAfterFirstIntervalMet() throws Exception {
        handler.handle(500);

        verify(grid).nextTurn();
    }

    @Test
    public void shouldNotProgressTurnBeforeSecondIntervalMet() throws Exception {
        handler.handle(550);
        handler.handle(600);

        verify(grid, times(1)).nextTurn();
    }

    @Test
    public void shouldProgressMultipleTurnsWhenMultipleOfIntervalHasPasses() throws Exception {
        handler.handle(1500);

        verify(grid, times(3)).nextTurn();
    }

    @Test
    public void shouldTakeIntoAccountStartTimeWhenProgressingTurn() throws Exception {
        handler.setStartTimeNanos(200);
        handler.handle(600);

        verify(grid, never()).nextTurn();
    }

    @Test
    public void shouldCallRenderOnTurnProgress() throws Exception {
        handler.handle(500);

        verify(controller).render();
    }

    @Test
    public void shouldOnlyCallRenderOnceForMultipleTurns() throws Exception {
        handler.handle(1500);

        verify(controller, times(1)).render();
    }

    @Test
    public void shouldCallIncrementTurnNumberOnTurnProgress() throws Exception {
        handler.handle(500);

        verify(controller).incrementTurnNumber();
    }
}
