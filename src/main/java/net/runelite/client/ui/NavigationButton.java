/*
 * Copyright (c) 2017-2018, Adam <Adam@sigterm.info>
 * Copyright (c) 2018, Tomas Slusny <slusnucky@gmail.con>
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
package net.runelite.client.ui;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * UI navigation button.
 */
public class NavigationButton
{
	/**
	 * Icon of button.
	 */
	private final BufferedImage icon;

	/**
	 * If the button is tab or not
	 */
	private boolean tab = true;

	/**
	 * Tooltip to show when hovered.
	 */
	private final String tooltip;

	/**
	 * Button selection state
	 */
	private boolean selected;

	/**
	 * On click action of the button.
	 */
	private Runnable onClick;

	/**
	 * On select action of the button.
	 */
	private Runnable onSelect;

	/**
	 * Plugin panel, used when expanding and contracting sidebar.
	 */
	private PluginPanel panel;

	/**
	 * The order in which the button should be displayed in the side bar. (from lower to higher)
	 */
	private int priority;

	/**
	 * Map of key-value pairs for setting the popup menu
	 */
	private Map<String, Runnable> popup;

	NavigationButton(BufferedImage icon, boolean tab, String tooltip, boolean selected, Runnable onClick, Runnable onSelect, PluginPanel panel, int priority, Map<String, Runnable> popup) {
		this.icon = icon;
		this.tab = tab;
		this.tooltip = tooltip;
		this.selected = selected;
		this.onClick = onClick;
		this.onSelect = onSelect;
		this.panel = panel;
		this.priority = priority;
		this.popup = popup;
	}

	private static boolean $default$tab() {
		return true;
	}

	private static String $default$tooltip() {
		return "";
	}

	public static NavigationButtonBuilder builder() {
		return new NavigationButtonBuilder();
	}

	public BufferedImage getIcon() {
		return this.icon;
	}

	public boolean isTab() {
		return this.tab;
	}

	public String getTooltip() {
		return this.tooltip;
	}

	public boolean isSelected() {
		return this.selected;
	}

	public Runnable getOnClick() {
		return this.onClick;
	}

	public Runnable getOnSelect() {
		return this.onSelect;
	}

	public PluginPanel getPanel() {
		return this.panel;
	}

	public int getPriority() {
		return this.priority;
	}

	public Map<String, Runnable> getPopup() {
		return this.popup;
	}

	public void setTab(boolean tab) {
		this.tab = tab;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public void setOnClick(Runnable onClick) {
		this.onClick = onClick;
	}

	public void setOnSelect(Runnable onSelect) {
		this.onSelect = onSelect;
	}

	public void setPanel(PluginPanel panel) {
		this.panel = panel;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public void setPopup(Map<String, Runnable> popup) {
		this.popup = popup;
	}

	public String toString() {
		return "NavigationButton(icon=" + this.getIcon() + ", tab=" + this.isTab() + ", tooltip=" + this.getTooltip() + ", selected=" + this.isSelected() + ", onClick=" + this.getOnClick() + ", onSelect=" + this.getOnSelect() + ", panel=" + this.getPanel() + ", priority=" + this.getPriority() + ", popup=" + this.getPopup() + ")";
	}

	public boolean equals(final Object o) {
		if (o == this) return true;
		if (!(o instanceof NavigationButton)) return false;
		final NavigationButton other = (NavigationButton) o;
		if (!other.canEqual((Object) this)) return false;
		final Object this$tooltip = this.getTooltip();
		final Object other$tooltip = other.getTooltip();
		if (this$tooltip == null ? other$tooltip != null : !this$tooltip.equals(other$tooltip)) return false;
		return true;
	}

	protected boolean canEqual(final Object other) {
		return other instanceof NavigationButton;
	}

	public int hashCode() {
		final int PRIME = 59;
		int result = 1;
		final Object $tooltip = this.getTooltip();
		result = result * PRIME + ($tooltip == null ? 43 : $tooltip.hashCode());
		return result;
	}

	public static class NavigationButtonBuilder {
		private BufferedImage icon;
		private boolean tab$value;
		private boolean tab$set;
		private String tooltip$value;
		private boolean tooltip$set;
		private boolean selected;
		private Runnable onClick;
		private Runnable onSelect;
		private PluginPanel panel;
		private int priority;
		private Map<String, Runnable> popup;

		NavigationButtonBuilder() {
		}

		public NavigationButtonBuilder icon(BufferedImage icon) {
			this.icon = icon;
			return this;
		}

		public NavigationButtonBuilder tab(boolean tab) {
			this.tab$value = tab;
			this.tab$set = true;
			return this;
		}

		public NavigationButtonBuilder tooltip(String tooltip) {
			this.tooltip$value = tooltip;
			this.tooltip$set = true;
			return this;
		}

		public NavigationButtonBuilder selected(boolean selected) {
			this.selected = selected;
			return this;
		}

		public NavigationButtonBuilder onClick(Runnable onClick) {
			this.onClick = onClick;
			return this;
		}

		public NavigationButtonBuilder onSelect(Runnable onSelect) {
			this.onSelect = onSelect;
			return this;
		}

		public NavigationButtonBuilder panel(PluginPanel panel) {
			this.panel = panel;
			return this;
		}

		public NavigationButtonBuilder priority(int priority) {
			this.priority = priority;
			return this;
		}

		public NavigationButtonBuilder popup(Map<String, Runnable> popup) {
			this.popup = popup;
			return this;
		}

		public NavigationButton build() {
			boolean tab$value = this.tab$value;
			if (!this.tab$set) {
				tab$value = NavigationButton.$default$tab();
			}
			String tooltip$value = this.tooltip$value;
			if (!this.tooltip$set) {
				tooltip$value = NavigationButton.$default$tooltip();
			}
			return new NavigationButton(icon, tab$value, tooltip$value, selected, onClick, onSelect, panel, priority, popup);
		}

		public String toString() {
			return "NavigationButton.NavigationButtonBuilder(icon=" + this.icon + ", tab$value=" + this.tab$value + ", tooltip$value=" + this.tooltip$value + ", selected=" + this.selected + ", onClick=" + this.onClick + ", onSelect=" + this.onSelect + ", panel=" + this.panel + ", priority=" + this.priority + ", popup=" + this.popup + ")";
		}
	}
}
