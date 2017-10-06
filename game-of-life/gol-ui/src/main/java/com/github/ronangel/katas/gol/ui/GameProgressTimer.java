package com.github.ronangel.katas.gol.ui;

import javafx.animation.AnimationTimer;

public class GameProgressTimer {

    private final AnimationTimer animationTimer;

    private GameTickHandler gameTickHandler;
    private TimestampProvider timestampProvider;
    private boolean started;
    private long startTimeNano;

    public GameProgressTimer(RegisterableAnimationTimer animationTimer, GameTickHandler gameTickHandler) {

        if (animationTimer == null)
        {
            throw new IllegalArgumentException("animationTimer cannot be null");
        }

        if (gameTickHandler == null)
        {
            throw new IllegalArgumentException("gameTickHandler cannot be null");
        }

        this.animationTimer = animationTimer;
        this.gameTickHandler = gameTickHandler;
        this.started = false;
        this.timestampProvider = System::nanoTime;

        animationTimer.registerHandler((now) -> handle(now));
    }

    public void start() {

        animationTimer.start();

        startTimeNano = timestampProvider.getCurrentTimeNanos();

        started = true;
    }

    public void stop() {

        animationTimer.stop();

        started = false;
    }

    public boolean isStarted() {
        return started;
    }

    public void setTimestampProvider(TimestampProvider timestampProvider) {
        this.timestampProvider = timestampProvider;
    }

    public long getStartTimeNano() {
        return startTimeNano;
    }

    private void handle(long now) {
        gameTickHandler.gameTick(startTimeNano, now);
    }
}
