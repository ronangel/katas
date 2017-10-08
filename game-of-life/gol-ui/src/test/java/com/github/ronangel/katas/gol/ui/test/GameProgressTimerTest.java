package com.github.ronangel.katas.gol.ui.test;

import com.github.ronangel.katas.gol.ui.*;
import javafx.animation.AnimationTimer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GameProgressTimerTest
{
    @Mock
    AnimationTimer animationTimer;

    @Mock
    private TimestampProvider timestampProvider;

    @Mock
    private TimerHandler gameTickHandler;

    GameProgressTimer gameProgressTimer;

    @Before
    public void setup() throws Exception {
        gameProgressTimer = new GameProgressTimer(animationTimer);
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
        gameProgressTimer.setTimestampProvider(timestampProvider);

        when(timestampProvider.getCurrentTimeNanos()).thenReturn(42L);

        gameProgressTimer.start();

        assertEquals(42L, gameProgressTimer.getStartTimeNano());
    }
}
