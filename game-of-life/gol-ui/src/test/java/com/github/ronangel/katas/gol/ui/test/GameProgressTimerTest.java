package com.github.ronangel.katas.gol.ui.test;

import com.github.ronangel.katas.gol.ui.timer.GameProgressTimer;
import com.github.ronangel.katas.gol.ui.timer.StartTimeCallback;
import com.github.ronangel.katas.gol.ui.timer.TimestampProvider;
import javafx.animation.AnimationTimer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GameProgressTimerTest
{
    @Mock
    AnimationTimer animationTimer;

    @Mock
    private TimestampProvider timestampProvider;

    GameProgressTimer gameProgressTimer;

    @Before
    public void setup() throws Exception {
        gameProgressTimer = new GameProgressTimer(animationTimer);
        gameProgressTimer.setTimestampProvider(timestampProvider);
        when(timestampProvider.getCurrentTimeNanos()).thenReturn(42L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIfAnimationTimerIsNull() {
        gameProgressTimer = new GameProgressTimer(null);
    }

    @Test
    public void shouldCallAnimationTimerStartOnStart() {
        gameProgressTimer.start();

        verify(animationTimer).start();
    }

    @Test
    public void shouldCallAnimationTimerStopOnStop() {
        gameProgressTimer.stop();

        verify(animationTimer).stop();
    }

    @Test
    public void shouldReturnIsStartedAfterBeingStarted() {
        gameProgressTimer.start();

        assertTrue(gameProgressTimer.isStarted());
    }

    @Test
    public void shouldReturnNotStartedAfterBeingStopped() {
        gameProgressTimer.start();
        gameProgressTimer.stop();

        assertFalse(gameProgressTimer.isStarted());
    }

    @Test
    public void shouldDelegateToTimestampProviderToGetStartTime() {
        gameProgressTimer.setTimestampProvider(timestampProvider);

        gameProgressTimer.start();

        verify(timestampProvider).getCurrentTimeNanos();
    }

    @Test
    public void shouldReturnCorrectStartTime() {
        gameProgressTimer.start();

        assertEquals(42L, gameProgressTimer.getStartTimeNanos());
    }

    @Test
    public void shouldCallSetStartTimeOnStartCallback() {
        StartTimeCallback startTimeCallback = mock(StartTimeCallback.class);
        gameProgressTimer.setStartTimeCallback(startTimeCallback);

        gameProgressTimer.start();

        verify(startTimeCallback).setStartTime(42L);
    }
}
