package net.runelite.client.ui.components.slider;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.function.Consumer;

public class RawSlider extends JPanel
{
    static final int KNOB_WIDTH = 4;

    private static final int KNOB_HEIGHT = 14;
    private static final Color TRACK_COLOR = new Color(20, 20, 20);
    private static final Color KNOB_COLOR = new Color(150, 150, 150);

    private static final int KNOB_WIDTH_HALF = KNOB_WIDTH / 2;

    private final boolean allowWrapAround;

    public boolean beingDragged = false;
    public boolean ctrlPressed = false;
    public boolean shiftPressed = false;

    private float ratio = 0;
    private int initialX;

    public void setOnValueChanged(Consumer<Float> onValueChanged) {
        this.onValueChanged = onValueChanged;
    }

    private Consumer<Float> onValueChanged;

    RawSlider(boolean allowWrapAround)
    {
        this.allowWrapAround = allowWrapAround;

        this.setMinimumSize(new Dimension(50, KNOB_HEIGHT));
        addMouseMotionListener(new MouseMotionAdapter()
        {
            @Override
            public void mouseDragged(MouseEvent me)
            {
                ctrlPressed = me.isControlDown();
                moveTarget(me.getX());
                if (shiftPressed != me.isShiftDown())
                {
                    // reset initialX when transitioning
                    initialX = Math.max(0, Math.min(selectableWidth(), me.getX()));
                    shiftPressed = me.isShiftDown();
                }
            }
        });

        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent me)
            {
                beingDragged = false;
                ctrlPressed = me.isControlDown();
                shiftPressed = me.isShiftDown();
                moveTarget(me.getX());
            }

            @Override
            public void mousePressed(MouseEvent me)
            {
                initialX = me.getX();
                beingDragged = true;
                ctrlPressed = me.isControlDown();
                shiftPressed = me.isShiftDown();
                moveTarget(me.getX());
            }
        });
    }

    private int selectableWidth()
    {
        return this.getWidth() - KNOB_WIDTH;
    }

    public void setRatio(float ratio)
    {
        // Don't transmit change event
        this.ratio = ratio;
//        moveTarget((int) (ratio * selectableWidth()));
    }

    private void moveTarget(float x)
    {
        if (shiftPressed)
        {
            float diff = x - initialX;
            x = initialX + diff / 10.f; // Slow down x10
        }

        x -= KNOB_WIDTH_HALF;

        float max = this.getWidth() - KNOB_WIDTH_HALF;
        if (max > 0)
        {
            ratio = x / max;
            if (ctrlPressed || !this.allowWrapAround)
            {
                // Disallow wrap-around
                ratio = Math.max(0, Math.min(1, ratio));
            }
            else if (ratio < 0)
            {
                ratio += max;
            }
        }

        if (onValueChanged != null)
        {
            onValueChanged.accept(ratio);
        }
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        g.setColor(TRACK_COLOR);
        g.fillRect(0, getHeight() / 2 - 2, getWidth(), 5);

        g.setColor(KNOB_COLOR);
        g.fillRect((int) (ratio * selectableWidth()), getHeight() / 2 - KNOB_HEIGHT / 2, KNOB_WIDTH, KNOB_HEIGHT);
    }

    float getRatio()
    {
        return ratio;
    }
}
