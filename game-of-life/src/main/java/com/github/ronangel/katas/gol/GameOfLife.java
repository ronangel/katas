package com.github.ronangel.katas.gol;

import com.github.ronangel.katas.gol.core.*;
import com.github.ronangel.katas.gol.core.rendering.GridRenderer;
import com.github.ronangel.katas.gol.core.rendering.TextRenderer;

public class GameOfLife
{
    public static void main(String []args)
    {
        try
        {
            Grid grid = new Grid(10, 10);

            grid.setCell(Cell.ALIVE, CellLocation.get(4, 5));
            grid.setCell(Cell.ALIVE, CellLocation.get(5, 5));
            grid.setCell(Cell.ALIVE, CellLocation.get(6, 5));
            grid.setCell(Cell.ALIVE, CellLocation.get(5, 6));

            GridRenderer renderer = new TextRenderer(System.out);

            DeathChecker deathChecker = new DeathChecker();

            doTurn(renderer, grid);

            Thread.sleep(1000);

            do
            {
                doTurn(renderer, grid);

                Thread.sleep(1000);

            } while (!deathChecker.check(grid));

            System.out.println("GAME OF LIFE IS OVER");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void doTurn(GridRenderer renderer, Grid grid) throws Exception
    {
        renderer.render(grid);

        System.out.println("\n\n\n\n\n\n");

        grid.nextTurn();
    }
}
