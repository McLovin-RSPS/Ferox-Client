package net.runelite.api.hooks;

import net.runelite.api.BufferProvider;
import net.runelite.api.GraphicsBufferType;
import net.runelite.api.MainBufferProvider;
import net.runelite.api.widgets.WidgetItem;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public interface Callbacks {
	void post(Object paramObject);

	void postDeferred(Object paramObject);

	void clientMainLoop();

	void updateNpcs();

	void drawScene();

	void drawGame();

	void drawAboveOverheads();

	void drawAfterWidgets(GraphicsBufferType paramGraphicsBufferType);

	void draw(BufferProvider paramMainBufferProvider, Graphics paramGraphics);

	MouseEvent mousePressed(MouseEvent paramMouseEvent);

	MouseEvent mouseReleased(MouseEvent paramMouseEvent);

	MouseEvent mouseClicked(MouseEvent paramMouseEvent);

	MouseEvent mouseEntered(MouseEvent paramMouseEvent);

	MouseEvent mouseExited(MouseEvent paramMouseEvent);

	MouseEvent mouseDragged(MouseEvent paramMouseEvent);

	MouseEvent mouseMoved(MouseEvent paramMouseEvent);

	MouseWheelEvent mouseWheelMoved(MouseWheelEvent paramMouseWheelEvent);

	void keyPressed(KeyEvent paramKeyEvent);

	void keyReleased(KeyEvent paramKeyEvent);

	void keyTyped(KeyEvent paramKeyEvent);

	void drawItem(int paramInt, WidgetItem paramWidgetItem);
}

