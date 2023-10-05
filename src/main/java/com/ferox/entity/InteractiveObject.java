package com.ferox.entity;

import com.ferox.Client;
import net.runelite.api.GameObject;
import net.runelite.api.Point;
import net.runelite.api.coords.Angle;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;

import java.awt.*;
import java.awt.geom.Area;

/**
 * ObjectGenre = 2
 */
public final class InteractiveObject implements GameObject {

    public int plane;
    public int world_z;
    public int world_x;
    public int world_y;
    public Renderable node;
    public int orientation;
    public int xLocLow;
    public int xLocHigh;
    public int yLocHigh;
    public int yLocLow;
    public int camera_distance;//TODO - zooming in and out scales the value, up/down do not
    public int rendered;
    public long uid;
    /**
     * mask = (byte)((objectRotation << 6) + objectType);
     */
    public byte mask;

    @Override
    public Point getSceneMinLocation() {
        return new Point(this.getRelativeX(), this.getRelativeY());
    }

    @Override
    public Point getSceneMaxLocation() {
        return new Point(this.getOffsetX(), this.getOffsetY());
    }
    public int getRelativeX() {
        return this.xLocLow;
    }

    public int getRelativeY() {
        return this.yLocLow;
    }

    public int getOffsetX() {
        return this.xLocHigh;
    }

    public int getOffsetY() {
        return this.yLocHigh;
    }
    @Override
    public int getRsOrientation() {
        return orientation;
    }

    @Override
    public Polygon getConvexHull() {
        return null;
    }

    @Override
    public Angle getOrientation() {
        int orientation = this.getRsOrientation();
        int face = this.mask >> 6 & 3;
        return new Angle(orientation + face * 512);
    }

    @Override
    public int sizeX() {
        return xLocHigh - xLocLow;
    }

    @Override
    public int sizeY() {
        return yLocHigh - yLocLow;
    }

    @Override
    public long getHash() {
        return uid;
    }

    @Override
    public int getX() {
        return world_x;
    }

    @Override
    public int getY() {
        return world_y;
    }

    @Override
    public int getPlane() {
        return plane;
    }

    @Override
    public int getId() {
        return (int) (uid >>> 32) & 0x7fffffff;
    }

    public WorldPoint getWorldLocation() {
        return WorldPoint.fromLocal(Client.instance, getLocalLocation());
    }

    public LocalPoint getLocalLocation() {
        return new LocalPoint(this.getX(), this.getY());
    }

    @Override
    public Point getCanvasLocation() {
        return null;
    }

    @Override
    public Point getCanvasLocation(int zOffset) {
        return null;
    }

    @Override
    public Polygon getCanvasTilePoly() {
        return null;
    }

    @Override
    public Point getCanvasTextLocation(Graphics2D graphics, String text, int zOffset) {
        return null;
    }

    @Override
    public Point getMinimapLocation() {
        return null;
    }

    @Override
    public Area getClickbox() {
        return null;
    }

    @Override
    public net.runelite.api.Renderable getRenderable() {
        return node;
    }
}
