package com.github.ronangel.katas.gol.test.util;

import static org.junit.Assert.*;

public class AssertHelper
{
    public static void assertThrows(Action action, Class<? extends Exception> exceptionType)
    {
        boolean thrown = false;
        try
        {
            action.doSomething();
        }
        catch (Throwable t)
        {
            assertTrue(exceptionType.isAssignableFrom(t.getClass()));

            thrown = true;
        }

        assertTrue("No exception thrown", thrown);
    }
}
