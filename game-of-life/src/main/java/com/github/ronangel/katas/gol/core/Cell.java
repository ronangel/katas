package com.github.ronangel.katas.gol.core;

public class Cell
{
    public static Cell ALIVE = new Cell(true);
    public static Cell DEAD = new Cell(false);

    private final boolean isAlive;

    private Cell(boolean isAlive)
    {
        this.isAlive = isAlive;
    }

    public Cell nextTurn(int numAliveNeighbors)
    {
        boolean isAlive = false;

        if (this.isAlive)
        {
            if (numAliveNeighbors == 2 || numAliveNeighbors == 3)
            {
                isAlive = true;
            }
        }
        else if (numAliveNeighbors == 3)
        {
            isAlive = true;
        }

        if (isAlive)
            return Cell.ALIVE;

        return Cell.DEAD;
    }

    public boolean isAlive()
    {
        return isAlive;
    }

    @Override
    public String toString() {
        return isAlive ? "ALIVE" : "DEAD";
    }

}
