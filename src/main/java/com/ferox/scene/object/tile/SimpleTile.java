package com.ferox.scene.object.tile;

import net.runelite.api.SceneTilePaint;

public final class SimpleTile implements SceneTilePaint {

    public SimpleTile(int hsl_a, int hsl_b, int hsl_c, int hsl_d, int texture_id, int color_id, boolean flat)
    {
        this.swColor = hsl_a;
        this.seColor = hsl_b;
        this.neColor = hsl_c;
        this.nwColor = hsl_d;
        this.texture = texture_id;
        this.colorRGB = color_id;
        this.flat = flat;
    }

    public final int swColor;
    public final int seColor;
    public final int neColor;
    public final int nwColor;
    public final int texture;
    public boolean flat;
    public final int colorRGB;

    @Override
    public int getRBG() {
        return colorRGB;
    }

    @Override
    public int getNorthEastColor() {
        return swColor;
    }

    @Override
    public int getNorthColor() {
        return seColor;
    }

    @Override
    public int getEastColor() {
        return nwColor;
    }

    @Override
    public int getCenterColor() {
        return neColor;
    }

    @Override
    public int getTexture() {
        return texture;
    }
    private int bufferOffset, uvBufferOffset, bufferLen;
    @Override
    public int getBufferOffset() {
        return bufferOffset;
    }

    @Override
    public void setBufferOffset(int bufferOffset) {
        this.bufferOffset = bufferOffset;
    }

    @Override
    public int getUvBufferOffset() {
        return uvBufferOffset;
    }

    @Override
    public void setUvBufferOffset(int bufferOffset) {
        this.uvBufferOffset = bufferOffset;
    }

    @Override
    public int getBufferLen() {
        return bufferLen;
    }

    @Override
    public void setBufferLen(int bufferLen) {
        this.bufferLen = bufferLen;
    }
}
