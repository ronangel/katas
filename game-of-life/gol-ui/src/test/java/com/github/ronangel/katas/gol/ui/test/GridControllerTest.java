package com.github.ronangel.katas.gol.ui.test;

import com.github.ronangel.katas.gol.core.Grid;
import com.github.ronangel.katas.gol.ui.GridController;
import com.github.ronangel.katas.gol.ui.GridOverlay;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GridControllerTest {

    private GridOverlay overlay;
    private Grid grid;
    private GridController gridController;

    @Before
    public void setup() throws Exception {
        overlay = mock(GridOverlay.class);
        grid = mock(Grid.class);
        gridController = new GridController(grid, overlay);
    }

    @Test
    public void shouldDelegateToGridOverlayOnFlipCell() throws Exception {
        gridController.flipCell(42.0, 84.0);

        verify(overlay).flipCell(42.0, 84.0);
    }

    @Test
    public void shouldCallNextTurnOnProgress() {
        gridController.progress();

        verify(grid).nextTurn();
    }

    @Test
    public void shouldReturnZeroTurns() {
        int turnNumber = gridController.getTurnNumber();

        assertEquals(0, turnNumber);
    }

    @Test
    public void shouldReturnFiveTurns(){
        for (int i = 0; i < 5; i++)
            gridController.progress();

        int turnNumber = gridController.getTurnNumber();

        assertEquals(5, turnNumber);
    }
}
