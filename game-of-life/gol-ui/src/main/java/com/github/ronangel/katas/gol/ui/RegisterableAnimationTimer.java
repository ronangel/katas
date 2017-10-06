package com.github.ronangel.katas.gol.ui;

import javafx.animation.AnimationTimer;

public class RegisterableAnimationTimer extends AnimationTimer {

    private TimerHandler timerHandler;

    public void registerHandler(TimerHandler timerHandler) {
        this.timerHandler = timerHandler;
    }

    @Override
    public void handle(long now) {

        if (timerHandler != null)
        {
            timerHandler.handle(now);
        }
    }
}
