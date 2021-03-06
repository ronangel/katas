package com.github.ronangel.katas.gol.test;

import com.github.ronangel.katas.gol.core.Cell;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(MockitoJUnitRunner.class)
public class CellTest {

    @Test
    public void shouldDieWithLessThanTwoNeighbors()
    {
        Cell cell = Cell.ALIVE;
        cell = cell.nextTurn(1);

        assertFalse(cell.isAlive());
    }

    @Test
    public void shouldSurviveWithTwoNeighbors()
    {
        Cell cell = Cell.ALIVE;
        cell = cell.nextTurn(2);

        assertTrue(cell.isAlive());
    }

    @Test
    public void shouldSurviveWithThreeNeighbors()
    {
        Cell cell = Cell.ALIVE;
        cell = cell.nextTurn(3);

        assertTrue(cell.isAlive());
    }

    @Test
    public void shouldDieWithMoreThanThreeNeighbors()
    {
        Cell cell = Cell.ALIVE;
        cell = cell.nextTurn(4);

        assertFalse(cell.isAlive());
    }

    @Test
    public void shouldReviveIfDeadWithExactlyThreeNeighbors()
    {
        Cell cell = Cell.DEAD;
        cell = cell.nextTurn(3);

        assertTrue(cell.isAlive());
    }

    @Test
    public void shouldStayDeadWithOnlyTwoNeighbors()
    {
        Cell cell = Cell.DEAD;
        cell = cell.nextTurn(2);

        assertFalse(cell.isAlive());
    }

    @Test
    public void shouldStayDeadWithFourNeighbors()
    {
        Cell cell = Cell.DEAD;
        cell = cell.nextTurn(4);

        assertFalse(cell.isAlive());
    }

    // TODO - set mock com.github.ronangel.katas.gol.core.Grid and have cells ask for their number of neighbors, which will delegate to the com.github.ronangel.katas.gol.core.Grid
}
