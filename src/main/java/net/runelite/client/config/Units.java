package net.runelite.client.config;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Units
{
    String MILLISECONDS = "ms";
    String MINUTES = " mins";
    String PERCENT = "%";
    String PIXELS = "px";
    String SECONDS = "s";
    String TICKS = " ticks";
    String DEGREES = "°";

    String value();
}
