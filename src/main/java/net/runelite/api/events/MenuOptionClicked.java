/*
 * Copyright (c) 2017, Robin <robin.weymans@gmail.com>
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

import net.runelite.api.*;

/**
 * An event where a menu option has been clicked.
 * <p>
 * This event does not only trigger when clicking an option in a
 * right-clicked menu. The event will trigger for any left click
 * action performed (ie. clicking an item, walking to a tile, etc) as
 * well as any right-click menu option.
 * <p>
 * By default, when there is no action performed when left-clicking,
 * it seems that this event still triggers with the "Cancel" action.
 */
public class MenuOptionClicked
{
    /**
     * Action parameter 0. Its value depends on the menuAction.
     */
    private int param0;
    /**
     * Action parameter 1. Its value depends on the menuAction.
     */
    private int param1;
    /**
     * The option text added to the menu.
     */
    private String menuOption;
    /**
     * The target of the action.
     */
    private String menuTarget;
    /**
     * The action performed.
     */
    private MenuAction menuAction;
    /**
     * The ID of the object, actor, or item that the interaction targets.
     */
    private long id;
    /**
     * The selected item index at the time of the option click.
     */
    private int selectedItemIndex;
    /**
     * Whether or not the event has been consumed by a subscriber.
     */
    private boolean consumed;

    private NPC npc;

    private GameObject gameObject;

    public NPC getNpc() {
        return npc;
    }

    public void setNpc(NPC npc) {
        this.npc = npc;
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private Player player;

    public MenuOptionClicked() {
    }

    /**
     * Marks the event as having been consumed.
     * <p>
     * Setting this state indicates that a plugin has processed the menu
     * option being clicked and that the event will not be passed on
     * for handling by vanilla client code.
     */
    public void consume()
    {
        this.consumed = true;
    }

    @Deprecated
    public int getActionParam()
    {
        return param0;
    }

    @Deprecated
    public int getWidgetId()
    {
        return param1;
    }

    public int getParam0() {
        return this.param0;
    }

    public int getParam1() {
        return this.param1;
    }

    public String getMenuOption() {
        return this.menuOption;
    }

    public String getMenuTarget() {
        return this.menuTarget;
    }

    public MenuAction getMenuAction() {
        return this.menuAction;
    }

    public long getId() {
        return this.id;
    }

    public int getSelectedItemIndex() {
        return this.selectedItemIndex;
    }

    public boolean isConsumed() {
        return this.consumed;
    }

    public void setParam0(int param0) {
        this.param0 = param0;
    }

    public void setParam1(int param1) {
        this.param1 = param1;
    }

    public void setMenuOption(String menuOption) {
        this.menuOption = menuOption;
    }

    public void setMenuTarget(String menuTarget) {
        this.menuTarget = menuTarget;
    }

    public void setMenuAction(MenuAction menuAction) {
        this.menuAction = menuAction;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSelectedItemIndex(int selectedItemIndex) {
        this.selectedItemIndex = selectedItemIndex;
    }

    public void setConsumed(boolean consumed) {
        this.consumed = consumed;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof MenuOptionClicked)) return false;
        final MenuOptionClicked other = (MenuOptionClicked) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getParam0() != other.getParam0()) return false;
        if (this.getParam1() != other.getParam1()) return false;
        final Object this$menuOption = this.getMenuOption();
        final Object other$menuOption = other.getMenuOption();
        if (this$menuOption == null ? other$menuOption != null : !this$menuOption.equals(other$menuOption))
            return false;
        final Object this$menuTarget = this.getMenuTarget();
        final Object other$menuTarget = other.getMenuTarget();
        if (this$menuTarget == null ? other$menuTarget != null : !this$menuTarget.equals(other$menuTarget))
            return false;
        final Object this$menuAction = this.getMenuAction();
        final Object other$menuAction = other.getMenuAction();
        if (this$menuAction == null ? other$menuAction != null : !this$menuAction.equals(other$menuAction))
            return false;
        if (this.getId() != other.getId()) return false;
        if (this.getSelectedItemIndex() != other.getSelectedItemIndex()) return false;
        if (this.isConsumed() != other.isConsumed()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof MenuOptionClicked;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getParam0();
        result = result * PRIME + this.getParam1();
        final Object $menuOption = this.getMenuOption();
        result = result * PRIME + ($menuOption == null ? 43 : $menuOption.hashCode());
        final Object $menuTarget = this.getMenuTarget();
        result = result * PRIME + ($menuTarget == null ? 43 : $menuTarget.hashCode());
        final Object $menuAction = this.getMenuAction();
        result = result * PRIME + ($menuAction == null ? 43 : $menuAction.hashCode());
        result = (int) (result * PRIME + this.getId());
        result = result * PRIME + this.getSelectedItemIndex();
        result = result * PRIME + (this.isConsumed() ? 79 : 97);
        return result;
    }

    public String toString() {
        return "MenuOptionClicked(param0=" + this.getParam0() + ", param1=" + this.getParam1() + ", menuOption=" + this.getMenuOption() + ", menuTarget=" + this.getMenuTarget() + ", menuAction=" + this.getMenuAction() + ", id=" + this.getId() + ", selectedItemIndex=" + this.getSelectedItemIndex() + ", consumed=" + this.isConsumed() + ")";
    }
}