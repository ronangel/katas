package com.github.ronangel.katas.gol.ui;

import com.github.ronangel.katas.gol.core.Grid;
import com.github.ronangel.katas.gol.ui.timer.TimerHandler;

public class ProgressTurnGameTickHandler implements TimerHandler {

    private final Grid grid;

    private long intervalNanos;
    private long lastTurnNanos;
    private GameOfLifeController controller;

    public ProgressTurnGameTickHandler(Grid grid) {
        this.grid = grid;
    }

    public void setTurnIntervalNanos(long turnIntervalNanos) {
        this.intervalNanos = turnIntervalNanos;
    }

    public void setStartTimeNanos(long startTimeNanos) {
        this.lastTurnNanos = startTimeNanos;
    }

    public void setController(GameOfLifeController controller) {
        this.controller = controller;
    }

    @Override
    public void handle(long now) throws Exception {

        boolean shouldRender = false;

        while (now - lastTurnNanos >= intervalNanos)
        {
            grid.nextTurn();

            lastTurnNanos += intervalNanos;

            controller.incrementTurnNumber();

            shouldRender = true;
        }

        if (shouldRender)
        {
            controller.render();
        }
    }
}
