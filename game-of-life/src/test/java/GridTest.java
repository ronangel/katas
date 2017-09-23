import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class GridTest
{

    @Test
    public void shouldHaveHeight()
    {
        Grid grid = new Grid(10, 20);

        assertEquals(20, grid.getHeight());
    }

    @Test
    public void shouldHaveWidth()
    {
        Grid grid = new Grid(10, 20);

        assertEquals(10, grid.getWidth());
    }

    @Test
    public void shouldReturnNullCellForCellThatWasNotSet()
    {
        Grid grid = new Grid(10, 20);

        Cell cell = grid.getCell(new CellLocation(0, 0));

        assertNull(cell);
    }

    @Test
    public void shouldReturnCellThatWasSet()
    {
        Grid grid = new Grid(10, 20);

        Cell cell = mock(Cell.class);

        grid.setCell(cell, new CellLocation(0, 0));

        Cell returnedCell = grid.getCell(new CellLocation(0, 0));

        assertEquals(cell, returnedCell);
    }

    // todo - edge cases


    @Test
    public void shouldReturnZeroNeighbors()
    {
        Grid grid = new Grid(10, 10);
        Cell cell = mock(Cell.class);
        CellLocation location = new CellLocation(5, 5);

        grid.setCell(cell, location);

//        int numNeighbors = grid.getNumNeighbors(location);


    }
}
