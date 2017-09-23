import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class CellTest {

    @Test
    public void shouldDieWithLessThanTwoNeighbors()
    {
        Cell cell = new Cell(true);
        cell.nextTurn(1);

        assertFalse(cell.isAlive());
    }

    @Test
    public void shouldSurviveWithTwoNeighbors()
    {
        Cell cell = new Cell(true);
        cell.nextTurn(2);

        assertTrue(cell.isAlive());
    }

    @Test
    public void shouldSurviveWithThreeNeighbors()
    {
        Cell cell = new Cell(true);
        cell.nextTurn(3);

        assertTrue(cell.isAlive());
    }

    @Test
    public void shouldDieWithMoreThanThreeNeighbors()
    {
        Cell cell = new Cell(true);
        cell.nextTurn(4);

        assertFalse(cell.isAlive());
    }

    @Test
    public void shouldReviveIfDeadWithExactlyThreeNeighbors()
    {
        Cell cell = new Cell(false);
        cell.nextTurn(3);

        assertTrue(cell.isAlive());
    }

    // TODO - set mock Grid and have cells ask for their number of neighbors, which will delegate to the Grid
}
