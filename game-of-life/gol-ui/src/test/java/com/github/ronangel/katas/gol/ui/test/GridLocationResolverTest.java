package com.github.ronangel.katas.gol.ui.test;

import com.github.ronangel.katas.gol.core.CellLocation;
import com.github.ronangel.katas.gol.core.Grid;
import com.github.ronangel.katas.gol.core.exceptions.InvalidGridSizeException;
import com.github.ronangel.katas.gol.ui.mechanics.GridLocationResolver;
import com.github.ronangel.katas.gol.ui.mechanics.InvalidCoordinateException;
import com.github.ronangel.katas.gol.ui.mechanics.TwoDimentional;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GridLocationResolverTest {

    private GridLocationResolver gridLocationResolver;
    private double width;
    private double height;

    @Before
    public void setup() throws InvalidGridSizeException {
        width = 200.0;
        height = 100.0;

        TwoDimentional twoDimentionalObject = mock(TwoDimentional.class);
        when(twoDimentionalObject.getHeight()).thenReturn(height);
        when(twoDimentionalObject.getWidth()).thenReturn(width);

        Grid grid = new Grid(2, 2);
        gridLocationResolver = new GridLocationResolver(grid, twoDimentionalObject);

    }

    @Test(expected = InvalidCoordinateException.class)
    public void shouldThrowForNegativeX() throws Exception {
        CellLocation location = gridLocationResolver.getCellLocation(-1.0, height / 2);
    }

    @Test(expected = InvalidCoordinateException.class)
    public void shouldThrowForNegativeY() throws Exception {
        CellLocation location = gridLocationResolver.getCellLocation(width / 2, -1.0);
    }

    @Test(expected = InvalidCoordinateException.class)
    public void shouldThrowForOutOfBoundsY() throws Exception {
        CellLocation location = gridLocationResolver.getCellLocation(width / 2, height + 1);
    }

    @Test(expected = InvalidCoordinateException.class)
    public void shouldThrowForOutOfBoundsX() throws Exception{
        CellLocation location = gridLocationResolver.getCellLocation(width + 1, height / 2);
    }

    @Test
    public void shouldReturnBottomLeftCellForGridCoordinates() throws Exception {
        CellLocation expectedLocation = CellLocation.get(0, 0);
        CellLocation actualLocation = gridLocationResolver.getCellLocation(width *1/4, height * 1/4);

        assertEquals(expectedLocation, actualLocation);
    }

    @Test
    public void shouldReturnBottomRightCellForGridCoordinates() throws Exception {
        CellLocation expectedLocation = CellLocation.get(1, 0);
        CellLocation actualLocation = gridLocationResolver.getCellLocation(width * 3/4, height * 1/4);

        assertEquals(expectedLocation, actualLocation);
    }

    @Test
    public void shouldReturnTopLeftCellForGridCoordinates() throws Exception {
        CellLocation expectedLocation = CellLocation.get(0, 1);
        CellLocation actualLocation = gridLocationResolver.getCellLocation(width * 1/4, height *3/4);

        assertEquals(expectedLocation, actualLocation);
    }

    @Test
    public void shouldReturnTopRightCellForGridCoordinates() throws Exception {
        CellLocation expectedLocation = CellLocation.get(1, 1);
        CellLocation actualLocation = gridLocationResolver.getCellLocation(width *3/4, height * 3/4);

        assertEquals(expectedLocation, actualLocation);
    }
}
