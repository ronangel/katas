package com.github.ronangel.katas.gol.ui.test;

import com.github.ronangel.katas.gol.core.Cell;
import com.github.ronangel.katas.gol.core.CellLocation;
import com.github.ronangel.katas.gol.core.Grid;
import com.github.ronangel.katas.gol.ui.mechanics.GridLocationResolver;
import com.github.ronangel.katas.gol.ui.mechanics.GridOverlay;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GridOverlayTest {

    private Grid grid;
    private GridLocationResolver locationResolver;
    private CellLocation cellLocation;
    private GridOverlay gridOverlay;

    @Before
    public void setup() throws Exception {
        grid = mock(Grid.class);
        locationResolver = mock(GridLocationResolver.class);
        cellLocation = cellLocation;
        gridOverlay = new GridOverlay(grid, locationResolver);

        when(grid.getCell(cellLocation)).thenReturn(Cell.DEAD);
        when(locationResolver.getCellLocation(42.0, 84.0)).thenReturn(cellLocation);
    }

    @Test
    public void shouldGetCellLocationFromGridLocationResolver() throws Exception {
        gridOverlay.flipCell(42.0, 84.0);

        verify(locationResolver).getCellLocation(42.0, 84.0);
    }

    @Test
    public void shouldGetCellValueFromGridInOrderToFlipCell() throws Exception {
        gridOverlay.flipCell(42.0, 84.0);

        verify(grid).getCell(cellLocation);
    }

    @Test
    public void shouldTurnOnDeadCellOnGrid() throws Exception {
        when(grid.getCell(cellLocation)).thenReturn(Cell.DEAD);

        gridOverlay.flipCell(42.0, 84.0);

        verify(grid).setCell(Cell.ALIVE, cellLocation);
    }

    @Test
    public void shouldTurnOffAliveCellOnGrid() throws Exception {
        when(grid.getCell(cellLocation)).thenReturn(Cell.ALIVE);

        gridOverlay.flipCell(42.0, 84.0);

        verify(grid).setCell(Cell.DEAD, cellLocation);
    }
}
