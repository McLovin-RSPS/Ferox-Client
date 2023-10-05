package com.ferox.draw;
import net.runelite.client.plugins.hdosrs.HdPlugin;
import net.runelite.rs.api.RSBufferProvider;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.DirectColorModel;
import java.awt.image.WritableRaster;
import java.util.Hashtable;

public final class ProducingGraphicsBuffer implements RSBufferProvider {

    public final int[] canvasRaster;
    public final int canvasWidth;
    public final int canvasHeight;
    private float[] depthbuffer;
    private final BufferedImage bufferedImage;

    public ProducingGraphicsBuffer(int w, int h) {
        canvasWidth = w;
        canvasHeight = h;
        canvasRaster = new int[w * h + 1];
        depthbuffer = new float[canvasWidth * canvasHeight];
        DataBufferInt data = new DataBufferInt(canvasRaster, canvasRaster.length);
        DirectColorModel model;
        final boolean gpu = HdPlugin.process();
        if (gpu) {
            model = new DirectColorModel(ColorSpace.getInstance(1000), 32, 16711680, 65280, 255, -16777216, true, 3);
        } else {
            model = new DirectColorModel(32, 16711680, 65280, 255);
        }
        WritableRaster raster = java.awt.image.Raster.createWritableRaster(model.createCompatibleSampleModel(canvasWidth, canvasHeight), data, null);
        bufferedImage = new BufferedImage(model, raster, gpu, new Hashtable());
        init();
    }

    public void drawGraphics(int x, Graphics graphics, int y) {
        if(HdPlugin.process())
            return;
        graphics.drawImage(bufferedImage, y, x, null);
    }

    public void init() {
        Rasterizer2D.init(canvasWidth, canvasHeight, canvasRaster, depthbuffer);
    }

    @Override
    public Image getImage() {
        return bufferedImage;
    }

    @Override
    public void setRaster() {

    }

    @Override
    public int[] getRaster() {
        return canvasRaster;
    }

    @Override
    public int getWidth() {
        return canvasWidth;
    }

    @Override
    public int getHeight() {
        return canvasHeight;
    }
}
