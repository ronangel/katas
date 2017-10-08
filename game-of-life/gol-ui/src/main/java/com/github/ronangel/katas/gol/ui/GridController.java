package com.github.ronangel.katas.gol.ui;

import com.github.ronangel.katas.gol.core.Grid;
import com.github.ronangel.katas.gol.core.exceptions.InvalidCellLocationException;

public class GridController {

    private final GridOverlay overlay;
    private final Grid grid;
    private int turnNumber;

    public GridController(Grid grid, GridOverlay overlay) {
        this.grid = grid;
        this.overlay = overlay;
        this.turnNumber = 0;
    }

    public void flipCell(double x, double y) throws InvalidCoordinateException, InvalidCellLocationException {
        overlay.flipCell(x, y);
    }

    public void progress() {
        grid.nextTurn();

        turnNumber++;
    }

    public int getTurnNumber() {
        return turnNumber;
    }
}
