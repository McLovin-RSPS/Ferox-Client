package com.ferox.scene.object;

import com.ferox.Client;
import com.ferox.entity.Renderable;
import net.runelite.api.Perspective;
import net.runelite.api.Point;
import net.runelite.api.WallObject;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;

import java.awt.*;
import java.awt.geom.Area;

public final class Wall implements WallObject {

    public int plane;
    public int world_x;
    public int world_y;
    public int wall_orientation;
    public int corner_orientation;
    public Renderable wall;
    public Renderable corner;
    public long uid;
    public byte mask;

    @Override
    public long getHash() {
        return getHash();
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
        return Perspective.localToCanvas((net.runelite.api.Client)Client.instance, this.getLocalLocation(), this.getPlane());
    }

    @Override
    public Point getCanvasLocation(int zOffset) {
        return Perspective.localToCanvas((net.runelite.api.Client)Client.instance, this.getLocalLocation(), this.getPlane(), zOffset);
    }
    @Override
    public Polygon getCanvasTilePoly() {
        return Perspective.getCanvasTilePoly(Client.instance, this.getLocalLocation());
    }

    public Point getCanvasTextLocation(Graphics2D graphics, String text, int zOffset) {
        return Perspective.getCanvasTextLocation(Client.instance, graphics, getLocalLocation(), text, zOffset);
    }


    public Point getMinimapLocation() {
        return Perspective.localToMinimap(Client.instance, getLocalLocation());
    }

    @Override
    public Area getClickbox() {
        LocalPoint point = this.getLocalLocation();
        return Perspective.getClickbox(Client.instance, this.wall.get_rotated_model(), wall_orientation, point.getX(), point.getY());
    }

    @Override
    public net.runelite.api.Renderable getRenderable() {
        return wall;
    }

    @Override
    public int getOrientationA() {
        return wall_orientation;
    }

    @Override
    public int getOrientationB() {
        return corner_orientation;
    }

    @Override
    public int getConfig() {
        return 0;
    }

    @Override
    public void setBufferOffset(int bufferOffset) {

    }

    @Override
    public net.runelite.api.Renderable getRenderable1() {
        return wall;
    }

    @Override
    public net.runelite.api.Renderable getRenderable2() {
        return corner;
    }
}
