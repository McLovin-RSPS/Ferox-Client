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
package net.runelite.api.events;

/**
 * An event where a player menu option that was added by RuneLite has
 * been clicked (ie. HiScore Lookup).
 */
public class PlayerMenuOptionClicked
{
	/**
	 * The menu option clicked.
	 */
	private String menuOption;
	/**
	 * The target player.
	 */
	private String menuTarget;

    public PlayerMenuOptionClicked() {
    }

    public String getMenuOption() {
        return this.menuOption;
    }

    public String getMenuTarget() {
        return this.menuTarget;
    }

    public void setMenuOption(String menuOption) {
        this.menuOption = menuOption;
    }

    public void setMenuTarget(String menuTarget) {
        this.menuTarget = menuTarget;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof PlayerMenuOptionClicked)) return false;
        final PlayerMenuOptionClicked other = (PlayerMenuOptionClicked) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$menuOption = this.getMenuOption();
        final Object other$menuOption = other.getMenuOption();
        if (this$menuOption == null ? other$menuOption != null : !this$menuOption.equals(other$menuOption))
            return false;
        final Object this$menuTarget = this.getMenuTarget();
        final Object other$menuTarget = other.getMenuTarget();
        if (this$menuTarget == null ? other$menuTarget != null : !this$menuTarget.equals(other$menuTarget))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PlayerMenuOptionClicked;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $menuOption = this.getMenuOption();
        result = result * PRIME + ($menuOption == null ? 43 : $menuOption.hashCode());
        final Object $menuTarget = this.getMenuTarget();
        result = result * PRIME + ($menuTarget == null ? 43 : $menuTarget.hashCode());
        return result;
    }

    public String toString() {
        return "PlayerMenuOptionClicked(menuOption=" + this.getMenuOption() + ", menuTarget=" + this.getMenuTarget() + ")";
    }
}
