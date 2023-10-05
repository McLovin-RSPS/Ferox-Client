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
package net.runelite.client.ui.overlay.components;

import net.runelite.client.ui.FontManager;

import java.awt.*;

public class InfoBoxComponent implements LayoutableRenderableEntity
{
	private static final int SEPARATOR = 3;
	private static final int DEFAULT_SIZE = 32;

	private String tooltip;

	private Point preferredLocation = new Point();

	private Dimension preferredSize = new Dimension(DEFAULT_SIZE, DEFAULT_SIZE);

	private String text;
	private Color color = Color.WHITE;
	private Color backgroundColor = ComponentConstants.STANDARD_BACKGROUND_COLOR;
	private Image image;

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (image == null)
		{
			return new Dimension();
		}

		graphics.setFont(getSize() < DEFAULT_SIZE ? FontManager.getRunescapeSmallFont() : FontManager.getRunescapeFont());
		graphics.translate(preferredLocation.x, preferredLocation.y);

		// Calculate dimensions
		final FontMetrics metrics = graphics.getFontMetrics();
		final int size = getSize();
		final Rectangle bounds = new Rectangle(size, size);

		// Render background
		final BackgroundComponent backgroundComponent = new BackgroundComponent();
		backgroundComponent.setBackgroundColor(backgroundColor);
		backgroundComponent.setRectangle(bounds);
		backgroundComponent.render(graphics);

		// Render image
		graphics.drawImage(
			image,
			(size - image.getWidth(null)) / 2,
			(size - image.getHeight(null)) / 2,
			null);

		// Render caption
		final TextComponent textComponent = new TextComponent();
		textComponent.setColor(color);
		textComponent.setText(text);
		textComponent.setPosition(new Point(((size - metrics.stringWidth(text)) / 2), size - SEPARATOR));
		textComponent.render(graphics);

		graphics.translate(-preferredLocation.x, -preferredLocation.y);
		return bounds.getSize();
	}

	public Dimension getPreferredSize()
	{
		return new Dimension(getSize(), getSize());
	}

	private int getSize()
	{
		return Math.max(preferredSize.width, preferredSize.height);
	}

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    public void setPreferredLocation(Point preferredLocation) {
        this.preferredLocation = preferredLocation;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getTooltip() {
        return this.tooltip;
    }

    public Point getPreferredLocation() {
        return this.preferredLocation;
    }

    public void setPreferredSize(Dimension preferredSize) {
        this.preferredSize = preferredSize;
    }
}
