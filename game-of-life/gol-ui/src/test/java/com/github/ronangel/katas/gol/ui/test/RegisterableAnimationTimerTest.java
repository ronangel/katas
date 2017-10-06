package com.github.ronangel.katas.gol.ui.test;

import com.github.ronangel.katas.gol.ui.RegisterableAnimationTimer;
import com.github.ronangel.katas.gol.ui.TimerHandler;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RegisterableAnimationTimerTest {

    @Test
    public void shouldCallRegisteredHandlerOnHandle() {

        TimerHandler handler = mock(TimerHandler.class);

        RegisterableAnimationTimer timer = new RegisterableAnimationTimer();
        timer.registerHandler(handler);

        timer.handle(42L);

        verify(handler).handle(42L);
    }
}
