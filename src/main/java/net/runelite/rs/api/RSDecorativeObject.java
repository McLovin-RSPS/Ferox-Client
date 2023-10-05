/*
 * Decompiled with CFR 0.150.
 */
package net.runelite.rs.api;

import net.runelite.api.DecorativeObject;
import net.runelite.mapping.Import;

public interface RSDecorativeObject
		extends DecorativeObject {
	@Override
	@Import(value="hash")
	public long getHash();

	@Override
	@Import(value="x")
	public int getX();

	@Override
	@Import(value="y")
	public int getY();

	@Import(value="offsetX")
	public int getXOffset();

	@Import(value="offsetY")
	public int getYOffset();

	@Import(value="rotation")
	public int getOrientation();

	@Import(value="renderable1")
	public RSRenderable getRenderable();

	public void setPlane(int var1);
}

