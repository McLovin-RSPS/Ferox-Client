package net.runelite.api.hooks;

import net.runelite.api.*;
import net.runelite.api.events.GameStateChanged;

public interface DrawCallbacks
{
    void draw(Renderable renderable, int orientation, int pitchSin, int pitchCos, int yawSin, int yawCos, int x, int y, int z, long hash);

    void drawScenePaint(int orientation, int pitchSin, int pitchCos, int yawSin, int yawCos, int x, int y, int z,
                        SceneTilePaint paint, int tileZ, int tileX, int tileY,
                        int zoom, int centerX, int centerY);


    void drawSceneModel(int orientation, int pitchSin, int pitchCos, int yawSin, int yawCos, int x, int y, int z,
                        SceneTileModel model, int tileZ, int tileX, int tileY,
                        int zoom, int centerX, int centerY);

    /**
     * Called when a frame should be drawn.
     *
     * @param overlayColor Color of full-viewport overlays, if any
     */
    void draw(int overlayColor);

    boolean drawFace(Model model, int face);

    /**
     * Called before the scene is drawn
     * @param cameraX
     * @param cameraY
     * @param cameraZ
     * @param cameraPitch
     * @param cameraYaw
     * @param plane
     */
    void drawScene(int cameraX, int cameraY, int cameraZ, int cameraPitch, int cameraYaw, int plane);
    void onGameStateChanged(GameStateChanged gameStateChanged);
    /**
     * Called after the scene has been drawn
     */
    void postDrawScene();

    void animate(Texture texture, int diff);
}
