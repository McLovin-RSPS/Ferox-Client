/*
 * Copyright (c) 2016-2017, Adam <Adam@sigterm.info>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.ui.overlay;

import net.runelite.api.GraphicsBufferType;
import net.runelite.client.ui.overlay.components.LayoutableRenderableEntity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Overlay implements LayoutableRenderableEntity
{
	private Point preferredLocation;
	private Dimension preferredSize;
	private OverlayPosition preferredPosition;
	private Rectangle bounds = new Rectangle();
	private OverlayPosition position = OverlayPosition.TOP_LEFT;
	private OverlayPriority priority = OverlayPriority.NONE;
	private OverlayLayer layer = OverlayLayer.UNDER_WIDGETS;
	private List<GraphicsBufferType> bufferType = new ArrayList<>();
	private boolean resizable;
	private boolean resettable = true;

	public void addGraphicsBufferType(GraphicsBufferType type) {
		bufferType.add(type);
	}
	/**
	 * Overlay name, used for saving the overlay, needs to be unique
	 * @return overlay name
	 */
	public String getName()
	{
		return this.getClass().getSimpleName();
	}

    public Point getPreferredLocation() {
        return this.preferredLocation;
    }

    public Dimension getPreferredSize() {
        return this.preferredSize;
    }

    public OverlayPosition getPreferredPosition() {
        return this.preferredPosition;
    }

    public Rectangle getBounds() {
        return this.bounds;
    }

    public OverlayPosition getPosition() {
        return this.position;
    }

    public OverlayPriority getPriority() {
        return this.priority;
    }

    public OverlayLayer getLayer() {
        return this.layer;
    }

    public List<GraphicsBufferType> getBufferType() {
        return this.bufferType;
    }

    public boolean isResizable() {
        return this.resizable;
    }

    public boolean isResettable() {
        return this.resettable;
    }

    public void setPreferredLocation(Point preferredLocation) {
        this.preferredLocation = preferredLocation;
    }

    public void setPreferredSize(Dimension preferredSize) {
        this.preferredSize = preferredSize;
    }

    public void setPreferredPosition(OverlayPosition preferredPosition) {
        this.preferredPosition = preferredPosition;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public void setPosition(OverlayPosition position) {
        this.position = position;
    }

    public void setPriority(OverlayPriority priority) {
        this.priority = priority;
    }

    public void setLayer(OverlayLayer layer) {
        this.layer = layer;
    }

    public void setBufferType(List<GraphicsBufferType> bufferType) {
        this.bufferType = bufferType;
    }

    public void setResizable(boolean resizable) {
        this.resizable = resizable;
    }

    public void setResettable(boolean resettable) {
        this.resettable = resettable;
    }
}
