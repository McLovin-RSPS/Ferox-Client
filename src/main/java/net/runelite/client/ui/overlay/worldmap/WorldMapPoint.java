/*
 * Copyright (c) 2018, Morgan Lewis <https://github.com/MESLewis>
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
package net.runelite.client.ui.overlay.worldmap;

import net.runelite.api.Point;
import net.runelite.api.coords.WorldPoint;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class WorldMapPoint
{
	private BufferedImage image;

	private WorldPoint worldPoint;

	/**
	 * The point on the image that will be drawn at WorldPoint
	 * WorldMapPointManager will center the image if imagePoint is null
	 */
	private Point imagePoint;

	private Rectangle clickbox;

	private boolean snapToEdge;

	private boolean currentlyEdgeSnapped;

	/**
	 * Whether or not the map jumps to worldPoint when the overlay is clicked
	 */
	private boolean jumpOnClick;

	private boolean tooltipVisible;

	private String tooltip;

	public WorldMapPoint(WorldPoint worldPoint, BufferedImage image)
	{
		this.worldPoint = worldPoint;
		this.image = image;
	}

	public MouseEvent onClick(MouseEvent e)
	{
		return e;
	}

	public void onEdgeSnap()
	{
	}

	public void onEdgeUnsnap()
	{
	}

    public BufferedImage getImage() {
        return this.image;
    }

    public WorldPoint getWorldPoint() {
        return this.worldPoint;
    }

    public Point getImagePoint() {
        return this.imagePoint;
    }

    public Rectangle getClickbox() {
        return this.clickbox;
    }

    public boolean isSnapToEdge() {
        return this.snapToEdge;
    }

    public boolean isCurrentlyEdgeSnapped() {
        return this.currentlyEdgeSnapped;
    }

    public boolean isJumpOnClick() {
        return this.jumpOnClick;
    }

    public boolean isTooltipVisible() {
        return this.tooltipVisible;
    }

    public String getTooltip() {
        return this.tooltip;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setWorldPoint(WorldPoint worldPoint) {
        this.worldPoint = worldPoint;
    }

    public void setImagePoint(Point imagePoint) {
        this.imagePoint = imagePoint;
    }

    public void setClickbox(Rectangle clickbox) {
        this.clickbox = clickbox;
    }

    public void setSnapToEdge(boolean snapToEdge) {
        this.snapToEdge = snapToEdge;
    }

    public void setCurrentlyEdgeSnapped(boolean currentlyEdgeSnapped) {
        this.currentlyEdgeSnapped = currentlyEdgeSnapped;
    }

    public void setJumpOnClick(boolean jumpOnClick) {
        this.jumpOnClick = jumpOnClick;
    }

    public void setTooltipVisible(boolean tooltipVisible) {
        this.tooltipVisible = tooltipVisible;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof WorldMapPoint)) return false;
        final WorldMapPoint other = (WorldMapPoint) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$image = this.getImage();
        final Object other$image = other.getImage();
        if (this$image == null ? other$image != null : !this$image.equals(other$image)) return false;
        final Object this$worldPoint = this.getWorldPoint();
        final Object other$worldPoint = other.getWorldPoint();
        if (this$worldPoint == null ? other$worldPoint != null : !this$worldPoint.equals(other$worldPoint))
            return false;
        final Object this$imagePoint = this.getImagePoint();
        final Object other$imagePoint = other.getImagePoint();
        if (this$imagePoint == null ? other$imagePoint != null : !this$imagePoint.equals(other$imagePoint))
            return false;
        final Object this$clickbox = this.getClickbox();
        final Object other$clickbox = other.getClickbox();
        if (this$clickbox == null ? other$clickbox != null : !this$clickbox.equals(other$clickbox)) return false;
        if (this.isSnapToEdge() != other.isSnapToEdge()) return false;
        if (this.isCurrentlyEdgeSnapped() != other.isCurrentlyEdgeSnapped()) return false;
        if (this.isJumpOnClick() != other.isJumpOnClick()) return false;
        if (this.isTooltipVisible() != other.isTooltipVisible()) return false;
        final Object this$tooltip = this.getTooltip();
        final Object other$tooltip = other.getTooltip();
        if (this$tooltip == null ? other$tooltip != null : !this$tooltip.equals(other$tooltip)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof WorldMapPoint;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $image = this.getImage();
        result = result * PRIME + ($image == null ? 43 : $image.hashCode());
        final Object $worldPoint = this.getWorldPoint();
        result = result * PRIME + ($worldPoint == null ? 43 : $worldPoint.hashCode());
        final Object $imagePoint = this.getImagePoint();
        result = result * PRIME + ($imagePoint == null ? 43 : $imagePoint.hashCode());
        final Object $clickbox = this.getClickbox();
        result = result * PRIME + ($clickbox == null ? 43 : $clickbox.hashCode());
        result = result * PRIME + (this.isSnapToEdge() ? 79 : 97);
        result = result * PRIME + (this.isCurrentlyEdgeSnapped() ? 79 : 97);
        result = result * PRIME + (this.isJumpOnClick() ? 79 : 97);
        result = result * PRIME + (this.isTooltipVisible() ? 79 : 97);
        final Object $tooltip = this.getTooltip();
        result = result * PRIME + ($tooltip == null ? 43 : $tooltip.hashCode());
        return result;
    }

    public String toString() {
        return "WorldMapPoint(image=" + this.getImage() + ", worldPoint=" + this.getWorldPoint() + ", imagePoint=" + this.getImagePoint() + ", clickbox=" + this.getClickbox() + ", snapToEdge=" + this.isSnapToEdge() + ", currentlyEdgeSnapped=" + this.isCurrentlyEdgeSnapped() + ", jumpOnClick=" + this.isJumpOnClick() + ", tooltipVisible=" + this.isTooltipVisible() + ", tooltip=" + this.getTooltip() + ")";
    }
}
