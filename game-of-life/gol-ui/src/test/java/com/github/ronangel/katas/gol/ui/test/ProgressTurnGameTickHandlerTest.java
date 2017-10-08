package com.github.ronangel.katas.gol.ui.test;

import com.github.ronangel.katas.gol.core.Grid;
import com.github.ronangel.katas.gol.ui.ProgressTurnGameTickHandler;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ProgressTurnGameTickHandlerTest {

    private Grid grid;

    private ProgressTurnGameTickHandler handler;

    @Before
    public void setup() {
        grid = mock(Grid.class);

        handler = new ProgressTurnGameTickHandler(grid);
        handler.setTurnIntervalMs(500);
        handler.setStartTimeMs(0);
    }

    @Test
    public void shouldNotProgressTurnBeforeFirstIntervalMet() {
        handler.handle(250);

        verify(grid, never()).nextTurn();
    }

    @Test
    public void shouldProgressTurnAfterFirstIntervalMet() {
        handler.handle(500);

        verify(grid).nextTurn();
    }

    @Test
    public void shouldNotProgressTurnBeforeSecondIntervalMet() {
        handler.handle(550);
        handler.handle(600);

        verify(grid, times(1)).nextTurn();
    }

    @Test
    public void shouldProgressMultipleTurnsWhenMultipleOfIntervalHasPasses() {
        handler.handle(1500);

        verify(grid, times(3)).nextTurn();
    }

    @Test
    public void shouldTakeIntoAccountStartTimeWhenProgressingTurn() {
        handler.setStartTimeMs(200);
        handler.handle(600);

        verify(grid, never()).nextTurn();
    }
}
