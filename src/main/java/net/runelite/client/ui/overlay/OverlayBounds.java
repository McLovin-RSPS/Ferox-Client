/*
 * Copyright (c) 2018, Adam <Adam@sigterm.info>
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

import java.awt.*;
import java.util.Arrays;
import java.util.Collection;

import static net.runelite.client.ui.overlay.OverlayPosition.*;

final
class OverlayBounds
{
	private final Rectangle topLeft, topCenter, topRight, bottomLeft, bottomRight, aboveChatboxRight;

	OverlayBounds(OverlayBounds other)
	{
		topLeft = new Rectangle(other.topLeft);
		topCenter = new Rectangle(other.topCenter);
		topRight = new Rectangle(other.topRight);
		bottomLeft = new Rectangle(other.bottomLeft);
		bottomRight = new Rectangle(other.bottomRight);
		aboveChatboxRight = new Rectangle(other.aboveChatboxRight);
	}

    public OverlayBounds(Rectangle topLeft, Rectangle topCenter, Rectangle topRight, Rectangle bottomLeft, Rectangle bottomRight, Rectangle aboveChatboxRight) {
        this.topLeft = topLeft;
        this.topCenter = topCenter;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
        this.aboveChatboxRight = aboveChatboxRight;
    }

    OverlayBounds translated(final int x, final int y)
	{
		final OverlayBounds translated = new OverlayBounds(this);
		translated.getTopRight().translate(x, 0);
		translated.getTopCenter().translate(x / 2, 0);
		translated.getBottomLeft().translate(0, y);
		translated.getBottomRight().translate(x, y);
		translated.getAboveChatboxRight().translate(x, y);
		return translated;
	}

	Rectangle forPosition(OverlayPosition overlayPosition)
	{
		switch (overlayPosition)
		{
			case TOP_LEFT:
				return topLeft;
			case TOP_CENTER:
				return topCenter;
			case TOP_RIGHT:
				return topRight;
			case BOTTOM_LEFT:
				return bottomLeft;
			case BOTTOM_RIGHT:
				return bottomRight;
			case ABOVE_CHATBOX_RIGHT:
				return aboveChatboxRight;
			default:
				throw new IllegalArgumentException();
		}
	}

	OverlayPosition fromBounds(Rectangle bounds)
	{
		if (bounds == topLeft)
		{
			return TOP_LEFT;
		}
		else if (bounds == topCenter)
		{
			return TOP_CENTER;
		}
		else if (bounds == topRight)
		{
			return TOP_RIGHT;
		}
		else if (bounds == bottomLeft)
		{
			return BOTTOM_LEFT;
		}
		else if (bounds == bottomRight)
		{
			return BOTTOM_RIGHT;
		}
		else if (bounds == aboveChatboxRight)
		{
			return ABOVE_CHATBOX_RIGHT;
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}

	Collection<Rectangle> getBounds()
	{
		return Arrays.asList(topLeft, topRight, bottomLeft, bottomRight, aboveChatboxRight);
	}

    public Rectangle getTopLeft() {
        return this.topLeft;
    }

    public Rectangle getTopCenter() {
        return this.topCenter;
    }

    public Rectangle getTopRight() {
        return this.topRight;
    }

    public Rectangle getBottomLeft() {
        return this.bottomLeft;
    }

    public Rectangle getBottomRight() {
        return this.bottomRight;
    }

    public Rectangle getAboveChatboxRight() {
        return this.aboveChatboxRight;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof OverlayBounds)) return false;
        final OverlayBounds other = (OverlayBounds) o;
        final Object this$topLeft = this.getTopLeft();
        final Object other$topLeft = other.getTopLeft();
        if (this$topLeft == null ? other$topLeft != null : !this$topLeft.equals(other$topLeft)) return false;
        final Object this$topCenter = this.getTopCenter();
        final Object other$topCenter = other.getTopCenter();
        if (this$topCenter == null ? other$topCenter != null : !this$topCenter.equals(other$topCenter)) return false;
        final Object this$topRight = this.getTopRight();
        final Object other$topRight = other.getTopRight();
        if (this$topRight == null ? other$topRight != null : !this$topRight.equals(other$topRight)) return false;
        final Object this$bottomLeft = this.getBottomLeft();
        final Object other$bottomLeft = other.getBottomLeft();
        if (this$bottomLeft == null ? other$bottomLeft != null : !this$bottomLeft.equals(other$bottomLeft))
            return false;
        final Object this$bottomRight = this.getBottomRight();
        final Object other$bottomRight = other.getBottomRight();
        if (this$bottomRight == null ? other$bottomRight != null : !this$bottomRight.equals(other$bottomRight))
            return false;
        final Object this$aboveChatboxRight = this.getAboveChatboxRight();
        final Object other$aboveChatboxRight = other.getAboveChatboxRight();
        if (this$aboveChatboxRight == null ? other$aboveChatboxRight != null : !this$aboveChatboxRight.equals(other$aboveChatboxRight))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $topLeft = this.getTopLeft();
        result = result * PRIME + ($topLeft == null ? 43 : $topLeft.hashCode());
        final Object $topCenter = this.getTopCenter();
        result = result * PRIME + ($topCenter == null ? 43 : $topCenter.hashCode());
        final Object $topRight = this.getTopRight();
        result = result * PRIME + ($topRight == null ? 43 : $topRight.hashCode());
        final Object $bottomLeft = this.getBottomLeft();
        result = result * PRIME + ($bottomLeft == null ? 43 : $bottomLeft.hashCode());
        final Object $bottomRight = this.getBottomRight();
        result = result * PRIME + ($bottomRight == null ? 43 : $bottomRight.hashCode());
        final Object $aboveChatboxRight = this.getAboveChatboxRight();
        result = result * PRIME + ($aboveChatboxRight == null ? 43 : $aboveChatboxRight.hashCode());
        return result;
    }

    public String toString() {
        return "OverlayBounds(topLeft=" + this.getTopLeft() + ", topCenter=" + this.getTopCenter() + ", topRight=" + this.getTopRight() + ", bottomLeft=" + this.getBottomLeft() + ", bottomRight=" + this.getBottomRight() + ", aboveChatboxRight=" + this.getAboveChatboxRight() + ")";
    }
}
