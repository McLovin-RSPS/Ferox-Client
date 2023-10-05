package net.runelite.client.util;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectUtil
{
    private ReflectUtil()
    {
    }

    public static MethodHandles.Lookup privateLookupIn(Class clazz)
    {
        try
        {
            // Java 9+ has privateLookupIn method on MethodHandles, but since we are shipping and using Java 8
            // we need to access it via reflection. This is preferred way because it's Java 9+ public api and is
            // likely to not change
            final Method privateLookupIn = MethodHandles.class.getMethod("privateLookupIn", Class.class, MethodHandles.Lookup.class);
            return (MethodHandles.Lookup) privateLookupIn.invoke(null, clazz, MethodHandles.lookup());
        }
        catch (InvocationTargetException | IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }
        catch (NoSuchMethodException e)
        {
            try
            {
                // In Java 8 we first do standard lookupIn class
                final MethodHandles.Lookup lookupIn = MethodHandles.lookup().in(clazz);

                // and then we mark it as trusted for private lookup via reflection on private field
                final Field modes = MethodHandles.Lookup.class.getDeclaredField("allowedModes");
                modes.setAccessible(true);
                modes.setInt(lookupIn, -1); // -1 == TRUSTED
                return lookupIn;
            }
            catch (ReflectiveOperationException ex)
            {
                throw new RuntimeException(ex);
            }
        }
    }
}
