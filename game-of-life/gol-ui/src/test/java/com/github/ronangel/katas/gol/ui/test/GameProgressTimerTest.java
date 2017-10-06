package com.github.ronangel.katas.gol.ui.test;

import com.github.ronangel.katas.gol.ui.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GameProgressTimerTest
{
    @Mock
    RegisterableAnimationTimer registerableAnimationTimer;

    @Mock
    private TimestampProvider timestampProvider;

    @Mock
    private GameTickHandler gameTickHandler;

    GameProgressTimer gameProgressTimer;

    @Before
    public void setup() throws Exception {
        gameProgressTimer = new GameProgressTimer(registerableAnimationTimer, gameTickHandler);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIfAnimationTimerIsNull() {
        gameProgressTimer = new GameProgressTimer(null, gameTickHandler);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIfGameTickHandlerIsNull() {
        gameProgressTimer = new GameProgressTimer(registerableAnimationTimer, null);
    }

    @Test
    public void shouldCallAnimationTimerStartOnStart() {

        gameProgressTimer.start();

        verify(registerableAnimationTimer).start();
    }

    @Test
    public void shouldCallAnimationTimerStopOnStop() {

        gameProgressTimer.stop();

        verify(registerableAnimationTimer).stop();
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

    @Test
    public void shouldRegisterItselfWithTimer() {
        verify(registerableAnimationTimer).registerHandler(notNull(TimerHandler.class));
    }

    @Test
    public void shouldDelegateToGameTickHandlerOnHandle() {

        registerableAnimationTimer = mock(RegisterableAnimationTimer.class);

        doCallRealMethod().when(registerableAnimationTimer).registerHandler(any(TimerHandler.class));
        doCallRealMethod().when(registerableAnimationTimer).handle(84L);

        gameProgressTimer = new GameProgressTimer(registerableAnimationTimer, gameTickHandler);

        gameProgressTimer.setTimestampProvider(timestampProvider);

        when(timestampProvider.getCurrentTimeNanos()).thenReturn(42L);

        gameProgressTimer.start();

        registerableAnimationTimer.handle(84L);

        verify(gameTickHandler).gameTick(42, 84);
    }
}
