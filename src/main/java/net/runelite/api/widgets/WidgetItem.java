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
package net.runelite.api.widgets;

import net.runelite.api.Point;

import java.awt.*;

/**
 * An item that is being represented in a {@link Widget}.
 */
public final class WidgetItem
{
	private final int id;
	private final int quantity;
	private final int index;
	private final Rectangle canvasBounds;
	private final Widget widget;

    public WidgetItem(int id, int quantity, int index, Rectangle canvasBounds, Widget widget) {
        this.id = id;
        this.quantity = quantity;
        this.index = index;
        this.canvasBounds = canvasBounds;
        this.widget = widget;
    }

    public Point getCanvasLocation()
	{
		return new Point((int) canvasBounds.getX(), (int) canvasBounds.getY());
	}

    public int getId() {
        return this.id;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getIndex() {
        return this.index;
    }

    public Rectangle getCanvasBounds() {
        return this.canvasBounds;
    }

    public Widget getWidget() {
        return this.widget;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof WidgetItem)) return false;
        final WidgetItem other = (WidgetItem) o;
        if (this.getId() != other.getId()) return false;
        if (this.getQuantity() != other.getQuantity()) return false;
        if (this.getIndex() != other.getIndex()) return false;
        final Object this$canvasBounds = this.getCanvasBounds();
        final Object other$canvasBounds = other.getCanvasBounds();
        if (this$canvasBounds == null ? other$canvasBounds != null : !this$canvasBounds.equals(other$canvasBounds))
            return false;
        final Object this$widget = this.getWidget();
        final Object other$widget = other.getWidget();
        if (this$widget == null ? other$widget != null : !this$widget.equals(other$widget)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        result = result * PRIME + this.getQuantity();
        result = result * PRIME + this.getIndex();
        final Object $canvasBounds = this.getCanvasBounds();
        result = result * PRIME + ($canvasBounds == null ? 43 : $canvasBounds.hashCode());
        final Object $widget = this.getWidget();
        result = result * PRIME + ($widget == null ? 43 : $widget.hashCode());
        return result;
    }

    public String toString() {
        return "WidgetItem(id=" + this.getId() + ", quantity=" + this.getQuantity() + ", index=" + this.getIndex() + ", canvasBounds=" + this.getCanvasBounds() + ", widget=" + this.getWidget() + ")";
    }
}
