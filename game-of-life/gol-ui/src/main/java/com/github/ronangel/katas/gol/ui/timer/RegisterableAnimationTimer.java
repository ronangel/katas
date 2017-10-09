package com.github.ronangel.katas.gol.ui.timer;

import javafx.animation.AnimationTimer;

public class RegisterableAnimationTimer extends AnimationTimer {

    private TimerHandler timerHandler;
    private TimerExceptionHandler timerExceptionHandler;

    public void registerHandler(TimerHandler timerHandler) {
        this.timerHandler = timerHandler;
    }

    public void setExceptionHandler(TimerExceptionHandler timerExceptionHandler) {
        this.timerExceptionHandler = timerExceptionHandler;
    }

    @Override
    public void handle(long now) {

        if (timerHandler != null)
        {
            try {
                timerHandler.handle(now);
            } catch (Exception e) {
                if (timerExceptionHandler != null)
                {
                    timerExceptionHandler.handleException(e);
                }
            }
        }
    }
}
