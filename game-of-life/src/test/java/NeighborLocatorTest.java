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

        CellLocation location = new CellLocation(0, 0);

        List<CellLocation> neighbors = locator.locateNeighbors(location);

        assertEquals(8, neighbors.size());

        assertTrue(neighbors.contains(new CellLocation(1, 1)));
        assertTrue(neighbors.contains(new CellLocation(1, 0)));
        assertTrue(neighbors.contains(new CellLocation(1, -1)));
        assertTrue(neighbors.contains(new CellLocation(0, -1)));
        assertTrue(neighbors.contains(new CellLocation(0, 1)));
        assertTrue(neighbors.contains(new CellLocation(-1, 1)));
        assertTrue(neighbors.contains(new CellLocation(-1, 0)));
        assertTrue(neighbors.contains(new CellLocation(-1, -1)));
    }

    @Test
    public void shouldFindCellsForBottomLeftCorner() throws Exception
    {
        Grid grid = new Grid(2, 2);

        List<CellLocation> theValidLocations = new ArrayList<>(3);
        theValidLocations.add(new CellLocation(0, 1));
        theValidLocations.add(new CellLocation(1, 1));
        theValidLocations.add(new CellLocation(1, 0));

        for (CellLocation loc : theValidLocations)
        {
            grid.setCell(Cell.DEAD, loc);
        }

        NeighborLocator locator = new NeighborLocator();

        CellLocation bottomLeftCorner = new CellLocation(0,0);

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
