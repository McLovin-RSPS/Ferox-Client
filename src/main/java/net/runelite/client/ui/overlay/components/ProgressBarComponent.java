/*
 * Copyright (c) 2018, Cameron <moberg@tuta.io>
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
import java.text.DecimalFormat;

public class ProgressBarComponent implements LayoutableRenderableEntity
{
    public void setMinimum(long minimum) {
        this.minimum = minimum;
    }

    public void setMaximum(long maximum) {
        this.maximum = maximum;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setLabelDisplayMode(LabelDisplayMode labelDisplayMode) {
        this.labelDisplayMode = labelDisplayMode;
    }

    public void setLeftLabel(String leftLabel) {
        this.leftLabel = leftLabel;
    }

    public void setRightLabel(String rightLabel) {
        this.rightLabel = rightLabel;
    }

    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }

    public void setPreferredLocation(Point preferredLocation) {
        this.preferredLocation = preferredLocation;
    }

    public void setPreferredSize(Dimension preferredSize) {
        this.preferredSize = preferredSize;
    }

    public enum LabelDisplayMode
	{
		PERCENTAGE,
		FULL
	}

	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.0");
	private static final DecimalFormat DECIMAL_FORMAT_ABS = new DecimalFormat("#0");
	private long minimum;
	private long maximum = 100;
	private double value;
	private LabelDisplayMode labelDisplayMode = LabelDisplayMode.PERCENTAGE;
	private String leftLabel;
	private String rightLabel;
	private Color foregroundColor = new Color(82, 161, 82);
	private Color backgroundColor = new Color(255, 255, 255, 127);
	private Color fontColor = Color.WHITE;
	private Point preferredLocation = new Point();
	private Dimension preferredSize = new Dimension(ComponentConstants.STANDARD_WIDTH, 16);
	private static final int SIDE_LABEL_OFFSET = 4;

	@Override
	public Dimension render(Graphics2D graphics)
	{
		graphics.translate(preferredLocation.x, preferredLocation.y);
		final FontMetrics metrics = graphics.getFontMetrics();

		final int barX = 0;
		final int barY = 0;

		final long span = maximum - minimum;
		final double currentValue = value - minimum;
		final double pc = currentValue / span;
		final String textToWrite;

        if (labelDisplayMode == LabelDisplayMode.PERCENTAGE) {
            textToWrite = DECIMAL_FORMAT.format(pc * 100d) + "%";
        } else {
            textToWrite = DECIMAL_FORMAT_ABS.format(Math.floor(currentValue)) + "/" + maximum;
        }

		final int width = preferredSize.width;
		final int height = Math.max(preferredSize.height, 16);
		final int progressTextX = barX + (width - metrics.stringWidth(textToWrite)) / 2;
		final int progressTextY = barY + ((height - metrics.getHeight()) / 2) + metrics.getHeight();
		final int progressFill = (int) (width * pc);

		//Draw bar
		graphics.setColor(backgroundColor);
		graphics.fillRect(barX, barY, width, height);
		graphics.setColor(foregroundColor);
		graphics.fillRect(barX, barY, progressFill, height);

		final TextComponent textComponent = new TextComponent();
		textComponent.setPosition(new Point(progressTextX, progressTextY));
		textComponent.setColor(fontColor);
		textComponent.setText(textToWrite);
		textComponent.render(graphics);

		if (leftLabel != null)
		{
			final TextComponent leftTextComponent = new TextComponent();
			leftTextComponent.setPosition(new Point(barX + SIDE_LABEL_OFFSET, progressTextY));
			leftTextComponent.setColor(fontColor);
			leftTextComponent.setText(leftLabel);
			leftTextComponent.render(graphics);
		}

		if (rightLabel != null)
		{
			final TextComponent leftTextComponent = new TextComponent();
			leftTextComponent.setPosition(new Point(barX + width - metrics.stringWidth(rightLabel) - SIDE_LABEL_OFFSET, progressTextY));
			leftTextComponent.setColor(fontColor);
			leftTextComponent.setText(rightLabel);
			leftTextComponent.render(graphics);
		}

		graphics.translate(-preferredLocation.x, -preferredLocation.y);
		return new Dimension(width, height);
	}
}
