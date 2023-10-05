package net.runelite.client.ui.overlay;

import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.overlay.components.ComponentConstants;
import net.runelite.client.ui.overlay.components.PanelComponent;

import java.awt.*;

public abstract class OverlayPanel extends Overlay
{
    protected final PanelComponent panelComponent = new PanelComponent();

    /**
     * Enables/disables automatic clearing of {@link this#getPanelComponent()} children after rendering (enabled by default)
     */
    private boolean clearChildren = true;

    /**
     * Enables/disables automatic font size changes based on panel component size relative to default panel component size.
     */
    private boolean dynamicFont = false;

    /**
     * Preferred color used for panel component background
     */
    private Color preferredColor = null;

    protected OverlayPanel()
    {
        super();
        setResizable(true);
    }

    @Override
    public Dimension render(final Graphics2D graphics)
    {
        final Dimension oldSize = panelComponent.getPreferredSize();

        if (getPreferredSize() != null)
        {
            panelComponent.setPreferredSize(getPreferredSize());

            if (dynamicFont)
            {
                if (getPreferredSize().width >= ComponentConstants.STANDARD_WIDTH * 1.3)
                {
                    graphics.setFont(FontManager.getRunescapeBoldFont());
                }
                else if (getPreferredSize().width <= ComponentConstants.STANDARD_WIDTH * 0.8)
                {
                    graphics.setFont(FontManager.getRunescapeSmallFont());
                }
            }
        }

        final Color oldBackgroundColor = panelComponent.getBackgroundColor();

        if (getPreferredColor() != null && ComponentConstants.STANDARD_BACKGROUND_COLOR.equals(oldBackgroundColor))
        {
            panelComponent.setBackgroundColor(getPreferredColor());
        }

        final Dimension dimension = panelComponent.render(graphics);

        if (clearChildren)
        {
            panelComponent.getChildren().clear();
        }

        panelComponent.setPreferredSize(oldSize);
        panelComponent.setBackgroundColor(oldBackgroundColor);
        return dimension;
    }

    public PanelComponent getPanelComponent() {
        return this.panelComponent;
    }

    public boolean isClearChildren() {
        return this.clearChildren;
    }

    public boolean isDynamicFont() {
        return this.dynamicFont;
    }

    public Color getPreferredColor() {
        return this.preferredColor;
    }

    public void setClearChildren(boolean clearChildren) {
        this.clearChildren = clearChildren;
    }

    public void setDynamicFont(boolean dynamicFont) {
        this.dynamicFont = dynamicFont;
    }

    public void setPreferredColor(Color preferredColor) {
        this.preferredColor = preferredColor;
    }
}
