package com.ferox;

import com.ferox.draw.Rasterizer3D;
import net.runelite.api.Texture;
import net.runelite.rs.api.RSTextureProvider;

public class TextureManager implements RSTextureProvider {
    @Override
    public double getBrightness() {
        return Rasterizer3D.bright;
    }

    @Override
    public void setBrightness(double brightness) {
        Rasterizer3D.adjust_brightness(brightness);
    }

    @Override
    public Texture[] getTextures() {
        return Rasterizer3D.tex_images;
    }

    @Override
    public int[] load(int textureId) {
        return Rasterizer3D.get_texels(textureId)[0];
    }

    @Override
    public void checkTextures(int var1) {

    }
}
