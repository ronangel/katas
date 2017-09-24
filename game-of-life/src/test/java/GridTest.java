import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static util.AssertHelper.assertThrows;

public class GridTest
{
    @Test
    public void shouldHaveHeight() throws Exception
    {
        Grid grid = new Grid(10, 20);

        assertEquals(20, grid.getHeight());
    }

    @Test
    public void shouldHaveWidth() throws Exception
    {
        Grid grid = new Grid(10, 20);

        assertEquals(10, grid.getWidth());
    }

    @Test
    public void shouldThrowExceptionIfHeightOrWidthLessThanOne()
    {
        assertThrows(() -> new Grid(0, 1), InvalidGridSizeException.class);
        assertThrows(() -> new Grid(1, 0), InvalidGridSizeException.class);
    }

    //TODO - edge cases for trying to get cells that are out of range

    @Test
    public void shouldReturnCellThatWasSet() throws Exception
    {
        Grid grid = new Grid(10, 20);

        Cell cell = mock(Cell.class);

        grid.setCell(cell, new CellLocation(0, 0));

        Cell returnedCell = grid.getCell(new CellLocation(0, 0));

        assertEquals(cell, returnedCell);
    }

    @Test
    public void shouldThrowExceptionIfCellInNegativeLocation() throws Exception
    {
        Grid grid = new Grid(10, 10);

        assertThrows(() -> grid.setCell(Cell.DEAD, new CellLocation(-1,0)), InvalidCellLocationException.class);

        assertThrows(() -> grid.setCell(Cell.DEAD, new CellLocation(0,-1)), InvalidCellLocationException.class);
    }

    @Test
    public void shouldThrowExceptionIfCellNotInRange() throws Exception
    {
        Grid grid = new Grid(10, 10);

        assertThrows(() -> grid.setCell(Cell.DEAD, new CellLocation(0, 10)), InvalidCellLocationException.class);

        assertThrows(() -> grid.setCell(Cell.DEAD, new CellLocation(10, 0)), InvalidCellLocationException.class);
    }


    @Test
    public void shouldReturnDeadCellIfCellWasNotSet() throws Exception
    {
        Grid grid = new Grid(1, 1);
        Cell theCell = grid.getCell(new CellLocation(0,0));

        assertFalse(theCell.isAlive());
    }

    @Test
    public void shouldSetAllCellsToDeadAfterOneTurnWhenNoCellsSet() throws Exception
    {
        Grid grid = new Grid(10, 10);

        grid.nextTurn();

        for (int col = 0; col < grid.getWidth(); col++)
        {
            for (int row = 0; row < grid.getHeight(); row++)
            {
                assertFalse(grid.getCell(new CellLocation(col, row)).isAlive());
            }
        }
    }

    @Test
    public void shouldKillOneCell() throws Exception
    {
        Grid grid = new Grid(3, 3);
        CellLocation location = new CellLocation(1, 1);
        grid.setCell(Cell.ALIVE, location);
        grid.nextTurn();

        assertFalse(grid.getCell(location).isAlive());
    }

    @Test
    public void shouldKeepOneCellAliveIfTwoNeighborsAreAlive() throws Exception
    {
        Grid grid = new Grid(3, 3);
        CellLocation targetCellLocation = new CellLocation(1, 1);
        CellLocation firstNeighbor = new CellLocation(0, 0);
        CellLocation secondNeighbor = new CellLocation(0, 1);

        grid.setCell(Cell.ALIVE, targetCellLocation);
        grid.setCell(Cell.ALIVE, firstNeighbor);
        grid.setCell(Cell.ALIVE, secondNeighbor);

        grid.nextTurn();

        assertTrue(grid.getCell(targetCellLocation).isAlive());
    }

    @Test
    public void shouldKeepOneCellAliveIfThreeNeighborsAreAlive() throws Exception
    {
        Grid grid = new Grid(3, 3);
        CellLocation targetCellLocation = new CellLocation(1, 1);
        CellLocation firstNeighbor = new CellLocation(0, 0);
        CellLocation secondNeighbor = new CellLocation(0, 1);
        CellLocation thirdNeighbor = new CellLocation(0, 2);

        grid.setCell(Cell.ALIVE, targetCellLocation);
        grid.setCell(Cell.ALIVE, firstNeighbor);
        grid.setCell(Cell.ALIVE, secondNeighbor);
        grid.setCell(Cell.ALIVE, thirdNeighbor);

        grid.nextTurn();

        assertTrue(grid.getCell(targetCellLocation).isAlive());
    }

    @Test
    public void shouldKillCellIfFourNeighborsAreAlive() throws Exception
    {
        Grid grid = new Grid(3, 3);
        CellLocation targetCellLocation = new CellLocation(1, 1);
        CellLocation firstNeighbor = new CellLocation(0, 0);
        CellLocation secondNeighbor = new CellLocation(0, 1);
        CellLocation thirdNeighbor = new CellLocation(0, 2);
        CellLocation fourthNeighbor = new CellLocation(1, 2);

        grid.setCell(Cell.ALIVE, targetCellLocation);
        grid.setCell(Cell.ALIVE, firstNeighbor);
        grid.setCell(Cell.ALIVE, secondNeighbor);
        grid.setCell(Cell.ALIVE, thirdNeighbor);
        grid.setCell(Cell.ALIVE, fourthNeighbor);

        grid.nextTurn();

        assertFalse(grid.getCell(targetCellLocation).isAlive());
    }
}
