package com.github.ronangel.katas.gol.ui.test;

import com.github.ronangel.katas.gol.core.CellLocation;
import com.github.ronangel.katas.gol.core.Grid;
import com.github.ronangel.katas.gol.core.exceptions.InvalidGridSizeException;
import com.github.ronangel.katas.gol.ui.GridLocationResolver;
import com.github.ronangel.katas.gol.ui.InvalidCoordinateException;
import com.github.ronangel.katas.gol.ui.TwoDimentional;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GridLocationResolverTest {

    private GridLocationResolver gridLocationResolver;

    @Before
    public void setup() throws InvalidGridSizeException {
        TwoDimentional twoDimentionalObject = mock(TwoDimentional.class);
        when(twoDimentionalObject.getHeight()).thenReturn(100.0);
        when(twoDimentionalObject.getWidth()).thenReturn(100.0);

        Grid grid = new Grid(2, 2);
        gridLocationResolver = new GridLocationResolver(grid, twoDimentionalObject);

    }

    @Test(expected = InvalidCoordinateException.class)
    public void shouldThrowForNegativeY() throws Exception {
        CellLocation location = gridLocationResolver.getCellLocation(50.0, -1.0);
    }

    @Test(expected = InvalidCoordinateException.class)
    public void shouldThrowForOutOfBoundsY() throws Exception {
        CellLocation location = gridLocationResolver.getCellLocation(50.0, 101.0);
    }

    @Test(expected = InvalidCoordinateException.class)
    public void shouldThrowForNegativeX() throws Exception {
        CellLocation location = gridLocationResolver.getCellLocation(-1.0, 50.0);
    }

    @Test(expected = InvalidCoordinateException.class)
    public void shouldThrowForOutOfBoundsX() throws Exception{
        CellLocation location = gridLocationResolver.getCellLocation(101.0, 50.0);
    }

    @Test
    public void shouldReturnBottomLeftCellForGridCoordinates() throws Exception {
        CellLocation expectedLocation = CellLocation.get(0, 0);
        CellLocation actualLocation = gridLocationResolver.getCellLocation(25.0, 25.0);

        assertEquals(expectedLocation, actualLocation);
    }

    @Test
    public void shouldReturnBottomRightCellForGridCoordinates() throws Exception {
        CellLocation expectedLocation = CellLocation.get(1, 0);
        CellLocation actualLocation = gridLocationResolver.getCellLocation(75.0, 25.0);

        assertEquals(expectedLocation, actualLocation);
    }

    @Test
    public void shouldReturnTopLeftCellForGridCoordinates() throws Exception {
        CellLocation expectedLocation = CellLocation.get(0, 1);
        CellLocation actualLocation = gridLocationResolver.getCellLocation(25.0, 75.0);

        assertEquals(expectedLocation, actualLocation);
    }

    @Test
    public void shouldReturnTopRightCellForGridCoordinates() throws Exception {
        CellLocation expectedLocation = CellLocation.get(1, 1);
        CellLocation actualLocation = gridLocationResolver.getCellLocation(75.0, 75.0);

        assertEquals(expectedLocation, actualLocation);
    }
}
