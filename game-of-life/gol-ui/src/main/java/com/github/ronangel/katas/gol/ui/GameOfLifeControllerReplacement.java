package com.github.ronangel.katas.gol.ui;

import javafx.event.ActionEvent;

public class GameOfLifeControllerReplacement {

    private GameProgressTimer gameProgressTimer;

    public void setGameProgressTimer(GameProgressTimer gameProgressTimer) {
        this.gameProgressTimer = gameProgressTimer;
    }

    public void startButtonPressed(ActionEvent event) {
        gameProgressTimer.start();
    }

    public void stopButtonPressed(ActionEvent event) {
        gameProgressTimer.stop();
    }
}
