import org.junit.Test;
import static org.junit.Assert.*;

public class CellLocationTest
{
    @Test
    public void shouldReturnCoordinateValuesPassedToConstructor()
    {
        CellLocation cellLocation = new CellLocation(1, 1);

        int x = cellLocation.getX();

        assertEquals(1, x);

        int y = cellLocation.getY();

        assertEquals(1, y);
    }

    @Test
    public void cellLocationsShouldBeEqual()
    {
        CellLocation loc1 = new CellLocation(5, 5);
        CellLocation loc2 = new CellLocation(5, 5);

        assertEquals(loc1, loc2);
    }
}