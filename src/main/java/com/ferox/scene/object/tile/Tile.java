package com.ferox.scene.object.tile;
import com.ferox.Client;
import com.ferox.collection.Node;
import com.ferox.entity.GroundItem;
import com.ferox.scene.object.GroundDecoration;
import com.ferox.scene.object.Wall;
import com.ferox.scene.object.WallDecoration;
import com.ferox.entity.InteractiveObject;
import net.runelite.api.*;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;

import java.util.List;

public final class Tile extends Node implements net.runelite.api.Tile {
    public Tile(int i, int j, int k) {
        interactive_obj = new InteractiveObject[5];
        interactive_obj_size = new int[5];
        renderLevel = plane = i;
        x = j;
        y = k;
    }

    public int plane;
    public final int x;
    public final int y;
    public final int renderLevel;
    public SimpleTile simple;
    public ComplexTile complex;
    public Wall wall;
    public WallDecoration wall_decor;
    public GroundDecoration ground_decor;
    public GroundItem ground_item;
    public int occupants;
    public final InteractiveObject[] interactive_obj;
    public final int[] interactive_obj_size;
    public int origin_mask;
    public int logicHeight;
    public boolean updated;
    public boolean drawn;
    public boolean multiple_objects;
    public int render_mask;
    public int viewport_angle;
    public int culled_face_mask;
    public int depth;
    public Tile bridge;
    public boolean isBridge = false;

    @Override
    public DecorativeObject getDecorativeObject() {
        return wall_decor;
    }

    @Override
    public GameObject[] getObjects() {
        return this.interactive_obj;
    }

    @Override
    public ItemLayer getItemLayer() {
        return null;
    }

    @Override
    public GroundObject getGroundObject() {
        return ground_decor;
    }

    @Override
    public WallObject getWallObject() {
        return wall;
    }

    public WorldPoint getWorldLocation() {
        return WorldPoint.fromLocal(Client.instance, getLocalLocation());
    }

    public LocalPoint getLocalLocation() {
        return LocalPoint.fromScene(this.x, this.y);
    }

    @Override
    public int getPlane() {
        return renderLevel;
    }

    public Point getSceneLocation() {
        return new Point(this.x, this.y);
    }


    @Override
    public boolean hasLineOfSightTo(net.runelite.api.Tile other) {
        return false;
    }

    @Override
    public List<Item> getGroundItems() {
        return null;
    }

    @Override
    public net.runelite.api.Tile getBridge() {
        return bridge;
    }

    @Override
    public SceneTilePaint getSceneTilePaint() {
        return this.simple;
    }

    @Override
    public SceneTileModel getSceneTileModel() {
        return this.complex;
    }

    @Override
    public GameObject[] getGameObjects() {
        return interactive_obj;
    }

    @Override
    public int getRenderLevel() {
        return isBridge ? renderLevel + 1 : renderLevel;
    }
}
