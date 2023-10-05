package net.runelite.client.ui.skin;

import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.ui.overlay.*;

import javax.inject.Inject;
import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class WidgetItemOverlay extends Overlay {

    @Inject
    private OverlayManager overlayManager;
    /**
     * Interfaces to draw overlay over.
     */
    private final Set<Integer> interfaceGroups = new HashSet<>();

    protected WidgetItemOverlay()
    {
        super.setPosition(OverlayPosition.DYNAMIC);
        super.setPriority(OverlayPriority.LOW);
        super.setLayer(OverlayLayer.ABOVE_WIDGETS);
    }

    public abstract void renderItemOverlay(Graphics2D graphics, int itemId, WidgetItem itemWidget);

            @Override
            public Dimension render(Graphics2D graphics)
            {
                final List<WidgetItem> itemWidgets = overlayManager.getItemWidgets();
                for (WidgetItem widgetItem : itemWidgets)
                {
                    Widget widget = widgetItem.getWidget();
            int interfaceGroup = widget.getParentId();

            // Don't draw if this widget isn't one of the allowed
            if (!interfaceGroups.contains(interfaceGroup))
            {
               continue;
            }

            renderItemOverlay(graphics, widgetItem.getId(), widgetItem);
        }
        return null;
    }

    protected void showOnInventory()
    {
        showOnInterfaces(3213);
    }

    protected void showOnBank()
    {
        showOnInterfaces(5292);
    }

    protected void showOnEquipment()
    {
        showOnInterfaces(18691);
    }

    protected void showOnInterfaces(int... ids)
    {
        Arrays.stream(ids).forEach(interfaceGroups::add);
    }

    // Don't allow setting position, priority, or layer

    @Override
    public void setPosition(OverlayPosition position)
    {
        throw new IllegalStateException();
    }

    @Override
    public void setPriority(OverlayPriority priority)
    {
        throw new IllegalStateException();
    }

    @Override
    public void setLayer(OverlayLayer layer)
    {
        throw new IllegalStateException();
    }

    void setOverlayManager(OverlayManager overlayManager) {
        this.overlayManager = overlayManager;
    }
}
