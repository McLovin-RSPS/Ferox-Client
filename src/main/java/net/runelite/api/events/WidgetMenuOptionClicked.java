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
package net.runelite.api.events;

import net.runelite.api.widgets.WidgetInfo;

/**
 * An event where an option has been clicked in a {@link net.runelite.api.widgets.Widget}s menu.
 */
public class WidgetMenuOptionClicked
{
	/**
	 * The clicked menu option.
	 */
	private String menuOption;
	/**
	 * The clicked menu target.
	 */
	private String menuTarget;
	/**
	 * The type of widget that was clicked.
	 */
	private WidgetInfo widget;

    public WidgetMenuOptionClicked() {
    }

    public String getMenuOption() {
        return this.menuOption;
    }

    public String getMenuTarget() {
        return this.menuTarget;
    }

    public WidgetInfo getWidget() {
        return this.widget;
    }

    public void setMenuOption(String menuOption) {
        this.menuOption = menuOption;
    }

    public void setMenuTarget(String menuTarget) {
        this.menuTarget = menuTarget;
    }

    public void setWidget(WidgetInfo widget) {
        this.widget = widget;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof WidgetMenuOptionClicked)) return false;
        final WidgetMenuOptionClicked other = (WidgetMenuOptionClicked) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$menuOption = this.getMenuOption();
        final Object other$menuOption = other.getMenuOption();
        if (this$menuOption == null ? other$menuOption != null : !this$menuOption.equals(other$menuOption))
            return false;
        final Object this$menuTarget = this.getMenuTarget();
        final Object other$menuTarget = other.getMenuTarget();
        if (this$menuTarget == null ? other$menuTarget != null : !this$menuTarget.equals(other$menuTarget))
            return false;
        final Object this$widget = this.getWidget();
        final Object other$widget = other.getWidget();
        if (this$widget == null ? other$widget != null : !this$widget.equals(other$widget)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof WidgetMenuOptionClicked;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $menuOption = this.getMenuOption();
        result = result * PRIME + ($menuOption == null ? 43 : $menuOption.hashCode());
        final Object $menuTarget = this.getMenuTarget();
        result = result * PRIME + ($menuTarget == null ? 43 : $menuTarget.hashCode());
        final Object $widget = this.getWidget();
        result = result * PRIME + ($widget == null ? 43 : $widget.hashCode());
        return result;
    }

    public String toString() {
        return "WidgetMenuOptionClicked(menuOption=" + this.getMenuOption() + ", menuTarget=" + this.getMenuTarget() + ", widget=" + this.getWidget() + ")";
    }
}
