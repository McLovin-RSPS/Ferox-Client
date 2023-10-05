/*
 * Copyright (c) 2017, Tomas Slusny <slusnucky@gmail.com>
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
package net.runelite.client.chat;

import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.Varbits;
import net.runelite.api.events.ConfigChanged;
import net.runelite.api.events.ResizeableChanged;
import net.runelite.api.events.SetMessage;
import net.runelite.api.events.VarbitChanged;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.InterfaceColour.ChatColorConfig;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.awt.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicReference;

@Singleton
public class ChatMessageManager
{
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ChatMessageManager.class);
    private final Multimap<ChatMessageType, ChatColor> colorCache = HashMultimap.create();
	private final Provider<Client> clientProvider;
	private final ScheduledExecutorService executor;
	private final ChatColorConfig chatColorConfig;
	private int transparencyVarbit = -1;
	private final Queue<QueuedMessage> queuedMessages = new ConcurrentLinkedQueue<>();

	@Inject
	private ChatMessageManager(Provider<Client> clientProvider, ScheduledExecutorService executor,
		ChatColorConfig chatColorConfig)
	{
		this.clientProvider = clientProvider;
		this.executor = executor;
		this.chatColorConfig = chatColorConfig;
	}

	@Subscribe
	public void onVarbitChanged(VarbitChanged event)
	{
		/*int setting = clientProvider.get().getVar(Varbits.TRANSPARENT_CHATBOX);

		if (transparencyVarbit != setting)
		{
			transparencyVarbit = setting;
			refreshAll();
		}*/
	}

	@Subscribe
	public void onResizableChanged(ResizeableChanged event)
	{
		refreshAll();
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		if (event.getGroup().equals("textrecolor"))
		{
			loadColors();
			refreshAll();
		}
	}

	@Subscribe
	public void onSetMessage(SetMessage setMessage)
	{

	}

	private static String wrapTextWithColour(String text, Color colour)
	{
		return "<col=" + Integer.toHexString(colour.getRGB() & 0xFFFFFF) + ">" + text + "</col>";
	}

	private static Color getDefaultColor(ChatMessageType type, boolean transparent)
	{
		if (!transparent)
		{
			switch (type)
			{
				case PUBLIC:
				case PUBLIC_MOD:
					return Color.decode("#0000FF");
				case PRIVATE_MESSAGE_SENT:
				case PRIVATE_MESSAGE_RECEIVED:
					return Color.decode("#00FFFF");
				case CLANCHAT:
					return Color.decode("#7F0000");
				case EXAMINE_ITEM:
				case EXAMINE_OBJECT:
				case EXAMINE_NPC:
				case GAME:
					return Color.decode("#000000");
			}
		}
		else
		{
			switch (type)
			{
				case PUBLIC:
				case PUBLIC_MOD:
					return Color.decode("#9090FF");
				case PRIVATE_MESSAGE_SENT:
				case PRIVATE_MESSAGE_RECEIVED:
					return Color.decode("#00FFFF");
				case CLANCHAT:
					return Color.decode("#7F0000");
				case EXAMINE_ITEM:
				case EXAMINE_OBJECT:
				case EXAMINE_NPC:
				case GAME:
					return Color.decode("#FFFFFF");
			}
		}

		return null;
	}

	private void loadColors()
	{

	}

	private ChatMessageManager cacheColor(final ChatColor chatColor, final ChatMessageType... types)
	{
		for (ChatMessageType chatMessageType : types)
		{
			// color is excluded from equals/hashCode on ChatColor
			colorCache.remove(chatMessageType, chatColor);
			colorCache.put(chatMessageType, chatColor);
		}

		return this;
	}

	public void queue(QueuedMessage message)
	{
		queuedMessages.add(message);
	}

	public void process()
	{
		if (!queuedMessages.isEmpty())
		{
			queuedMessages.forEach(this::add);
			queuedMessages.clear();
		}
	}

	private void add(QueuedMessage message)
	{
		final Client client = clientProvider.get();

		// this updates chat cycle
		client.addChatMessage(
			message.getType(),
			MoreObjects.firstNonNull(message.getName(), ""),
			MoreObjects.firstNonNull(message.getValue(), message.getRuneLiteFormattedMessage()),
			message.getSender());

		String[][] chatLineBuffer = client.getChatLineMap().get(Integer.valueOf(message.getType().getType()));
		String[] line = chatLineBuffer[0];
		line[2] = message.getRuneLiteFormattedMessage();
		update(line);
	}

	public void update(String[] target) {
		String runeliteFormattedMessage = target[2];
		if (Strings.isNullOrEmpty(runeliteFormattedMessage))
			return;
		ChatMessageType chatMessageType = ChatMessageType.of(Integer.parseInt(target[0]));
		Client client = (Client)this.clientProvider.get();
		boolean transparent = (client.isResized() && client.getVar(Varbits.TRANSPARENT_CHATBOX) != 0);
		Collection<ChatColor> chatColors = this.colorCache.get(chatMessageType);
		if (chatColors == null || chatColors.isEmpty()) {
			target[2] = runeliteFormattedMessage;
			return;
		}
		target[2] = recolorMessage(transparent, target[2], chatMessageType);
	}

	private String recolorMessage(boolean transparent, String message, ChatMessageType messageType)
	{
		final Collection<ChatColor> chatColors = colorCache.get(messageType);
		final AtomicReference<String> resultMessage = new AtomicReference<>(message);

		// Replace custom formatting with actual colors
		chatColors.stream()
			.filter(chatColor -> chatColor.isTransparent() == transparent)
			.forEach(chatColor ->
				resultMessage.getAndUpdate(oldMessage -> oldMessage.replaceAll(
					"<col" + chatColor.getType().name() + ">",
					"<col=" + Integer.toHexString(chatColor.getColor().getRGB() & 0xFFFFFF) + ">")));

		return resultMessage.get();
	}

	public void refreshAll() {
		Client client = this.clientProvider.get();
		this.executor.submit(() -> {
			client.getChatLineMap().values().stream().filter(Objects::nonNull).flatMap(Arrays::stream).filter(Objects::nonNull).forEach(this::update);
			client.refreshChat();
		});
	}
}
