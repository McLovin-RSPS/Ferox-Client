/*
 * Copyright (c) 2017, Adam <Adam@sigterm.info>
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
package net.runelite.client.ui.overlay.infobox;

import net.runelite.client.plugins.Plugin;

import java.awt.*;

public abstract class InfoBox
{
	private final Plugin plugin;

	private Image image;

	private Image scaledImage;

	private InfoBoxPriority priority;

	private String tooltip;

	public InfoBox(Image image, Plugin plugin)
	{
		this.plugin = plugin;
		setImage(image);
		setPriority(InfoBoxPriority.NONE);
	}

	public abstract String getText();

	public abstract Color getTextColor();

	public boolean render()
	{
		return true;
	}

	public boolean cull()
	{
		return false;
	}

    public Plugin getPlugin() {
        return this.plugin;
    }

    public Image getImage() {
        return this.image;
    }

    public Image getScaledImage() {
        return this.scaledImage;
    }

    public InfoBoxPriority getPriority() {
        return this.priority;
    }

    public String getTooltip() {
        return this.tooltip;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setScaledImage(Image scaledImage) {
        this.scaledImage = scaledImage;
    }

    public void setPriority(InfoBoxPriority priority) {
        this.priority = priority;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }
}
