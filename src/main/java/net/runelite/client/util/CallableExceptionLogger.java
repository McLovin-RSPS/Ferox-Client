package net.runelite.client.util;

import org.slf4j.Logger;

import java.util.concurrent.Callable;

public class CallableExceptionLogger<V> implements Callable<V>
{
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(CallableExceptionLogger.class);
    private final Callable<V> callable;

    public CallableExceptionLogger(Callable<V> callable) {
        this.callable = callable;
    }

    @Override
    public V call() throws Exception
    {
        try
        {
            return callable.call();
        }
        catch (Throwable ex)
        {
            log.warn("Uncaught exception in callable {}", callable, ex);
            throw ex;
        }
    }

    public static <V> CallableExceptionLogger<V> wrap(Callable<V> callable)
    {
        return new CallableExceptionLogger<>(callable);
    }
}