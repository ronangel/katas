package com.github.ronangel.katas.gol.test;

import com.github.ronangel.katas.gol.core.CellLocation;
import com.github.ronangel.katas.gol.core.CellLocationValidator;
import com.github.ronangel.katas.gol.core.Grid;
import com.github.ronangel.katas.gol.core.exceptions.InvalidGridSizeException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CellLocationValidatorTest
{
    private CellLocationValidator validator;
    private Grid grid;

    @Before
    public void setup() throws InvalidGridSizeException
    {
        validator = new CellLocationValidator();
        grid = new Grid(10, 10);
    }

    @Test
    public void shouldReturnValid()
    {
        CellLocation location = CellLocation.get(5, 5);

        boolean isLocationValid = validator.validate(grid, location);

        assertTrue(isLocationValid);
    }

    @Test
    public void shouldReturnInvalidBecauseColIsNegative()
    {
        CellLocation location = CellLocation.get(-1, 0);

        boolean isLocationValid = validator.validate(grid, location);

        assertFalse(isLocationValid);
    }

    @Test
    public void shouldReturnInvalidBecauseRowIsNegative()
    {
        CellLocation location = CellLocation.get(0, -1);

        boolean isLocationValid = validator.validate(grid, location);

        assertFalse(isLocationValid);
    }

    @Test
    public void shouldReturnInvalidBecauseColIsOutOfBounds()
    {
        CellLocation location = CellLocation.get(10, 0);

        boolean isLocationValid = validator.validate(grid, location);

        assertFalse(isLocationValid);
    }

    @Test
    public void shouldReturnInvalidBecauseRowIsOutOfBounds()
    {
        CellLocation location = CellLocation.get(0, 10);

        boolean isLocationValid = validator.validate(grid, location);

        assertFalse(isLocationValid);
    }

    @Test
    public void shouldReturnValidForBottomLeftCorner()
    {
        CellLocation location = CellLocation.get(0, 0);

        boolean isLocationValid = validator.validate(grid, location);

        assertTrue(isLocationValid);
    }

    @Test
    public void shouldReturnValidForBottomRightCorner()
    {
        CellLocation location = CellLocation.get(9, 0);

        boolean isLocationValid = validator.validate(grid, location);

        assertTrue(isLocationValid);
    }

    @Test
    public void shouldReturnValidForTopLeftCorner()
    {
        CellLocation location = CellLocation.get(0, 9);

        boolean isLocationValid = validator.validate(grid, location);

        assertTrue(isLocationValid);
    }

    @Test
    public void shouldReturnValidForTopRightCorner()
    {
        CellLocation location = CellLocation.get(9, 0);

        boolean isLocationValid = validator.validate(grid, location);

        assertTrue(isLocationValid);
    }
}
