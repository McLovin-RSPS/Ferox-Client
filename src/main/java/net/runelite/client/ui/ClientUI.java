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
package net.runelite.client.ui;

import net.runelite.api.Client;
import net.runelite.api.Constants;
import net.runelite.api.GameState;
import net.runelite.api.Point;
import net.runelite.api.events.ConfigChanged;
import net.runelite.client.RuneLite;
import net.runelite.client.RuneLiteProperties;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.config.ExpandResizeType;
import net.runelite.client.config.RuneLiteConfig;
import net.runelite.client.config.WarningOnExit;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.PluginToolbarButtonAdded;
import net.runelite.client.events.PluginToolbarButtonRemoved;
import net.runelite.client.events.TitleToolbarButtonAdded;
import net.runelite.client.events.TitleToolbarButtonRemoved;
import net.runelite.client.input.KeyManager;
import net.runelite.client.ui.skin.SubstanceRuneLiteLookAndFeel;
import net.runelite.client.util.*;
import org.pushingpixels.substance.internal.SubstanceSynapse;
import org.pushingpixels.substance.internal.utils.SubstanceCoreUtilities;
import org.pushingpixels.substance.internal.utils.SubstanceTitlePaneUtilities;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;


/**
 * Client UI.
 */
@Singleton
public class ClientUI
{
	private static final String CONFIG_GROUP = "runelite";
	private static final String CONFIG_CLIENT_BOUNDS = "clientBounds";
	private static final String CONFIG_CLIENT_MAXIMIZED = "clientMaximized";
	private static final int CLIENT_WELL_HIDDEN_MARGIN = 160;
	private static final int CLIENT_WELL_HIDDEN_MARGIN_TOP = 10;
	public static final BufferedImage ICON;
	private static final BufferedImage SIDEBAR_OPEN;
	private static final BufferedImage SIDEBAR_CLOSE;
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(ClientUI.class);

	static
	{
		BufferedImage icon;
		BufferedImage sidebarOpen;
		BufferedImage sidebarClose;

		try
		{
			synchronized (ImageIO.class)
			{
				icon = ImageIO.read(ClientUI.class.getResourceAsStream("icon.png"));
				sidebarOpen = ImageIO.read(ClientUI.class.getResourceAsStream("open.png"));
				sidebarClose = ImageIO.read(ClientUI.class.getResourceAsStream("close.png"));
			}
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}

		ICON = icon;
		SIDEBAR_OPEN = sidebarOpen;
		SIDEBAR_CLOSE = sidebarClose;
	}


	private final RuneLiteProperties properties;
	public final RuneLiteConfig config;
	private final KeyManager keyManager;
	private final Canvas client;
	private final ConfigManager configManager;
	private final CardLayout cardLayout = new CardLayout();
	public static ContainableFrame frame;
	private JPanel navContainer;
	private PluginPanel pluginPanel;
	private ClientPluginToolbar pluginToolbar;
	private ClientTitleToolbar titleToolbar;
	private JButton currentButton;
	private NavigationButton currentNavButton;
	private boolean sidebarOpen;
	public static JPanel container;
	private NavigationButton sidebarNavigationButton;
	private JButton sidebarNavigationJButton;
	private Dimension lastClientSize;

	@Inject
	private ClientUI(
		RuneLiteProperties properties,
		RuneLiteConfig config,
		KeyManager keyManager,
		@Nullable Canvas client,
		ConfigManager configManager)
	{
		this.properties = properties;
		this.config = config;
		this.keyManager = keyManager;
		this.client = client;
		this.configManager = configManager;
	}

	public static ContainableFrame getFrame() {
		return ClientUI.frame;
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		if (!event.getGroup().equals("runelite") ||
			event.getKey().equals(CONFIG_CLIENT_MAXIMIZED) ||
			event.getKey().equals(CONFIG_CLIENT_BOUNDS))
		{
			return;
		}

		SwingUtilities.invokeLater(this::updateFrameConfig);
	}

	@Subscribe
	public void onPluginToolbarButtonAdded(final PluginToolbarButtonAdded event)
	{
		SwingUtilities.invokeLater(() ->
		{
			final NavigationButton navigationButton = event.getButton();
			final PluginPanel pluginPanel = navigationButton.getPanel();

			if (pluginPanel != null)
			{
				navContainer.add(pluginPanel.getWrappedPanel(), navigationButton.getTooltip());
			}

			final JButton button = SwingUtil.createSwingButton(navigationButton, 0, (navButton, jButton) ->
			{
				final PluginPanel panel = navButton.getPanel();

				if (panel == null)
				{
					return;
				}

				boolean doClose = currentButton != null && currentButton == jButton && currentButton.isSelected();

				if (doClose)
				{
					contract();
					currentButton.setSelected(false);
					currentNavButton.setSelected(false);
					currentButton = null;
					currentNavButton = null;
				}
				else
				{
					if (currentButton != null)
					{
						currentButton.setSelected(false);
					}

					if (currentNavButton != null)
					{
						currentNavButton.setSelected(false);
					}

					currentButton = jButton;
					currentNavButton = navButton;
					currentButton.setSelected(true);
					currentNavButton.setSelected(true);
					expand(navButton);
				}
			});

			pluginToolbar.addComponent(event.getIndex(), event.getButton(), button);
		});
	}

	@Subscribe
	public void onPluginToolbarButtonRemoved(final PluginToolbarButtonRemoved event)
	{
		SwingUtilities.invokeLater(() ->
		{
			pluginToolbar.removeComponent(event.getButton());
			final PluginPanel pluginPanel = event.getButton().getPanel();

			if (pluginPanel != null)
			{
				navContainer.remove(pluginPanel.getWrappedPanel());
			}
		});
	}

	@Subscribe
	public void onTitleToolbarButtonAdded(final TitleToolbarButtonAdded event)
	{
		SwingUtilities.invokeLater(() ->
		{
			final int iconSize = ClientTitleToolbar.TITLEBAR_SIZE - 6;
			final JButton button = SwingUtil.createSwingButton(event.getButton(), iconSize, null);

			if (config.enableCustomChrome() || SwingUtil.isCustomTitlePanePresent(frame))
			{
				titleToolbar.addComponent(event.getButton(), button);
				return;
			}

			pluginToolbar.addComponent(-1, event.getButton(), button);
		});
	}

	@Subscribe
	public void onTitleToolbarButtonRemoved(final TitleToolbarButtonRemoved event)
	{
		SwingUtilities.invokeLater(() ->
		{
			if (config.enableCustomChrome() || SwingUtil.isCustomTitlePanePresent(frame))
			{
				titleToolbar.removeComponent(event.getButton());
				return;
			}

			pluginToolbar.removeComponent(event.getButton());
		});
	}

	/**
	 * Initialize UI.
	 *
	 * @param runelite runelite instance that will be shut down on exit
	 * @throws Exception exception that can occur during creation of the UI
	 */
	public void open(final RuneLite runelite) throws Exception
	{
		try {
			SwingUtilities.invokeAndWait(() ->
			{
				SplashScreen.stop();
				// Set some sensible swing defaults
				SwingUtil.setupDefaults();

				// Use substance look and feel
				SwingUtil.setTheme(new SubstanceRuneLiteLookAndFeel());

				// Use custom UI font
				SwingUtil.setFont(FontManager.getRunescapeFont());

				// Create main window
				frame = new ContainableFrame();

				// Try to enable fullscreen on OSX
				OSXUtil.tryEnableFullscreen(frame);


				try {
					frame.setIconImage(ImageIO.read(ClientUI.class.getResourceAsStream("icon.png")));
				} catch (IOException e) {
					e.printStackTrace();
				}

				frame.setTitle("Ancestral - Powered by RuneLite");
				frame.getLayeredPane().setCursor(Cursor.getDefaultCursor());
				frame.setLocationRelativeTo(frame.getOwner());
				frame.setResizable(true);

				SwingUtil.addGracefulExitCallback(frame,
						() ->
						{
							saveClientBoundsConfig();
							runelite.shutdown();
						},
						this::showWarningOnExit
				);

				container = new JPanel();
				container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
				container.add(new ClientPanel(client));

				navContainer = new JPanel();
				navContainer.setLayout(cardLayout);
				navContainer.setMinimumSize(new Dimension(0, 0));
				navContainer.setMaximumSize(new Dimension(0, 0));
				navContainer.setPreferredSize(new Dimension(0, 0));

				// To reduce substance's colorization (tinting)
				navContainer.putClientProperty(SubstanceSynapse.COLORIZATION_FACTOR, 1.0);
				container.add(navContainer);

				pluginToolbar = new ClientPluginToolbar();
				titleToolbar = new ClientTitleToolbar();
				frame.add(container);

				// Add key listener
				final UiKeyListener uiKeyListener = new UiKeyListener(this);
				frame.addKeyListener(uiKeyListener);
				keyManager.registerKeyListener(uiKeyListener);
				client.setFocusTraversalKeysEnabled(false);
				frame.setFocusTraversalKeysEnabled(false);



				// Update config
				updateFrameConfig();

				// Decorate window with custom chrome and titlebar if needed
				final boolean withTitleBar = config.enableCustomChrome();
				frame.setUndecorated(withTitleBar);

				if (withTitleBar)
				{
					frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);

					final JComponent titleBar = SubstanceCoreUtilities.getTitlePaneComponent(frame);
					titleToolbar.putClientProperty(SubstanceTitlePaneUtilities.EXTRA_COMPONENT_KIND, SubstanceTitlePaneUtilities.ExtraComponentKind.TRAILING);
					titleBar.add(titleToolbar);

					// Substance's default layout manager for the title bar only lays out substance's components
					// This wraps the default manager and lays out the TitleToolbar as well.
					LayoutManager delegate = titleBar.getLayout();
					titleBar.setLayout(new LayoutManager()
					{
						@Override
						public void addLayoutComponent(String name, Component comp)
						{
							delegate.addLayoutComponent(name, comp);
						}

						@Override
						public void removeLayoutComponent(Component comp)
						{
							delegate.removeLayoutComponent(comp);
						}

						@Override
						public Dimension preferredLayoutSize(Container parent)
						{
							return delegate.preferredLayoutSize(parent);
						}

						@Override
						public Dimension minimumLayoutSize(Container parent)
						{
							return delegate.minimumLayoutSize(parent);
						}

						@Override
						public void layoutContainer(Container parent)
						{
							delegate.layoutContainer(parent);
							final int width = titleToolbar.getPreferredSize().width;
							titleToolbar.setBounds(titleBar.getWidth() - 75 - width, 0, width, titleBar.getHeight());
						}
					});
				}

				// Show frame
				frame.pack();
				//frame.revalidateMinimumSize();

				if (config.rememberScreenBounds())
				{
					try
					{
						Rectangle clientBounds = configManager.getConfiguration(
								CONFIG_GROUP, CONFIG_CLIENT_BOUNDS, Rectangle.class);
						if (clientBounds != null)
						{
							frame.setBounds(clientBounds);
						}
						else
						{
							frame.setLocationRelativeTo(frame.getOwner());
						}

						if (configManager.getConfiguration(CONFIG_GROUP, CONFIG_CLIENT_MAXIMIZED) != null)
						{
							frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
						}
					}
					catch (Exception ex)
					{
						log.warn("Failed to set window bounds", ex);
						frame.setLocationRelativeTo(frame.getOwner());
					}
				}
				else
				{
					frame.setLocationRelativeTo(frame.getOwner());
				}

				frame.setVisible(true);

				com.ferox.Client.instance.initialize();
				//client.start();

				// If the frame is well hidden (e.g. unplugged 2nd screen),
				// we want to move it back to default position as it can be
				// hard for the user to reposition it themselves otherwise.
				Rectangle clientBounds = frame.getBounds();
				Rectangle screenBounds = frame.getGraphicsConfiguration().getBounds();
				if (clientBounds.x + clientBounds.width - CLIENT_WELL_HIDDEN_MARGIN < screenBounds.getX() ||
						clientBounds.x + CLIENT_WELL_HIDDEN_MARGIN > screenBounds.getX() + screenBounds.getWidth() ||
						clientBounds.y + CLIENT_WELL_HIDDEN_MARGIN_TOP < screenBounds.getY() ||
						clientBounds.y + CLIENT_WELL_HIDDEN_MARGIN > screenBounds.getY() + screenBounds.getHeight())
				{
					frame.setLocationRelativeTo(frame.getOwner());
				}

				// Create hide sidebar button
				sidebarNavigationButton = NavigationButton
						.builder()
						.icon(SIDEBAR_CLOSE)
						.onClick(this::toggleSidebar)
						.build();

				sidebarNavigationJButton = SwingUtil.createSwingButton(
						sidebarNavigationButton,
						0,
						null);

				titleToolbar.addComponent(sidebarNavigationButton, sidebarNavigationJButton);
				toggleSidebar();
				log.info("Showing frame {}", frame);
			});
		} catch (Exception e) {
			log.error("Error loading client UI", e);
		}
	}

	private static Cursor defaultCursor;

	/**
	 * Returns current cursor set on game container
	 * @return awt cursor
	 */
	public Cursor getCurrentCursor()
	{
		return container.getCursor();
	}

	/**
	 * Returns current custom cursor or default system cursor if cursor is not set
	 * @return awt cursor
	 */
	public Cursor getDefaultCursor()
	{
		return defaultCursor != null ? defaultCursor : Cursor.getDefaultCursor();
	}

	/**
	 * Changes cursor for client window. Requires ${@link ClientUI#init()} to be called first.
	 * FIXME: This is working properly only on Windows, Linux and Mac are displaying cursor incorrectly
	 * @param id get ID
	 */
	public static void setCursor(int id)
	{
		if (container == null)
		{
			return;
		}
	}

	/**
	 * Changes cursor for client window. Requires ${@link ClientUI#init()} to be called first.
	 * @param cursor awt cursor
	 */
	public static void setCursor(final Cursor cursor)
	{
		container.setCursor(cursor);
	}

	/**
	 * Resets client window cursor to default one.
	 * @see ClientUI#setCursor(BufferedImage, String)
	 */
	public static void resetCursor()
	{
		if (container == null)
		{
			return;
		}

		defaultCursor = null;
		container.setCursor(Cursor.getDefaultCursor());
	}


	private boolean showWarningOnExit()
	{
		if (config.warningOnExit() == WarningOnExit.ALWAYS)
		{
			return true;
		}

		if (config.warningOnExit() == WarningOnExit.LOGGED_IN && client instanceof Client)
		{
			return ((Client) client).getGameState() != GameState.LOGIN_SCREEN;
		}

		return false;
	}

	/**
	 * Paint this component to target graphics
	 *
	 * @param graphics the graphics
	 */
	public void paint(final Graphics graphics)
	{
		frame.paint(graphics);
	}

	/**
	 * Gets component width.
	 *
	 * @return the width
	 */
	public int getWidth()
	{
		return frame.getWidth();
	}

	/**
	 * Gets component height.
	 *
	 * @return the height
	 */
	public int getHeight()
	{
		return frame.getHeight();
	}

	/**
	 * Returns true if this component has focus.
	 *
	 * @return true if component has focus
	 */
	public boolean isFocused()
	{
		return frame.isFocused();
	}

	/**
	 * Request focus on this component and then on client component
	 */
	public void requestFocus()
	{
		if (OSType.getOSType() == OSType.MacOS)
		{
			OSXUtil.requestFocus();
		}

		frame.requestFocus();
		giveClientFocus();
	}

	/**
	 * Get offset of game canvas in game window
	 *
	 * @return game canvas offset
	 */
	public Point getCanvasOffset()
	{
		if (client instanceof Client)
		{
			final java.awt.Point point = SwingUtilities.convertPoint(com.ferox.Client.instance.getGameComponent(), 0, 0, frame);
			return new Point(point.x, point.y);
		}

		return new Point(0, 0);
	}

	public GraphicsConfiguration getGraphicsConfiguration()
	{
		return frame.getGraphicsConfiguration();
	}

	void toggleSidebar()
	{
		// Toggle sidebar open
		boolean isSidebarOpen = sidebarOpen;
		sidebarOpen = !sidebarOpen;

		// Select/deselect buttons
		if (currentButton != null)
		{
			currentButton.setSelected(sidebarOpen);
		}

		if (currentNavButton != null)
		{
			currentNavButton.setSelected(sidebarOpen);
		}

		if (isSidebarOpen)
		{
			sidebarNavigationJButton.setIcon(new ImageIcon(SIDEBAR_OPEN));
			sidebarNavigationJButton.setToolTipText("Open SideBar");

			contract();

			// Remove plugin toolbar
			container.remove(pluginToolbar);
		}
		else
		{
			sidebarNavigationJButton.setIcon(new ImageIcon(SIDEBAR_CLOSE));
			sidebarNavigationJButton.setToolTipText("Close SideBar");

			// Try to restore last panel
			expand(currentNavButton);

			// Add plugin toolbar back
			container.add(pluginToolbar);
		}

		// Revalidate sizes of affected Swing components
		container.revalidate();
		giveClientFocus();

		if (sidebarOpen)
		{
			frame.expandBy(pluginToolbar.getWidth());
		}
		else
		{
			frame.contractBy(pluginToolbar.getWidth());
		}
	}
	public void forceFocus()
	{
		switch (OSType.getOSType())
		{
			case MacOS:
				OSXUtil.requestFocus();
				break;
			case Windows:
				WinUtil.requestForeground(frame);
				break;
			default:
				frame.requestFocus();
				break;
		}

		giveClientFocus();
	}
	private void expand(@Nullable NavigationButton button)
	{
		if (button == null)
		{
			return;
		}

		final PluginPanel panel = button.getPanel();

		if (panel == null)
		{
			return;
		}

		if (!sidebarOpen)
		{
			toggleSidebar();
		}

		int width = panel.getWrappedPanel().getPreferredSize().width;
		int expandBy = pluginPanel != null ? pluginPanel.getWrappedPanel().getPreferredSize().width - width : width;
		pluginPanel = panel;

		// Expand sidebar
		navContainer.setMinimumSize(new Dimension(width, 0));
		navContainer.setMaximumSize(new Dimension(width, Integer.MAX_VALUE));
		navContainer.setPreferredSize(new Dimension(width, 0));
		navContainer.revalidate();
		cardLayout.show(navContainer, button.getTooltip());

		// panel.onActivate has to go after giveClientFocus so it can get focus if it needs.
		giveClientFocus();
		panel.onActivate();

		// Check if frame was really expanded or contracted
		if (expandBy > 0)
		{
			frame.expandBy(expandBy);
		}
		else if (expandBy < 0)
		{
			frame.contractBy(expandBy);
		}
	}

	private void contract()
	{
		if (pluginPanel == null)
		{
			return;
		}

		pluginPanel.onDeactivate();
		navContainer.setMinimumSize(new Dimension(0, 0));
		navContainer.setMaximumSize(new Dimension(0, 0));
		navContainer.setPreferredSize(new Dimension(0, 0));
		navContainer.revalidate();
		giveClientFocus();
		frame.contractBy(pluginPanel.getWrappedPanel().getPreferredSize().width);
		pluginPanel = null;
	}

	private void giveClientFocus()
	{
		if (client instanceof Client)
		{
			final Component c = ((Client) client).getCanvas();
			c.requestFocusInWindow();
		}
		else if (client != null)
		{
			client.requestFocusInWindow();
		}
	}

	private void updateFrameConfig()
	{
		if (frame == null)
		{
			return;
		}

		if (frame.isAlwaysOnTopSupported())
		{
			frame.setAlwaysOnTop(config.gameAlwaysOnTop());
		}

		frame.setResizable(!config.lockWindowSize());
		frame.setExpandResizeType(config.automaticResizeType());
		frame.setContainedInScreen(config.containInScreen() && config.enableCustomChrome());

		if (!config.rememberScreenBounds())
		{
			configManager.unsetConfiguration(CONFIG_GROUP, CONFIG_CLIENT_MAXIMIZED);
			configManager.unsetConfiguration(CONFIG_GROUP, CONFIG_CLIENT_BOUNDS);
		}

		if (client == null)
		{
			return;
		}

		// The upper bounds are defined by the applet's max size
		// The lower bounds are defined by the client's fixed size
		int width = Math.max(Math.min(config.gameSize().width, 7680), Constants.GAME_FIXED_WIDTH);
		int height = Math.max(Math.min(config.gameSize().height, 2160), Constants.GAME_FIXED_HEIGHT);
		final Dimension size = new Dimension(width, height);

		if (!size.equals(lastClientSize))
		{
			lastClientSize = size;
			client.setSize(size);
			client.setPreferredSize(size);
			client.getParent().setPreferredSize(size);
			client.getParent().setSize(size);

			if (frame.isVisible())
			{
				frame.pack();
			}
		}
	}

	private void saveClientBoundsConfig()
	{
		if ((frame.getExtendedState() & JFrame.MAXIMIZED_BOTH) != 0)
		{
			configManager.setConfiguration(CONFIG_GROUP, CONFIG_CLIENT_MAXIMIZED, true);
		}
		else
		{
			final Rectangle bounds = frame.getBounds();

			if (config.automaticResizeType() == ExpandResizeType.KEEP_GAME_SIZE)
			{
				// Try to contract sidebar
				if (sidebarOpen)
				{
					bounds.width -= pluginToolbar.getWidth();
				}

				// Try to contract plugin panel
				if (pluginPanel != null)
				{
					bounds.width -= pluginPanel.getWrappedPanel().getPreferredSize().width;
				}
			}

			configManager.unsetConfiguration(CONFIG_GROUP, CONFIG_CLIENT_MAXIMIZED);
			configManager.setConfiguration(CONFIG_GROUP, CONFIG_CLIENT_BOUNDS, bounds);
		}
	}
	private GraphicsConfiguration findDisplayFromBounds(final Rectangle bounds)
	{
		GraphicsDevice[] gds = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();

		for (GraphicsDevice gd : gds)
		{
			GraphicsConfiguration gc = gd.getDefaultConfiguration();

			final Rectangle displayBounds = gc.getBounds();
			if (displayBounds.contains(bounds))
			{
				return gc;
			}
		}

		return null;
	}

	public static void setSize(int width, int height, boolean resize) {

		frame.setSize(new Dimension(width,height));
		frame.setResizable(resize);

	}

	public void show()
	{
		SwingUtilities.invokeLater(() ->
		{
			// Layout frame
			frame.pack();
			frame.revalidateMinimumSize();

			// Move frame around (needs to be done after frame is packed)
			if (config.rememberScreenBounds())
			{
				try
				{
					Rectangle clientBounds = configManager.getConfiguration(
							CONFIG_GROUP, CONFIG_CLIENT_BOUNDS, Rectangle.class);
					if (clientBounds != null)
					{
						frame.setBounds(clientBounds);

						// frame.getGraphicsConfiguration().getBounds() returns the bounds for the primary display.
						// We have to find the correct graphics configuration by using the client boundaries.
						GraphicsConfiguration gc = findDisplayFromBounds(clientBounds);
						if (gc != null)
						{
							double scale = gc.getDefaultTransform().getScaleX();

							// When Windows screen scaling is on, the position/bounds will be wrong when they are set.
							// The bounds saved in shutdown are the full, non-scaled co-ordinates.
							if (scale != 1)
							{
								clientBounds.setRect(
										clientBounds.getX() / scale,
										clientBounds.getY() / scale,
										clientBounds.getWidth() / scale,
										clientBounds.getHeight() / scale);

								frame.setMinimumSize(clientBounds.getSize());
								frame.setBounds(clientBounds);
							}
						}
						else
						{
							frame.setLocationRelativeTo(frame.getOwner());
						}
					}
					else
					{
						frame.setLocationRelativeTo(frame.getOwner());
					}

					if (configManager.getConfiguration(CONFIG_GROUP, CONFIG_CLIENT_MAXIMIZED) != null)
					{
						frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					}
				}
				catch (Exception ex)
				{
					log.warn("Failed to set window bounds", ex);
					frame.setLocationRelativeTo(frame.getOwner());
				}
			}
			else
			{
				frame.setLocationRelativeTo(frame.getOwner());
			}

			// Show frame
			frame.setVisible(true);
			frame.toFront();
			requestFocus();
			log.info("Showing frame {}", frame);
			frame.revalidateMinimumSize();
		});

		// Show out of date dialog if needed
		if (client != null && !(client instanceof Client))
		{
			SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(frame,
					"RuneLite has not yet been updated to work with the latest\n"
							+ "game update, it will work with reduced functionality until then.",
					"RuneLite is outdated", INFORMATION_MESSAGE));
		}
	}

}
