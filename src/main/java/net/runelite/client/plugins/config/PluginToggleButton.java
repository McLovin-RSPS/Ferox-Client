package net.runelite.client.plugins.config;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import net.runelite.client.util.ImageUtil;
import net.runelite.client.util.SwingUtil;

class PluginToggleButton extends JToggleButton
{
    private static final ImageIcon ON_SWITCHER;
    private static final ImageIcon OFF_SWITCHER;

    static
    {
        BufferedImage onSwitcher = ImageUtil.loadImageResource(ConfigPanel.class, "switcher_on.png");
        ON_SWITCHER = new ImageIcon(onSwitcher);
        OFF_SWITCHER = new ImageIcon(ImageUtil.flipImage(
                ImageUtil.luminanceScale(
                        ImageUtil.grayscaleImage(onSwitcher),
                        0.61f
                ),
                true,
                false
        ));
    }

    public PluginToggleButton()
    {
        super(OFF_SWITCHER);
        setSelectedIcon(ON_SWITCHER);
        SwingUtil.removeButtonDecorations(this);
        setPreferredSize(new Dimension(25, 0));
        SwingUtil.addModalTooltip(this, "Disable plugin", "Enable plugin");
    }
}