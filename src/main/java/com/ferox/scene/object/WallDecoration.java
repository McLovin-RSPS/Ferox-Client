package com.ferox.scene.object;

import com.ferox.Client;
import com.ferox.entity.Renderable;
import net.runelite.api.DecorativeObject;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;

import java.awt.*;
import java.awt.geom.Area;

public final class WallDecoration implements DecorativeObject {

    public int plane;
    public int world_x;
    public int world_y;
    public int config_mask;
    public int orientation;
    public Renderable node;
    public long uid;
    public byte mask;

    @Override
    public Polygon getConvexHull() {
        return null;
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
        return this.node;
    }
}
