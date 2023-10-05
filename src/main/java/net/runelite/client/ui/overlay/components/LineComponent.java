/*
 * Copyright (c) 2018, Tomas Slusny <slusnucky@gmail.com>
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

import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;

import java.awt.*;

public class LineComponent implements LayoutableRenderableEntity
{
	private String left;
	private String right;

	private Color leftColor = Color.WHITE;

	private Color rightColor = Color.WHITE;

	private Point preferredLocation = new Point();

	private Dimension preferredSize = new Dimension(ComponentConstants.STANDARD_WIDTH, 0);

    LineComponent(String left, String right, Color leftColor, Color rightColor, Point preferredLocation, Dimension preferredSize) {
        this.left = left;
        this.right = right;
        this.leftColor = leftColor;
        this.rightColor = rightColor;
        this.preferredLocation = preferredLocation;
        this.preferredSize = preferredSize;
    }

    private static Color $default$leftColor() {
        return Color.WHITE;
    }

    private static Color $default$rightColor() {
        return Color.WHITE;
    }

    private static Point $default$preferredLocation() {
        return new Point();
    }

    private static Dimension $default$preferredSize() {
        return new Dimension(ComponentConstants.STANDARD_WIDTH, 0);
    }

    public static LineComponentBuilder builder() {
        return new LineComponentBuilder();
    }

    @Override
	public Dimension render(Graphics2D graphics)
	{
		graphics.translate(preferredLocation.x, preferredLocation.y);
		// Prevent NPEs
		final String left = MoreObjects.firstNonNull(this.left, "");
		final String right = MoreObjects.firstNonNull(this.right, "");

		final FontMetrics metrics = graphics.getFontMetrics();
		int x = 0;
		int y = metrics.getHeight();
		final int leftFullWidth = getLineWidth(left, metrics);
		final int rightFullWidth = getLineWidth(right, metrics);

		if (preferredSize.width < leftFullWidth + rightFullWidth)
		{
			int leftSmallWidth = preferredSize.width;
			int rightSmallWidth = 0;

			if (!Strings.isNullOrEmpty(right))
			{
				rightSmallWidth = (preferredSize.width / 3);
				leftSmallWidth -= rightSmallWidth;
			}

			final String[] leftSplitLines = lineBreakText(left, leftSmallWidth, metrics);
			final String[] rightSplitLines = lineBreakText(right, rightSmallWidth, metrics);

			int lineCount = Math.max(leftSplitLines.length, rightSplitLines.length);

			for (int i = 0; i < lineCount; i++)
			{
				String leftText = "";
				String rightText = "";

				if (i < leftSplitLines.length)
				{
					leftText = leftSplitLines[i];
				}

				if (i < rightSplitLines.length)
				{
					rightText = rightSplitLines[i];
				}

				final TextComponent leftLineComponent = new TextComponent();
				leftLineComponent.setPosition(new Point(x, y));
				leftLineComponent.setText(leftText);
				leftLineComponent.setColor(leftColor);
				leftLineComponent.render(graphics);

				final TextComponent rightLineComponent = new TextComponent();
				rightLineComponent.setPosition(new Point(x + leftSmallWidth + rightSmallWidth - getLineWidth(rightText, metrics), y));
				rightLineComponent.setText(rightText);
				rightLineComponent.setColor(rightColor);
				rightLineComponent.render(graphics);
				y += metrics.getHeight();
			}

			graphics.translate(-preferredLocation.x, -preferredLocation.y);
			return new Dimension(preferredSize.width, y - metrics.getHeight());
		}

		final TextComponent leftLineComponent = new TextComponent();
		leftLineComponent.setPosition(new Point(x, y));
		leftLineComponent.setText(left);
		leftLineComponent.setColor(leftColor);
		leftLineComponent.render(graphics);

		final TextComponent rightLineComponent = new TextComponent();
		rightLineComponent.setPosition(new Point(x + preferredSize.width - getLineWidth(right, metrics), y));
		rightLineComponent.setText(right);
		rightLineComponent.setColor(rightColor);
		rightLineComponent.render(graphics);
		y += metrics.getHeight();

		graphics.translate(-preferredLocation.x, -preferredLocation.y);
		return new Dimension(preferredSize.width, y - metrics.getHeight());
	}

	private static int getLineWidth(final String line, final FontMetrics metrics)
	{
		return metrics.stringWidth(TextComponent.textWithoutColTags(line));
	}

	private static String[] lineBreakText(String text, int maxWidth, FontMetrics metrics)
	{
		final String[] words = text.split(" ");

		if (words.length == 0)
		{
			return new String[0];
		}

		final StringBuilder wrapped = new StringBuilder(words[0]);
		int spaceLeft = maxWidth - metrics.stringWidth(wrapped.toString());

		for (int i = 1; i < words.length; i++)
		{
			final String word = words[i];
			final int wordLen = metrics.stringWidth(word);
			final int spaceWidth = metrics.stringWidth(" ");

			if (wordLen + spaceWidth > spaceLeft)
			{
				wrapped.append("\n").append(word);
				spaceLeft = maxWidth - wordLen;
			}
			else
			{
				wrapped.append(" ").append(word);
				spaceLeft -= spaceWidth + wordLen;
			}
		}

		return wrapped.toString().split("\n");
	}

    public void setLeft(String left) {
        this.left = left;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public void setLeftColor(Color leftColor) {
        this.leftColor = leftColor;
    }

    public void setRightColor(Color rightColor) {
        this.rightColor = rightColor;
    }

    public void setPreferredLocation(Point preferredLocation) {
        this.preferredLocation = preferredLocation;
    }

    public void setPreferredSize(Dimension preferredSize) {
        this.preferredSize = preferredSize;
    }

    public static class LineComponentBuilder {
        private String left;
        private String right;
        private Color leftColor$value;
        private boolean leftColor$set;
        private Color rightColor$value;
        private boolean rightColor$set;
        private Point preferredLocation$value;
        private boolean preferredLocation$set;
        private Dimension preferredSize$value;
        private boolean preferredSize$set;

        LineComponentBuilder() {
        }

        public LineComponentBuilder left(String left) {
            this.left = left;
            return this;
        }

        public LineComponentBuilder right(String right) {
            this.right = right;
            return this;
        }

        public LineComponentBuilder leftColor(Color leftColor) {
            this.leftColor$value = leftColor;
            this.leftColor$set = true;
            return this;
        }

        public LineComponentBuilder rightColor(Color rightColor) {
            this.rightColor$value = rightColor;
            this.rightColor$set = true;
            return this;
        }

        public LineComponentBuilder preferredLocation(Point preferredLocation) {
            this.preferredLocation$value = preferredLocation;
            this.preferredLocation$set = true;
            return this;
        }

        public LineComponentBuilder preferredSize(Dimension preferredSize) {
            this.preferredSize$value = preferredSize;
            this.preferredSize$set = true;
            return this;
        }

        public LineComponent build() {
            Color leftColor$value = this.leftColor$value;
            if (!this.leftColor$set) {
                leftColor$value = LineComponent.$default$leftColor();
            }
            Color rightColor$value = this.rightColor$value;
            if (!this.rightColor$set) {
                rightColor$value = LineComponent.$default$rightColor();
            }
            Point preferredLocation$value = this.preferredLocation$value;
            if (!this.preferredLocation$set) {
                preferredLocation$value = LineComponent.$default$preferredLocation();
            }
            Dimension preferredSize$value = this.preferredSize$value;
            if (!this.preferredSize$set) {
                preferredSize$value = LineComponent.$default$preferredSize();
            }
            return new LineComponent(left, right, leftColor$value, rightColor$value, preferredLocation$value, preferredSize$value);
        }

        public String toString() {
            return "LineComponent.LineComponentBuilder(left=" + this.left + ", right=" + this.right + ", leftColor$value=" + this.leftColor$value + ", rightColor$value=" + this.rightColor$value + ", preferredLocation$value=" + this.preferredLocation$value + ", preferredSize$value=" + this.preferredSize$value + ")";
        }
    }
}

