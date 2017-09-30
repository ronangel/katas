package com.github.ronangel.katas.gol.core;

public class CellLocationValidator
{
    public boolean validate(Grid grid, CellLocation location)
    {
        if (location.getX() < 0)
        {
            return false;
        }
        else if (location.getY() < 0)
        {
            return false;
        }
        else if (location.getX() >= grid.getWidth())
        {
            return false;
        }
        else if (location.getY() >= grid.getHeight())
        {
            return false;
        }

        return true;
    }
}
