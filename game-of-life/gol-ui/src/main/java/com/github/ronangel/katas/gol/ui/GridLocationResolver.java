package com.github.ronangel.katas.gol.ui;

import com.github.ronangel.katas.gol.core.CellLocation;
import com.github.ronangel.katas.gol.core.Grid;

public class GridLocationResolver {

    private final Grid grid;
    private final double height;
    private final double width;

    public GridLocationResolver(Grid grid, TwoDimentional twoDimentionalObject) {
        this.grid = grid;
        this.height = twoDimentionalObject.getHeight();
        this.width = twoDimentionalObject.getWidth();
    }

    public CellLocation getCellLocation(double x, double y) throws InvalidCoordinateException {
        validateCoordinates(x, y);

        int gridX = (int) (x / (width / grid.getWidth()));
        int gridY = (int) (y / (height / grid.getHeight()));

        return CellLocation.get(gridX, gridY);
    }

    private void validateCoordinates(double x, double y) throws InvalidCoordinateException {
        if (x < 0)
        {
            throw new InvalidCoordinateException("x coordinate is negative");
        }

        if (x > height)
        {
            throw new InvalidCoordinateException("x coordinate is out of bounds: " + x);
        }

        if (y < 0)
        {
            throw new InvalidCoordinateException("y coordinate is negative");
        }

        if (y > width)
        {
            throw new InvalidCoordinateException("y coordinate is out of bounds: " + y);
        }
    }
}
