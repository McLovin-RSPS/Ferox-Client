package net.runelite.client.util.ping;

import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;

public class Timeval extends Structure
{
    public long tv_sec;
    public long tv_usec;

    @Override
    protected List<String> getFieldOrder()
    {
        return Arrays.asList("tv_sec", "tv_usec");
    }
}
