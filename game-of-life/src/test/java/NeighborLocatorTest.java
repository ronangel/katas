import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NeighborLocatorTest
{

    @Test
    public void shouldReturnEightNeighbors()
    {
        NeighborLocator locator = new NeighborLocator();

        CellLocation location = new CellLocation(0, 0);

        List<CellLocation> neighbors = locator.locateNeighbors(location);

        assertEquals(8, neighbors.size());
    }


//    @Test
//    public void shouldFindCellsForBottomLeftCorner()
//    {
//        Grid grid = mock(Grid.class);
//        when(grid.getCell(new CellLocation(0, 1))).thenReturn(mock(Cell.class));
//        when(grid.getCell(new CellLocation(1, 1))).thenReturn(mock(Cell.class));
//        when(grid.getCell(new CellLocation(1, 0))).thenReturn(mock(Cell.class));
//
//        NeighborLocator locator = new NeighborLocator(grid);
//
//        CellLocation bottomLeftCorner = new CellLocation(0,0);
//
////        List<Cell> neighbors = locator.locateNeighbors(bottomLeftCorner);
//
////        assertEquals(3, neighbors.size());
//
//    }
}
