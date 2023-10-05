/*
 * Decompiled with CFR 0.150.
 */
package net.runelite.client.callback;

import com.google.inject.Injector;
import net.runelite.api.*;
import net.runelite.api.events.BeforeRender;
import net.runelite.api.events.GameTick;
import net.runelite.api.hooks.Callbacks;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.Notifier;
import net.runelite.client.RuneLite;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.input.KeyManager;
import net.runelite.client.input.MouseManager;
import net.runelite.client.task.Scheduler;
import net.runelite.client.ui.ClientUI;
import net.runelite.client.ui.DrawManager;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.OverlayRenderer;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import net.runelite.client.util.DeferredEventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;

import static java.lang.System.out;

@Singleton
public class Hooks
		implements Callbacks {
	private static final Logger log = LoggerFactory.getLogger(Hooks.class);
	private static final long CHECK = 600L;
	private static final Injector injector = RuneLite.getInjector();
	private static final GameTick GAME_TICK = new GameTick();
	@Inject
	private EventBus eventBus;
	@Inject
	private DeferredEventBus deferredEventBus;
	@Inject
	private Scheduler scheduler;
	@Inject
	private InfoBoxManager infoBoxManager;
	@Inject
	private ChatMessageManager chatMessageManager;
	@Inject
	private MouseManager mouseManager;
	@Inject
	private KeyManager keyManager;
	@Inject
	private ClientThread clientThread;
	@Inject
	private DrawManager drawManager;
	@Inject
	private Notifier notifier;
	@Inject
	private ClientUI clientUi;
	@Inject
	private Client client;
	@Inject
	private OverlayRenderer renderer;
	@Inject
	private OverlayManager overlayManager;
	private GameState lastGameState;
	private Dimension lastStretchedDimensions;
	private VolatileImage stretchedImage;
	private Graphics2D stretchedGraphics;
	private long lastCheck;
	private boolean shouldProcessGameTick;
	private static BufferProvider lastMainBufferProvider;
	private static Graphics2D lastGraphics;

	/**
	 * Get the Graphics2D for the MainBufferProvider image
	 * This caches the Graphics2D instance so it can be reused
	 * @param mainBufferProvider
	 * @return
	 */
	private static Graphics2D getGraphics(BufferProvider mainBufferProvider)
	{
		if (lastGraphics == null || lastMainBufferProvider != mainBufferProvider)
		{
			if (lastGraphics != null)
			{
				log.debug("Graphics reset!");
				lastGraphics.dispose();
			}

			lastMainBufferProvider = mainBufferProvider;
			lastGraphics = (Graphics2D) mainBufferProvider.getImage().getGraphics();
		}
		return lastGraphics;
	}
	@Override
	public void post(Object event) {
		this.eventBus.post(event);
	}

	@Override
	public void postDeferred(Object event) {
		this.deferredEventBus.post(event);
	}

	@Override
	public void clientMainLoop() {
		if (this.shouldProcessGameTick) {
			this.shouldProcessGameTick = false;
			this.deferredEventBus.replay();
			this.eventBus.post(GAME_TICK);
			int tick = this.client.getTickCount();
			this.client.setTickCount(tick + 1);
		}
		eventBus.post(new BeforeRender());
		//this.clientThread.invoke();
		long now = System.currentTimeMillis();
		if (now - this.lastCheck < 600L) {
			return;
		}
		this.lastCheck = now;
		try {
			this.scheduler.tick();
			this.infoBoxManager.cull();
			this.chatMessageManager.process();
		}
		catch (Exception ex) {
			log.warn("error during main loop tasks", ex);
			ex.printStackTrace();
		}
	}

	private void checkWorldMap() {
		Widget widget = this.client.getWidget(WidgetInfo.WORLD_MAP_VIEW);
		if (widget != null) {
			return;
		}
		RenderOverview renderOverview = this.client.getRenderOverview();
		if (renderOverview == null) {
			return;
		}
		WorldMapManager manager = renderOverview.getWorldMapManager();
		if (manager != null && manager.isLoaded()) {
            out.println("World map was closed, reinitializing");
			renderOverview.initializeWorldMap(renderOverview.getWorldMapData());
		}
	}

	@Override
	public MouseEvent mousePressed(MouseEvent mouseEvent) {
		return this.mouseManager.processMousePressed(mouseEvent);
	}

	@Override
	public MouseEvent mouseReleased(MouseEvent mouseEvent) {
		return this.mouseManager.processMouseReleased(mouseEvent);
	}

	@Override
	public MouseEvent mouseClicked(MouseEvent mouseEvent) {
		return this.mouseManager.processMouseClicked(mouseEvent);
	}

	@Override
	public MouseEvent mouseEntered(MouseEvent mouseEvent) {
		return this.mouseManager.processMouseEntered(mouseEvent);
	}

	@Override
	public MouseEvent mouseExited(MouseEvent mouseEvent) {
		return this.mouseManager.processMouseExited(mouseEvent);
	}

	@Override
	public MouseEvent mouseDragged(MouseEvent mouseEvent) {
		return this.mouseManager.processMouseDragged(mouseEvent);
	}

	@Override
	public MouseEvent mouseMoved(MouseEvent mouseEvent) {
		return this.mouseManager.processMouseMoved(mouseEvent);
	}

	@Override
	public MouseWheelEvent mouseWheelMoved(MouseWheelEvent event) {
		return this.mouseManager.processMouseWheelMoved(event);
	}

	@Override
	public void keyPressed(KeyEvent keyEvent) {
		this.keyManager.processKeyPressed(keyEvent);
	}

	@Override
	public void keyReleased(KeyEvent keyEvent) {
		this.keyManager.processKeyReleased(keyEvent);
	}

	@Override
	public void keyTyped(KeyEvent keyEvent) {
		this.keyManager.processKeyTyped(keyEvent);
	}

	@Override
	public void draw(BufferProvider mainBufferProvider, Graphics graphics) {
		if (graphics == null) {
			return;
		}
		Image image = mainBufferProvider.getImage();
		Graphics2D graphics2d = (Graphics2D)image.getGraphics();
		try {
			this.renderer.render(graphics2d, OverlayLayer.ALWAYS_ON_TOP, GraphicsBufferType.ALL);
		}
		catch (Exception ex) {
			log.warn("Error during overlay rendering", ex);
		}
		this.notifier.processFlash(graphics2d);
		if (!this.client.isResized() && this.client.isStretchedEnabled()) {
			GraphicsConfiguration gc = this.clientUi.getGraphicsConfiguration();
			Dimension stretchedDimensions = this.client.getStretchedDimensions();
			if (this.lastStretchedDimensions == null || !this.lastStretchedDimensions.equals(stretchedDimensions) || this.stretchedImage != null && this.stretchedImage.validate(gc) == 2) {
				this.stretchedImage = gc.createCompatibleVolatileImage(stretchedDimensions.width, stretchedDimensions.height);
				if (this.stretchedGraphics != null) {
					this.stretchedGraphics.dispose();
				}
				this.stretchedGraphics = (Graphics2D)this.stretchedImage.getGraphics();
				this.lastStretchedDimensions = stretchedDimensions;
				graphics.setColor(Color.BLACK);
				graphics.fillRect(0, 0, this.client.getCanvas().getWidth(), this.client.getCanvas().getHeight());
			}
			this.stretchedGraphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, this.client.isStretchedFast() ? RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR : RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			this.stretchedGraphics.drawImage(image, 0, 0, stretchedDimensions.width, stretchedDimensions.height, null);
			image = this.stretchedImage;
		}
		if(mainBufferProvider == client.getMainBufferProvider()) {
			graphics.drawImage(image, com.ferox.Client.screen == com.ferox.Client.ScreenMode.FIXED ? 4 : 0, com.ferox.Client.screen == com.ferox.Client.ScreenMode.FIXED ? 4 : 0, com.ferox.Client.instance.getGameComponent());
		} else {
			graphics.drawImage(image, 0, 0, com.ferox.Client.instance.getGameComponent());
		}
		this.drawManager.processDrawComplete(image);
		graphics2d.dispose();
	}



	/*
	 * WARNING - Removed try catching itself - possible behaviour change.
	 */
	@Override
	public void drawScene() {
		BufferProvider bufferProvider = this.client.getGameAreaBufferProvider();
		BufferedImage image = (BufferedImage)bufferProvider.getImage();
		Graphics2D graphics2d = image.createGraphics();
		try {
			this.renderer.render(graphics2d, OverlayLayer.ABOVE_SCENE, GraphicsBufferType.MAIN_GAME);
		}
		catch (Exception ex) {
			log.warn("Error during overlay rendering", ex);
		}
		finally {
			graphics2d.dispose();
		}
	}

	public void drawGame() {
		BufferProvider bufferProvider = client.getMainBufferProvider();
		BufferedImage image = (BufferedImage)bufferProvider.getImage();
		Graphics2D graphics2d = (Graphics2D) com.ferox.Client.instance.getGameComponent().getGraphics();
		try {
			draw(bufferProvider, graphics2d);
		} catch (Exception ex) {
            out.println(ex.toString());
		}
		finally {
			graphics2d.dispose();
		}
	}

	/*
	 * WARNING - Removed try catching itself - possible behaviour change.
	 */
	@Override
	public void drawAboveOverheads() {
		BufferProvider bufferProvider = this.client.getGameAreaBufferProvider();
		BufferedImage image = (BufferedImage)bufferProvider.getImage();
		Graphics2D graphics2d = image.createGraphics();
		try {
			this.renderer.render(graphics2d, OverlayLayer.UNDER_WIDGETS, GraphicsBufferType.MAIN_GAME);
		}
		catch (Exception ex) {
			log.warn("Error during overlay rendering", ex);
		}
		finally {
			graphics2d.dispose();
		}

	}

	/*
	 * WARNING - Removed try catching itself - possible behaviour change.
	 */
	@Override
	public void drawAfterWidgets(GraphicsBufferType bufferType) {
		BufferProvider bufferProvider = this.bufferProviderFor(bufferType);
		BufferedImage image = (BufferedImage)bufferProvider.getImage();
		Graphics2D graphics2d = image.createGraphics();
		try {
			this.renderer.render(graphics2d, OverlayLayer.ABOVE_WIDGETS, bufferType);
		}
		catch (Exception ex) {
			log.warn("Error during overlay rendering", ex);
		}
		finally {
			graphics2d.dispose();
		}
		this.overlayManager.getItemWidgets().clear();
	}

	private BufferProvider bufferProviderFor(GraphicsBufferType type) {
		switch (type) {
			case TAB_AREA: {
				return this.client.getTabAreaBufferProvider();
			}
			case MINIMAP: {
				return this.client.getMinimapBufferProvider();
			}
		}
		return this.client.getGameAreaBufferProvider();

	}

	@Override
	public void updateNpcs() {
		this.shouldProcessGameTick = true;
	}

	@Override
	public void drawItem(int itemId, WidgetItem widgetItem) {
		if (widgetItem.getId() != 6512) {
			this.overlayManager.getItemWidgets().add(widgetItem);
		}
	}
}

