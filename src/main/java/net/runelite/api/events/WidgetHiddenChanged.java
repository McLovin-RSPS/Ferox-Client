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
package net.runelite.api.events;

import net.runelite.api.widgets.Widget;

/**
 * An event where the hidden state of a {@link Widget} has been modified.
 */
public class WidgetHiddenChanged
{
	/**
	 * The affected widget.
	 */
	private Widget widget;
	/**
	 * The new hidden state of the widget.
	 */
	private boolean hidden;

    public WidgetHiddenChanged() {
    }

    public Widget getWidget() {
        return this.widget;
    }

    public boolean isHidden() {
        return this.hidden;
    }

    public void setWidget(Widget widget) {
        this.widget = widget;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof WidgetHiddenChanged)) return false;
        final WidgetHiddenChanged other = (WidgetHiddenChanged) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$widget = this.getWidget();
        final Object other$widget = other.getWidget();
        if (this$widget == null ? other$widget != null : !this$widget.equals(other$widget)) return false;
        if (this.isHidden() != other.isHidden()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof WidgetHiddenChanged;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $widget = this.getWidget();
        result = result * PRIME + ($widget == null ? 43 : $widget.hashCode());
        result = result * PRIME + (this.isHidden() ? 79 : 97);
        return result;
    }

    public String toString() {
        return "WidgetHiddenChanged(widget=" + this.getWidget() + ", hidden=" + this.isHidden() + ")";
    }
}
