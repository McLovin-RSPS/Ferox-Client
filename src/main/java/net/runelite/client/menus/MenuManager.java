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
package net.runelite.client.menus;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.runelite.api.*;
import net.runelite.api.events.*;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.util.Text;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.util.*;

@Singleton
public class MenuManager
{
	/*
	 * The index needs to be between 4 and 7,
	 */
	private static final int IDX_LOWER = 4;
	private static final int IDX_UPPER = 8;
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(MenuManager.class);

    private final Provider<Client> clientProvider;
	private final EventBus eventBus;

	//Maps the indexes that are being used to the menu option.
	private final Map<Integer, String> playerMenuIndexMap = new HashMap<>();
	//Used to manage custom non-player menu options
	private final Multimap<Integer, WidgetMenuOption> managedMenuOptions = HashMultimap.create();
	private final Set<String> npcMenuOptions = new HashSet<>();

	@Inject
	public MenuManager(Provider<Client> clientProvider, EventBus eventBus)
	{
		this.clientProvider = clientProvider;
		this.eventBus = eventBus;
	}

	public void addNpcMenuOption(String option)
	{
		npcMenuOptions.add(option);

		// add to surrounding npcs
		Client client = clientProvider.get();

		if (client == null)
		{
			return;
		}

		for (NPC npc : client.getNpcs())
		{
			NPCComposition composition = npc.getComposition();
			addNpcOption(composition, option);
		}
	}

	public void removeNpcMenuOption(String option)
	{
		npcMenuOptions.remove(option);

		// remove this option from all npc compositions
		Client client = clientProvider.get();

		if (client == null)
		{
			return;
		}

		for (NPC npc : client.getNpcs())
		{
			NPCComposition composition = npc.getComposition();
			removeNpcOption(composition, option);
		}
	}

	/**
	 * Adds a CustomMenuOption to the list of managed menu options.
	 *
	 * @param customMenuOption The custom menu to add
	 */
	public void addManagedCustomMenu(WidgetMenuOption customMenuOption)
	{
		WidgetInfo widget = customMenuOption.getWidget();
		managedMenuOptions.put(widget.getId(), customMenuOption);
	}

	/**
	 * Removes a CustomMenuOption from the list of managed menu options.
	 *
	 * @param customMenuOption The custom menu to add
	 */
	public void removeManagedCustomMenu(WidgetMenuOption customMenuOption)
	{
		WidgetInfo widget = customMenuOption.getWidget();
		managedMenuOptions.remove(widget.getId(), customMenuOption);
	}

	private boolean menuContainsCustomMenu(WidgetMenuOption customMenuOption)
	{
		Client client = clientProvider.get();

		if (client == null)
		{
			return false;
		}

		for (MenuEntry menuEntry : client.getMenuEntries())
		{
			String option = menuEntry.getOption();
			String target = menuEntry.getTarget();

			if (option.equals(customMenuOption.getMenuOption()) && target.equals(customMenuOption.getMenuTarget()))
			{
				return true;
			}
		}
		return false;
	}

	public void addPlayerMenuItem(String menuText)
	{
		Preconditions.checkNotNull(menuText);

		int playerMenuIndex = findEmptyPlayerMenuIndex();
		if (playerMenuIndex == IDX_UPPER)
		{
			return; // no more slots
		}

		addPlayerMenuItem(playerMenuIndex, menuText);
	}

	public void removePlayerMenuItem(String menuText)
	{
		Preconditions.checkNotNull(menuText);
		for (Map.Entry<Integer, String> entry : playerMenuIndexMap.entrySet())
		{
			if (entry.getValue().equalsIgnoreCase(menuText))
			{
				removePlayerMenuItem(entry.getKey());
				break;
			}
		}
	}

	@Subscribe
	public void onPlayerMenuOptionsChanged(PlayerMenuOptionsChanged event)
	{
		int idx = event.getIndex();

		String menuText = playerMenuIndexMap.get(idx);
		if (menuText == null)
		{
			return; // not our menu
		}

		// find new index for this option
		int newIdx = findEmptyPlayerMenuIndex();
		if (newIdx == IDX_UPPER)
		{
			log.debug("Client has updated player menu index {} where option {} was, and there are no more free slots available", idx, menuText);
			return;
		}

		log.debug("Client has updated player menu index {} where option {} was, moving to index {}", idx, menuText, newIdx);

		playerMenuIndexMap.remove(idx);
		addPlayerMenuItem(newIdx, menuText);
	}

	@Subscribe
	public void onNpcActionChanged(NpcActionChanged event)
	{
		NPCComposition composition = event.getNpcComposition();
		for (String npcOption : npcMenuOptions)
		{
			addNpcOption(composition, npcOption);
		}
	}

	private void addNpcOption(NPCComposition composition, String npcOption)
	{
		String[] actions = composition.getActions();
		int unused = -1;
		for (int i = 0; i < actions.length; ++i)
		{
			if (actions[i] == null && unused == -1)
			{
				unused = i;
			}
			else if (actions[i] != null && actions[i].equals(npcOption))
			{
				return;
			}
		}
		if (unused == -1)
		{
			return;
		}
		actions[unused] = npcOption;
	}

	private void removeNpcOption(NPCComposition composition, String npcOption)
	{
		String[] actions = composition.getActions();

		if (composition.getActions() == null)
		{
			return;
		}

		for (int i = 0; i < actions.length; ++i)
		{
			if (actions[i] != null && actions[i].equals(npcOption))
			{
				actions[i] = null;
			}
		}
	}

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked event)
	{
		if (event.getMenuAction() != MenuAction.RUNELITE)
		{
			return; // not a player menu
		}

		int widgetId = event.getWidgetId();
		Collection<WidgetMenuOption> options = managedMenuOptions.get(widgetId);

		for (WidgetMenuOption curMenuOption : options)
		{
			if (curMenuOption.getMenuTarget().equals(event.getMenuTarget())
					&& curMenuOption.getMenuOption().equals(event.getMenuOption()))
			{
				WidgetMenuOptionClicked customMenu = new WidgetMenuOptionClicked();
				customMenu.setMenuOption(event.getMenuOption());
				customMenu.setMenuTarget(event.getMenuTarget());
				customMenu.setWidget(curMenuOption.getWidget());
				eventBus.post(customMenu);
				return; // don't continue because it's not a player option
			}
		}

		String target = event.getMenuTarget();

		// removes tags and level from player names for example:
		// <col=ffffff>username<col=40ff00>  (level-42) or <col=ffffff><img=2>username</col>
		String username = Text.removeTags(target).split("[(]")[0].trim();

		PlayerMenuOptionClicked playerMenuOptionClicked = new PlayerMenuOptionClicked();
		playerMenuOptionClicked.setMenuOption(event.getMenuOption());
		playerMenuOptionClicked.setMenuTarget(username);

		eventBus.post(playerMenuOptionClicked);
	}

	private void addPlayerMenuItem(int playerOptionIndex, String menuText)
	{
		Client client = clientProvider.get();

		if (client == null)
		{
			return;
		}

		client.getPlayerOptions()[playerOptionIndex] = menuText;
		client.getPlayerOptionsPriorities()[playerOptionIndex] = true;
		client.getPlayerMenuTypes()[playerOptionIndex] = MenuAction.RUNELITE.getId();

		playerMenuIndexMap.put(playerOptionIndex, menuText);
	}

	private void removePlayerMenuItem(int playerOptionIndex)
	{
		Client client = clientProvider.get();

		if (client == null)
		{
			return;
		}

		client.getPlayerOptions()[playerOptionIndex] = null;
		playerMenuIndexMap.remove(playerOptionIndex);
	}

	/**
	 * Find the next empty player menu slot index
	 *
	 * @return
	 */
	private int findEmptyPlayerMenuIndex()
	{
		int index = IDX_LOWER;
		Client client = clientProvider.get();

		if (client == null)
		{
			return IDX_UPPER;
		}

		String[] playerOptions = client.getPlayerOptions();
		while (index < IDX_UPPER && playerOptions[index] != null)
		{
			index++;
		}

		return index;
	}
}
