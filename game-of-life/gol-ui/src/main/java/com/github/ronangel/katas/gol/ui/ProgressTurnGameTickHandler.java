package com.github.ronangel.katas.gol.ui;

import com.github.ronangel.katas.gol.core.Grid;

public class ProgressTurnGameTickHandler implements TimerHandler {

    private final Grid grid;

    private int intervalMs;
    private int lastTurnMs;

    public ProgressTurnGameTickHandler(Grid grid) {
        this.grid = grid;
    }

    public void setTurnIntervalMs(int turnIntervalMs) {
        this.intervalMs = turnIntervalMs;
    }

    public void setStartTimeMs(int startTimeMs) {
        this.lastTurnMs = startTimeMs;
    }

    @Override
    public void handle(long now) {

        while (now - lastTurnMs >= intervalMs)
        {
            grid.nextTurn();

            lastTurnMs += intervalMs;
        }
    }
}
