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

import java.awt.*;

public class TitleComponent implements LayoutableRenderableEntity
{
	private String text;

	private Color color = Color.WHITE;

	private Point preferredLocation = new Point();

	private Dimension preferredSize = new Dimension(ComponentConstants.STANDARD_WIDTH, 0);

    TitleComponent(String text, Color color, Point preferredLocation, Dimension preferredSize) {
        this.text = text;
        this.color = color;
        this.preferredLocation = preferredLocation;
        this.preferredSize = preferredSize;
    }

    private static Color $default$color() {
        return Color.WHITE;
    }

    private static Point $default$preferredLocation() {
        return new Point();
    }

    private static Dimension $default$preferredSize() {
        return new Dimension(ComponentConstants.STANDARD_WIDTH, 0);
    }

    public static TitleComponentBuilder builder() {
        return new TitleComponentBuilder();
    }

    @Override
	public Dimension render(Graphics2D graphics)
	{
		graphics.translate(preferredLocation.x, preferredLocation.y);
		final FontMetrics metrics = graphics.getFontMetrics();
		final TextComponent titleComponent = new TextComponent();
		titleComponent.setText(text);
		titleComponent.setColor(color);
		titleComponent.setPosition(new Point((preferredSize.width - metrics.stringWidth(text)) / 2, metrics.getHeight()));
		final Dimension dimension = titleComponent.render(graphics);
		graphics.translate(-preferredLocation.x, -preferredLocation.y);
		return new Dimension(preferredSize.width, dimension.height);
	}

    public void setText(String text) {
        this.text = text;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setPreferredLocation(Point preferredLocation) {
        this.preferredLocation = preferredLocation;
    }

    public void setPreferredSize(Dimension preferredSize) {
        this.preferredSize = preferredSize;
    }

    public static class TitleComponentBuilder {
        private String text;
        private Color color$value;
        private boolean color$set;
        private Point preferredLocation$value;
        private boolean preferredLocation$set;
        private Dimension preferredSize$value;
        private boolean preferredSize$set;

        TitleComponentBuilder() {
        }

        public TitleComponentBuilder text(String text) {
            this.text = text;
            return this;
        }

        public TitleComponentBuilder color(Color color) {
            this.color$value = color;
            this.color$set = true;
            return this;
        }

        public TitleComponentBuilder preferredLocation(Point preferredLocation) {
            this.preferredLocation$value = preferredLocation;
            this.preferredLocation$set = true;
            return this;
        }

        public TitleComponentBuilder preferredSize(Dimension preferredSize) {
            this.preferredSize$value = preferredSize;
            this.preferredSize$set = true;
            return this;
        }

        public TitleComponent build() {
            Color color$value = this.color$value;
            if (!this.color$set) {
                color$value = TitleComponent.$default$color();
            }
            Point preferredLocation$value = this.preferredLocation$value;
            if (!this.preferredLocation$set) {
                preferredLocation$value = TitleComponent.$default$preferredLocation();
            }
            Dimension preferredSize$value = this.preferredSize$value;
            if (!this.preferredSize$set) {
                preferredSize$value = TitleComponent.$default$preferredSize();
            }
            return new TitleComponent(text, color$value, preferredLocation$value, preferredSize$value);
        }

        public String toString() {
            return "TitleComponent.TitleComponentBuilder(text=" + this.text + ", color$value=" + this.color$value + ", preferredLocation$value=" + this.preferredLocation$value + ", preferredSize$value=" + this.preferredSize$value + ")";
        }
    }
}
