package com.github.ronangel.katas.gol.ui.mechanics;

import com.github.ronangel.katas.gol.core.Cell;
import com.github.ronangel.katas.gol.core.CellLocation;
import com.github.ronangel.katas.gol.core.Grid;
import com.github.ronangel.katas.gol.core.exceptions.InvalidCellLocationException;

public class GridOverlay {

    private final GridLocationResolver locationResolver;
    private final Grid grid;

    public GridOverlay(Grid grid, GridLocationResolver locationResolver) {
        this.grid = grid;
        this.locationResolver = locationResolver;
    }

    public void flipCell(double x, double y) throws InvalidCoordinateException, InvalidCellLocationException {
        CellLocation cellLocation = locationResolver.getCellLocation(x, y);

        Cell cell = grid.getCell(cellLocation);

        Cell replacementCell = (cell.isAlive()) ? Cell.DEAD : Cell.ALIVE;

        grid.setCell(replacementCell, cellLocation);
    }
}
