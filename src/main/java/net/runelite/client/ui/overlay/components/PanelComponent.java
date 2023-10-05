/*
 * Copyright (c) 2017, Tomas Slusny <slusnucky@gmail.com>
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

import net.runelite.client.ui.overlay.components.table.TableComponent;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanelComponent implements LayoutableRenderableEntity
{
    @Nullable
    public Color getBackgroundColor() {
        return this.backgroundColor;
    }

    public Dimension getPreferredSize() {
        return this.preferredSize;
    }

    public List<LayoutableRenderableEntity> getChildren() {
        return this.children;
    }

    public void setBackgroundColor(@Nullable Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setPreferredLocation(Point preferredLocation) {
        this.preferredLocation = preferredLocation;
    }

    public void setPreferredSize(Dimension preferredSize) {
        this.preferredSize = preferredSize;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public void setWrapping(int wrapping) {
        this.wrapping = wrapping;
    }

    public void setBorder(Rectangle border) {
        this.border = border;
    }

    public void setGap(Point gap) {
        this.gap = gap;
    }

    public enum Orientation
	{
		HORIZONTAL,
		VERTICAL;
	}

	@Nullable
    private Color backgroundColor = ComponentConstants.STANDARD_BACKGROUND_COLOR;

	private Point preferredLocation = new Point();

	private Dimension preferredSize = new Dimension(ComponentConstants.STANDARD_WIDTH, 0);

	private List<LayoutableRenderableEntity> children = new ArrayList<>();

	private Orientation orientation = Orientation.VERTICAL;

	private int wrapping = -1;

	private Rectangle border = new Rectangle(
		ComponentConstants.STANDARD_BORDER,
		ComponentConstants.STANDARD_BORDER,
		ComponentConstants.STANDARD_BORDER,
		ComponentConstants.STANDARD_BORDER);

	private Point gap = new Point(0, 0);

	private final Dimension childDimensions = new Dimension();

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (children.isEmpty())
		{
			return null;
		}
		children.removeIf(child -> child instanceof TableComponent && ((TableComponent) child).isEmpty());

		graphics.translate(preferredLocation.x, preferredLocation.y);

		// Calculate panel dimension
		final Dimension dimension = new Dimension(
			border.x + childDimensions.width + border.width,
			border.y + childDimensions.height + border.height);

		// Render background
		if (backgroundColor != null)
		{
			final BackgroundComponent backgroundComponent = new BackgroundComponent();
			backgroundComponent.setRectangle(new Rectangle(dimension));
			backgroundComponent.setBackgroundColor(backgroundColor);
			backgroundComponent.render(graphics);
		}

		// Offset children
		final int baseX = border.x;
		final int baseY = border.y;
		int width = 0;
		int height = 0;
		int x = baseX;
		int y = baseY;

		// Create child preferred size
		final Dimension childPreferredSize = new Dimension(
			preferredSize.width - border.x - border.width,
			preferredSize.height - border.y - border.height);

		// Calculate max width/height for infoboxes
		int totalHeight = 0;
		int totalWidth = 0;

		// Render all children
		for (int i = 0; i < children.size(); i ++)
		{
			final LayoutableRenderableEntity child = children.get(i);
			child.setPreferredLocation(new Point(x, y));
			child.setPreferredSize(childPreferredSize);
			final Dimension childDimension = child.render(graphics);

			switch (orientation)
			{
				case VERTICAL:
					height += childDimension.height + gap.y;
					y = baseY + height;
					width = Math.max(width, childDimension.width);
					break;
				case HORIZONTAL:
					width += childDimension.width + gap.x;
					x = baseX + width;
					height = Math.max(height, childDimension.height);
					break;
			}

			// Calculate total size
			totalWidth = Math.max(totalWidth, width);
			totalHeight = Math.max(totalHeight, height);

			if (wrapping > 0 && i < children.size() - 1 && (i + 1)  % wrapping == 0)
			{
				switch (orientation)
				{
					case VERTICAL:
					{
						height = 0;
						y = baseY;
						int diff = childDimension.width + gap.x;
						x += diff;
						width += diff;
						break;
					}
					case HORIZONTAL:
					{
						width = 0;
						x = baseX;
						int diff = childDimension.height + gap.y;
						y += diff;
						height += diff;
						break;
					}
				}
			}
		}

		// Remove last child gap
		totalWidth -= gap.x;
		totalHeight -= gap.y;

		// Cache children bounds
		childDimensions.setSize(totalWidth, totalHeight);

		graphics.translate(-preferredLocation.x, -preferredLocation.y);
		return dimension;
	}
}
