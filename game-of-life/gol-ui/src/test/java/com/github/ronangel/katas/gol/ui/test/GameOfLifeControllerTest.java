package com.github.ronangel.katas.gol.ui.test;

import com.github.ronangel.katas.gol.ui.GameOfLifeControllerReplacement;
import com.github.ronangel.katas.gol.ui.GameProgressTimer;
import com.github.ronangel.katas.gol.ui.GridOverlay;
import javafx.event.ActionEvent;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GameOfLifeControllerTest
{
    private GameOfLifeControllerReplacement controller;

    @Mock
    private GameProgressTimer gameProgressTimer;

    @Mock
    private ActionEvent event;

    @Before
    public void setup() {
        controller = new GameOfLifeControllerReplacement();
        controller.setGameProgressTimer(gameProgressTimer);
    }

    @Test
    public void shouldStartGameProgressTimerWhenStartButtonIsPressed() {

        controller.startButtonPressed(event);

        verify(gameProgressTimer).start();
    }

    @Test
    public void shouldStopGameProgressTimerWhenStopButtonPressed() {

        controller.stopButtonPressed(event);

        verify(gameProgressTimer).stop();
    }

    @Test
    public void shouldUseGridOverlayToDetermineGridCoordinateOnGridMouseClick() {

        GridOverlay gridOverlay = mock(GridOverlay.class);

        Assert.fail("TODO");
    }
}
