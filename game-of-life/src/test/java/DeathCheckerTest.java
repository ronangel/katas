import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DeathCheckerTest
{
    private Grid grid;
    private DeathChecker checker;

    @Before
    public void setup() throws Exception
    {
        grid = new Grid(10, 10);
        checker = new DeathChecker();
    }

    @Test
    public void shouldReturnGridIsDead() throws Exception
    {

        assertTrue(checker.check(grid));
    }

    @Test
    public void shouldReturnGridIsNotDead() throws Exception
    {
        grid.setCell(Cell.ALIVE, new CellLocation(5, 5));

        assertFalse(checker.check(grid));
    }
}
