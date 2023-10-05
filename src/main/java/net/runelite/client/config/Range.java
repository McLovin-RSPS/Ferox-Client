package net.runelite.client.config;

import java.lang.annotation.*;

/**
 * Used with ConfigItem, describes valid int range for a config item.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Range
{
    int min() default 0;

    int max() default Integer.MAX_VALUE;

    boolean slider() default false;

    boolean wrapAround() default false;
}