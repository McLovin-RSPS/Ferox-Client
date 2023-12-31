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
package net.runelite.client.ui.overlay;

import com.google.common.base.MoreObjects;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.GraphicsBufferType;
import net.runelite.api.events.FocusChanged;
import net.runelite.client.config.RuneLiteConfig;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.input.KeyListener;
import net.runelite.client.input.KeyManager;
import net.runelite.client.input.MouseListener;
import net.runelite.client.input.MouseManager;
import net.runelite.client.ui.FontManager;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;

import static java.lang.System.out;

@Singleton
public class OverlayRenderer extends MouseListener implements KeyListener
{
	private static final int BORDER_LEFT_RESIZABLE = 5;
	private static final int BORDER_TOP_RESIZABLE = 20;
	private static final int FRAME_OFFSET = 5;
	private static final int BORDER_LEFT_FIXED = BORDER_LEFT_RESIZABLE + FRAME_OFFSET;
	private static final int BORDER_TOP_FIXED = BORDER_TOP_RESIZABLE + FRAME_OFFSET;
	private static final int BORDER_RIGHT = 2;
	private static final int BORDER_BOTTOM = 2;
	private static final int PADDING = 2;
	private static final Dimension SNAP_CORNER_SIZE = new Dimension(80, 80);
	private static final Color SNAP_CORNER_COLOR = new Color(0, 255, 255, 50);
	private static final Color SNAP_CORNER_ACTIVE_COLOR = new Color(0, 255, 0, 100);
	private static final Color MOVING_OVERLAY_COLOR = new Color(255, 255, 0, 100);
	private static final Color MOVING_OVERLAY_ACTIVE_COLOR = new Color(255, 255, 0, 200);
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(OverlayRenderer.class);
    private final Provider<Client> clientProvider;
	private final OverlayManager overlayManager;
	private final RuneLiteConfig runeLiteConfig;

	// Overlay movement variables
	private final Point overlayOffset = new Point();
	private final Point mousePosition = new Point();
	private Overlay movedOverlay;
	private boolean inOverlayDraggingMode;

	// Overlay state validation
	private Rectangle viewportBounds;
	private Rectangle chatboxBounds;
	private boolean chatboxHidden;
	private boolean isResizeable;
	private OverlayBounds snapCorners;

	@Inject
	private OverlayRenderer(
		final Provider<Client> clientProvider,
		final OverlayManager overlayManager,
		final RuneLiteConfig runeLiteConfig,
		final MouseManager mouseManager,
		final KeyManager keyManager)
	{
		this.clientProvider = clientProvider;
		this.overlayManager = overlayManager;
		this.runeLiteConfig = runeLiteConfig;
		keyManager.registerKeyListener(this);
		mouseManager.registerMouseListener(this);
	}

	@Subscribe
	public void onFocusChanged(FocusChanged event)
	{
		if (!event.isFocused())
		{
			inOverlayDraggingMode = false;
		}
	}

	public void render(Graphics2D graphics, final OverlayLayer layer, GraphicsBufferType bufferType)
	{
		final Client client = clientProvider.get();
		final List<Overlay> overlays = overlayManager.getLayer(layer);
		if (client == null
			|| overlays == null
			|| overlays.isEmpty()
			|| client.getGameState() != GameState.LOGGED_IN)
		{
			return;
		}

		if (shouldInvalidateBounds())
		{
			snapCorners = buildSnapCorners();
		}

		// Create copy of snap corners because overlays will modify them
		OverlayBounds snapCorners = new OverlayBounds(this.snapCorners);
		OverlayUtil.setGraphicProperties(graphics);

		// Draw snap corners
		if (layer == OverlayLayer.UNDER_WIDGETS && movedOverlay != null && movedOverlay.getPosition() != OverlayPosition.DETACHED)
		{
			final OverlayBounds translatedSnapCorners = snapCorners.translated(
				-SNAP_CORNER_SIZE.width,
				-SNAP_CORNER_SIZE.height);

			final Color previous = graphics.getColor();

			for (final Rectangle corner : translatedSnapCorners.getBounds())
			{
				graphics.setColor(corner.contains(mousePosition) ? SNAP_CORNER_ACTIVE_COLOR : SNAP_CORNER_COLOR);
				graphics.fill(corner);
			}

			graphics.setColor(previous);
		}

		for (Overlay overlay : overlays)
		{
			if(overlay.getBufferType().contains(GraphicsBufferType.ALL) || overlay.getBufferType().contains(bufferType)) {
				OverlayPosition overlayPosition = overlay.getPosition();
				if (overlay.getPreferredPosition() != null) {
					overlayPosition = overlay.getPreferredPosition();
				}

				if (overlayPosition == OverlayPosition.ABOVE_CHATBOX_RIGHT && !isResizeable) {
					// On fixed mode, ABOVE_CHATBOX_RIGHT is in the same location as
					// BOTTOM_RIGHT. Just use BOTTOM_RIGHT to prevent overlays from
					// drawing over each other.
					overlayPosition = OverlayPosition.BOTTOM_RIGHT;
				}

				if (overlayPosition == OverlayPosition.DYNAMIC || overlayPosition == OverlayPosition.TOOLTIP) {
					safeRender(client, overlay, layer, graphics, new Point());
				} else {
					final Point location = overlay.getBounds().getLocation();
					final Dimension dimension = overlay.getBounds().getSize();
					// If the final position is not modified, layout it
					if (overlayPosition != OverlayPosition.DETACHED && (overlay.getPreferredLocation() == null || overlay.getPreferredPosition() != null)) {
						final Rectangle snapCorner = snapCorners.forPosition(overlayPosition);
						final Point translation = OverlayUtil.transformPosition(overlayPosition, dimension);
						location.setLocation(snapCorner.getX() + translation.x, snapCorner.getY() + translation.y);
						final Point padding = OverlayUtil.padPosition(overlayPosition, dimension, PADDING);
						snapCorner.translate(padding.x, padding.y);
					} else {
						final Point preferredLocation = overlay.getPreferredLocation();
                        out.println("preferredLocation=" + preferredLocation.toString());
						if (preferredLocation != null) {
							final Dimension realDimensions = client.getRealDimensions();
							final int x = Math.min(realDimensions.width - 5, preferredLocation.x);
							final int y = Math.min(realDimensions.height - 5, preferredLocation.y);
							location.setLocation(x, y);
						}
					}

					if (overlay.getPreferredSize() != null) {
						overlay.getBounds().setSize(overlay.getPreferredSize());
					}

					safeRender(client, overlay, layer, graphics, location);
					final Rectangle bounds = overlay.getBounds();

					if (bounds.isEmpty()) {
						continue;
					}

					if (inOverlayDraggingMode) {
						final Color previous = graphics.getColor();
						graphics.setColor(movedOverlay == overlay ? MOVING_OVERLAY_ACTIVE_COLOR : MOVING_OVERLAY_COLOR);
						graphics.draw(bounds);
						graphics.setColor(previous);
					}
				}
			}
		}
	}

	@Override
	public MouseEvent mouseClicked(MouseEvent mouseEvent) {
		return null;
	}

	@Override
	public MouseEvent mousePressed(MouseEvent mouseEvent)
	{
		if (!inOverlayDraggingMode)
		{
			return mouseEvent;
		}

		final Point mousePoint = mouseEvent.getPoint();
		mousePosition.setLocation(mousePoint);

		synchronized (overlayManager)
		{
			for (Overlay overlay : overlayManager.getOverlays())
			{
				if (overlay.getBounds().contains(mousePoint))
				{
					if (SwingUtilities.isRightMouseButton(mouseEvent))
					{
						overlayManager.resetOverlay(overlay);
					}
					else
					{
						final Point offset = new Point(mousePoint.x, mousePoint.y);
						offset.translate(-overlay.getBounds().x, -overlay.getBounds().y);
						overlayOffset.setLocation(offset);

						mousePoint.translate(-offset.x, -offset.y);
						movedOverlay = overlay;
						movedOverlay.setPreferredPosition(null);
						movedOverlay.setPreferredLocation(mousePoint);
						overlayManager.saveOverlay(movedOverlay);
					}

					mouseEvent.consume();
					break;
				}
			}
		}

		return mouseEvent;
	}

	@Override
	public MouseEvent mouseDragged(MouseEvent mouseEvent)
	{
		if (!inOverlayDraggingMode)
		{
			return mouseEvent;
		}

		final Client client = clientProvider.get();

		if (client == null)
		{
			return mouseEvent;
		}

		final Point mousePoint = mouseEvent.getPoint();
		mousePosition.setLocation(mousePoint);
		final Rectangle canvasRect = new Rectangle(client.getRealDimensions());

		if (!canvasRect.contains(mousePoint))
		{
			return mouseEvent;
		}

		if (movedOverlay != null)
		{
			mousePoint.translate(-overlayOffset.x, -overlayOffset.y);
			movedOverlay.setPreferredPosition(null);
			movedOverlay.setPreferredLocation(mousePoint);
			mouseEvent.consume();
		}

		return mouseEvent;
	}

	@Override
	public MouseEvent mouseMoved(MouseEvent mouseEvent) {
		return null;
	}

	@Override
	public MouseEvent mouseReleased(MouseEvent mouseEvent)
	{
		if (movedOverlay != null)
		{
			mousePosition.setLocation(-1, -1);

			// do not snapcorner detached overlays
			if (movedOverlay.getPosition() != OverlayPosition.DETACHED)
			{
				final OverlayBounds snapCorners = this.snapCorners.translated(-SNAP_CORNER_SIZE.width, -SNAP_CORNER_SIZE.height);

				for (Rectangle snapCorner : snapCorners.getBounds())
				{
					if (snapCorner.contains(mouseEvent.getPoint()))
					{
						OverlayPosition position = snapCorners.fromBounds(snapCorner);
						if(position != null) {
                            out.println("new OverlayPosition=" + position.name());
							if (position == movedOverlay.getPosition()) {
								// overlay moves back to default position
								position = null;
							}

							movedOverlay.setPreferredPosition(position);
							break;
						} else {
							movedOverlay.setPreferredPosition(OverlayPosition.TOP_CENTER);
						}
					}
				}
				movedOverlay.setPreferredLocation(null); // from dragging
			}

			overlayManager.saveOverlay(movedOverlay);
			movedOverlay = null;
			mouseEvent.consume();
		}

		return mouseEvent;
	}

	@Override
	public MouseEvent mouseEntered(MouseEvent mouseEvent) {
		return null;
	}

	@Override
	public MouseEvent mouseExited(MouseEvent mouseEvent) {
		return null;
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if (e.isAltDown())
		{
			inOverlayDraggingMode = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		if (!e.isAltDown())
		{
			inOverlayDraggingMode = false;
		}
	}

	private void safeRender(Client client, Overlay overlay, OverlayLayer layer, Graphics2D graphics, Point point)
	{
		final Graphics2D subGraphics = (Graphics2D) graphics.create();

		if (!isResizeable && (layer == OverlayLayer.ABOVE_SCENE || layer == OverlayLayer.UNDER_WIDGETS))
		{
			subGraphics.setClip(0,
				0,
				client.getViewportWidth(),
				client.getViewportHeight());
		}

		final OverlayPosition position = overlay.getPosition();

		// Set font based on configuration
		if (position == OverlayPosition.DYNAMIC)
		{
			subGraphics.setFont(runeLiteConfig.fontType().getFont());
		}
		else if (position == OverlayPosition.TOOLTIP)
		{
			subGraphics.setFont(runeLiteConfig.tooltipFontType().getFont());
		}
		else
		{
			subGraphics.setFont(FontManager.getRunescapeFont());
		}

		subGraphics.translate(point.x, point.y);
		final Dimension dimension = MoreObjects.firstNonNull(overlay.render(subGraphics), new Dimension());
		subGraphics.dispose();
		overlay.setBounds(new Rectangle(point, dimension));
	}

	private boolean shouldInvalidateBounds()
	{
		final Client client = clientProvider.get();
		final Rectangle chatbox = client.getChatboxBounds();
		final boolean resizeableChanged = isResizeable != client.isResized();
		boolean changed = false;

		if (resizeableChanged)
		{
			isResizeable = client.isResized();
			changed = true;
		}

		final boolean chatboxBoundsChanged = !chatbox.getBounds().equals(chatboxBounds);

		if (chatboxBoundsChanged)
		{
			chatboxBounds = chatbox.getBounds();
			changed = true;
		}

		final boolean viewportChanged = !client.getViewportBounds().getBounds().equals(viewportBounds);

		if (viewportChanged)
		{
			viewportBounds = client.getViewportBounds().getBounds();
			changed = true;
		}

		return changed;
	}

	private OverlayBounds buildSnapCorners()
	{
		final Client client = clientProvider.get();
		final Point topLeftPoint = new Point(
			isResizeable ? BORDER_LEFT_RESIZABLE : BORDER_LEFT_FIXED,
			isResizeable ? BORDER_TOP_RESIZABLE : BORDER_TOP_FIXED);

		final Point topCenterPoint = new Point(
				viewportBounds.width / 2,
				BORDER_LEFT_FIXED
		);

		final Point topRightPoint = new Point(
			viewportBounds.x + viewportBounds.width - BORDER_RIGHT,
			BORDER_TOP_FIXED);

		final Point bottomLeftPoint = new Point(
			isResizeable ? BORDER_LEFT_RESIZABLE : BORDER_LEFT_FIXED,
			viewportBounds.y + viewportBounds.height - BORDER_BOTTOM);

		final Point bottomRightPoint = new Point(
			viewportBounds.x + viewportBounds.width - BORDER_RIGHT,
			viewportBounds.y + viewportBounds.height - BORDER_BOTTOM);

		if (isResizeable)
			viewportBounds = new Rectangle(0, 0, 516, client.getViewportHeight() - 168);

		final Point rightChatboxPoint = new Point(
				viewportBounds.x + chatboxBounds.width - BORDER_RIGHT,
				viewportBounds.y + viewportBounds.height - BORDER_BOTTOM);

		// Check to see if chat box is minimized
		if (isResizeable && chatboxHidden)
		{
			rightChatboxPoint.y += chatboxBounds.height;
			bottomLeftPoint.y += chatboxBounds.height;
		}

		return new OverlayBounds(
			new Rectangle(topLeftPoint, SNAP_CORNER_SIZE),
			new Rectangle(topCenterPoint, SNAP_CORNER_SIZE),
			new Rectangle(topRightPoint, SNAP_CORNER_SIZE),
			new Rectangle(bottomLeftPoint, SNAP_CORNER_SIZE),
			new Rectangle(bottomRightPoint, SNAP_CORNER_SIZE),
			new Rectangle(rightChatboxPoint, SNAP_CORNER_SIZE));
	}
}
