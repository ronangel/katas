package com.github.ronangel.katas.gol.core;

public class DeathChecker
{
    public boolean check(Grid grid)
    {
        for (int col = 0; col < grid.getWidth(); col++)
        {
            for (int row = 0; row < grid.getHeight(); row++)
            {
                if (grid.getCell(new CellLocation(col, row)).isAlive())
                {
                    return false;
                }
            }
        }

        return true;
    }
}
