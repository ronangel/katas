package com.github.ronangel.katas.gol.test;

import com.github.ronangel.katas.gol.core.Cell;
import com.github.ronangel.katas.gol.core.CellLocation;
import com.github.ronangel.katas.gol.core.DeathChecker;
import com.github.ronangel.katas.gol.core.Grid;
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
        grid.setCell(Cell.ALIVE, CellLocation.get(5, 5));

        assertFalse(checker.check(grid));
    }
}
