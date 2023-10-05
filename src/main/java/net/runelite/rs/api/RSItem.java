package net.runelite.rs.api;

import net.runelite.api.Item;
import net.runelite.api.Tile;
import net.runelite.mapping.Import;

public interface RSItem extends RSRenderable, Item {
	@Import("id")
	int getId();

	@Import("id")
	void setId(int paramInt);

	@Import("quantity")
	int getQuantity();

	@Import("quantity")
	void setQuantity(int paramInt);

	int getX();

	void setX(int paramInt);

	int getY();

	void setY(int paramInt);

	Tile getTile();
}

