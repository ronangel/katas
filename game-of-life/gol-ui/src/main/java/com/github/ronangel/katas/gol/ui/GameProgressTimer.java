package com.github.ronangel.katas.gol.ui;

import javafx.animation.AnimationTimer;

public class GameProgressTimer {

    private final AnimationTimer animationTimer;

    private TimestampProvider timestampProvider;
    private boolean started;
    private long startTimeNanos;
    private StartTimeCallback startTimeCallback;

    public GameProgressTimer(AnimationTimer animationTimer) {

        if (animationTimer == null)
        {
            throw new IllegalArgumentException("animationTimer cannot be null");
        }

        this.animationTimer = animationTimer;
        this.started = false;
        this.timestampProvider = System::nanoTime;
    }

    public void setStartTimeCallback(StartTimeCallback startTimeCallback) {
        this.startTimeCallback = startTimeCallback;
    }

    public void start() {

        animationTimer.start();

        startTimeNanos = timestampProvider.getCurrentTimeNanos();

        if (startTimeCallback != null)
        {
            startTimeCallback.setStartTime(startTimeNanos);
        }

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

    public long getStartTimeNanos() {
        return startTimeNanos;
    }
}
