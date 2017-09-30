package com.github.ronangel.katas.gol.core;

import java.util.ArrayList;
import java.util.List;

public class Grid
{
    private final int height;
    private final int width;
    private final CellLocationValidator locationValidator;
    private final NeighborLocator neighborLocator;
    private Cell[][] cells;

    public Grid(int width, int height) throws InvalidGridSizeException
    {
        if (width < 1)
            throw new InvalidGridSizeException();

        if (height < 1)
            throw new InvalidGridSizeException();

        this.width = width;
        this.height = height;

        cells = new Cell[width][height];

        locationValidator = new CellLocationValidator();
        neighborLocator = new NeighborLocator();
    }

    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }

    public synchronized void setCell(Cell cell, CellLocation location) throws InvalidCellLocationException
    {
        validateLocation(location);

        int x = location.getX();
        int y = location.getY();

        cells[x][y] = cell;
    }

    public synchronized Cell getCell(CellLocation location)
    {
        int x = location.getX();
        int y = location.getY();

        Cell cell = cells[x][y];

        if (cell == null)
        {
            cell = Cell.DEAD;
        }

        return cell;
    }

    public synchronized void nextTurn()
    {
        Cell[][] newGrid = new Cell[width][height];

        for (int col = 0; col < width; col++)
        {
            for (int row = 0; row < height; row++)
            {
                CellLocation currentCellLocation = new CellLocation(col, row);
                Cell currentCell = getCell(currentCellLocation);

                List<CellLocation> neighborLocations = neighborLocator.locateNeighbors(currentCellLocation);
                List<Cell> neighbors = getNeighbors(neighborLocations);
                int numAliveNeighbors = countAliveNeighbors(neighbors);

                newGrid[col][row] = currentCell.nextTurn(numAliveNeighbors);
            }
        }

        swapGrid(newGrid);
    }

    private List<Cell> getNeighbors(List<CellLocation> neighborLocations)
    {
        List<Cell> neighbors = new ArrayList<>();

        for (CellLocation location : neighborLocations)
        {
            if (locationValidator.validate(this, location))
            {
                neighbors.add(getCell(location));
            }
        }

        return neighbors;
    }

    private int countAliveNeighbors(List<Cell> neighbors)
    {
        return (int) neighbors.stream().filter(Cell::isAlive).count();
    }

    private void validateLocation(CellLocation location) throws InvalidCellLocationException
    {
        if (!locationValidator.validate(this, location))
        {
            throw new InvalidCellLocationException();
        }
    }

    private synchronized void swapGrid(Cell[][] newGrid)
    {
        cells = newGrid;
    }
}