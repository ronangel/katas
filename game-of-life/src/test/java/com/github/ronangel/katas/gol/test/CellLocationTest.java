package com.github.ronangel.katas.gol.test;

import com.github.ronangel.katas.gol.core.CellLocation;
import org.junit.Test;
import static org.junit.Assert.*;

public class CellLocationTest
{
    @Test
    public void shouldReturnCoordinateValuesPassedToConstructor()
    {
        CellLocation cellLocation = CellLocation.get(1, 1);

        int x = cellLocation.getX();

        assertEquals(1, x);

        int y = cellLocation.getY();

        assertEquals(1, y);
    }

    @Test
    public void cellLocationsShouldBeEqual()
    {
        CellLocation loc1 = CellLocation.get(5, 5);
        CellLocation loc2 = CellLocation.get(5, 5);

        assertEquals(loc1, loc2);
    }
}
