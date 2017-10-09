package com.github.ronangel.katas.gol.ui.test;

import com.github.ronangel.katas.gol.ui.timer.RegisterableAnimationTimer;
import com.github.ronangel.katas.gol.ui.timer.TimerExceptionHandler;
import com.github.ronangel.katas.gol.ui.timer.TimerHandler;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class RegisterableAnimationTimerTest {

    @Test
    public void shouldCallRegisteredHandlerOnHandle() throws Exception {
        TimerHandler handler = mock(TimerHandler.class);

        RegisterableAnimationTimer timer = new RegisterableAnimationTimer();
        timer.registerHandler(handler);

        timer.handle(42L);

        verify(handler).handle(42L);
    }

    @Test
    public void shouldCallExceptionHandlerOnException() throws Exception {
        TimerHandler handler = mock(TimerHandler.class);
        Exception expectedException = mock(Exception.class);

        doThrow(expectedException).when(handler).handle(42L);

        RegisterableAnimationTimer timer = new RegisterableAnimationTimer();
        timer.registerHandler(handler);

        TimerExceptionHandler timerExceptionHandler = mock(TimerExceptionHandler.class);
        timer.setExceptionHandler(timerExceptionHandler);

        timer.handle(42L);

        verify(timerExceptionHandler).handleException(expectedException);
    }
}
