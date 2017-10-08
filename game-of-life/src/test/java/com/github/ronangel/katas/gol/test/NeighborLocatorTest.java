package com.github.ronangel.katas.gol.test;

import com.github.ronangel.katas.gol.core.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class NeighborLocatorTest
{

    @Test
    public void shouldReturnEightNeighbors()
    {
        NeighborLocator locator = new NeighborLocator();

        CellLocation location = CellLocation.get(0, 0);

        List<CellLocation> neighbors = locator.locateNeighbors(location);

        assertEquals(8, neighbors.size());

        assertTrue(neighbors.contains(CellLocation.get(1, 1)));
        assertTrue(neighbors.contains(CellLocation.get(1, 0)));
        assertTrue(neighbors.contains(CellLocation.get(1, -1)));
        assertTrue(neighbors.contains(CellLocation.get(0, -1)));
        assertTrue(neighbors.contains(CellLocation.get(0, 1)));
        assertTrue(neighbors.contains(CellLocation.get(-1, 1)));
        assertTrue(neighbors.contains(CellLocation.get(-1, 0)));
        assertTrue(neighbors.contains(CellLocation.get(-1, -1)));
    }

    @Test
    public void shouldFindCellsForBottomLeftCorner() throws Exception
    {
        Grid grid = new Grid(2, 2);

        List<CellLocation> theValidLocations = new ArrayList<>(3);
        theValidLocations.add(CellLocation.get(0, 1));
        theValidLocations.add(CellLocation.get(1, 1));
        theValidLocations.add(CellLocation.get(1, 0));

        for (CellLocation loc : theValidLocations)
        {
            grid.setCell(Cell.DEAD, loc);
        }

        NeighborLocator locator = new NeighborLocator();

        CellLocation bottomLeftCorner = CellLocation.get(0,0);

        List<CellLocation> neighborLocations = locator.locateNeighbors(bottomLeftCorner);

        CellLocationValidator validator = new CellLocationValidator();

        int numValidLocs = 0;
        for (CellLocation loc : neighborLocations)
        {
            if (validator.validate(grid, loc))
            {
                assertTrue(theValidLocations.contains(loc));

                numValidLocs++;
            }
        }

        assertEquals(3, numValidLocs);
    }
}
