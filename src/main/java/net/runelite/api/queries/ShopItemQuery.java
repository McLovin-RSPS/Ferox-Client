/*
 * Copyright (c) 2017, Devin French <https://github.com/devinfrench>
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
package net.runelite.api.queries;

import net.runelite.api.Client;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.api.widgets.WidgetItem;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class ShopItemQuery extends WidgetItemQuery
{
	@Override
	public WidgetItem[] result(Client client)
	{
		Collection<WidgetItem> widgetItems = getShopItems(client);
		if (widgetItems != null)
		{
			return widgetItems.stream()
				.filter(Objects::nonNull)
				.filter(predicate)
				.toArray(WidgetItem[]::new);
		}
		return new WidgetItem[0];
	}

	private Collection<WidgetItem> getShopItems(Client client)
	{
		Collection<WidgetItem> widgetItems = new ArrayList<>();
		Widget shop = client.getWidget(WidgetInfo.SHOP_ITEMS_CONTAINER);
		if (shop != null && !shop.isHidden())
		{
			Widget[] children = shop.getDynamicChildren();
			for (int i = 1; i < children.length; i++)
			{
				Widget child = children[i];
				// set bounds to same size as default inventory
				Rectangle bounds = child.getBounds();
				bounds.setBounds(bounds.x - 1, bounds.y - 1, 32, 32);
				widgetItems.add(new WidgetItem(child.getItemId(), child.getItemQuantity(), i - 1, bounds, child));
			}
		}
		return widgetItems;
	}
}
