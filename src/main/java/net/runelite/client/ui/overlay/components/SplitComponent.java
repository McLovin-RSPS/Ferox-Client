package net.runelite.client.ui.overlay.components;

import java.awt.*;

public class SplitComponent implements LayoutableRenderableEntity
{
    private LayoutableRenderableEntity first;
    private LayoutableRenderableEntity second;

    private Point preferredLocation = new Point();

    private Dimension preferredSize = new Dimension(ComponentConstants.STANDARD_WIDTH, 0);

    private ComponentOrientation orientation = ComponentOrientation.VERTICAL;

    private Point gap = new Point(0, 0);

    private final Rectangle bounds;

    SplitComponent(LayoutableRenderableEntity first, LayoutableRenderableEntity second, Point preferredLocation, Dimension preferredSize, ComponentOrientation orientation, Point gap, Rectangle bounds) {
        this.first = first;
        this.second = second;
        this.preferredLocation = preferredLocation;
        this.preferredSize = preferredSize;
        this.orientation = orientation;
        this.gap = gap;
        this.bounds = bounds;
    }

    private static Point $default$preferredLocation() {
        return new Point();
    }

    private static Dimension $default$preferredSize() {
        return new Dimension(ComponentConstants.STANDARD_WIDTH, 0);
    }

    private static ComponentOrientation $default$orientation() {
        return ComponentOrientation.VERTICAL;
    }

    private static Point $default$gap() {
        return new Point(0, 0);
    }

    private static Rectangle $default$bounds() {
        return new Rectangle();
    }

    public static SplitComponentBuilder builder() {
        return new SplitComponentBuilder();
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        first.setPreferredLocation(preferredLocation);
        first.setPreferredSize(preferredSize);

        final Dimension firstDimension = first.render(graphics);
        int x = 0, y = 0;

        if (orientation == ComponentOrientation.VERTICAL)
        {
            y = firstDimension.height + gap.y;
        }
        else
        {
            x = firstDimension.width + gap.x;
        }

        second.setPreferredLocation(new Point(x + preferredLocation.x, y + preferredLocation.y));
        // Make the second component fit to whatever size is left after the first component is rendered
        second.setPreferredSize(new Dimension(preferredSize.width - x, preferredSize.height - y));

        // The total width/height need to be determined as they are now always the same as the
        // individual width/height (for example image width/height will just be the height of the image
        // and not the height of the area the image is in
        final Dimension secondDimension = second.render(graphics);
        int totalWidth, totalHeight;

        if (orientation == ComponentOrientation.VERTICAL)
        {
            totalWidth = Math.max(firstDimension.width, secondDimension.width);
            totalHeight = y + secondDimension.height;
        }
        else
        {
            totalHeight = Math.max(firstDimension.height, secondDimension.height);
            totalWidth = x + secondDimension.width;
        }

        final Dimension dimension = new Dimension(totalWidth, totalHeight);
        bounds.setLocation(preferredLocation);
        bounds.setSize(dimension);
        return dimension;
    }

    public void setFirst(LayoutableRenderableEntity first) {
        this.first = first;
    }

    public void setSecond(LayoutableRenderableEntity second) {
        this.second = second;
    }

    public void setPreferredLocation(Point preferredLocation) {
        this.preferredLocation = preferredLocation;
    }

    public void setPreferredSize(Dimension preferredSize) {
        this.preferredSize = preferredSize;
    }

    public void setOrientation(ComponentOrientation orientation) {
        this.orientation = orientation;
    }

    public void setGap(Point gap) {
        this.gap = gap;
    }

    public Rectangle getBounds() {
        return this.bounds;
    }

    public static class SplitComponentBuilder {
        private LayoutableRenderableEntity first;
        private LayoutableRenderableEntity second;
        private Point preferredLocation$value;
        private boolean preferredLocation$set;
        private Dimension preferredSize$value;
        private boolean preferredSize$set;
        private ComponentOrientation orientation$value;
        private boolean orientation$set;
        private Point gap$value;
        private boolean gap$set;
        private Rectangle bounds$value;
        private boolean bounds$set;

        SplitComponentBuilder() {
        }

        public SplitComponentBuilder first(LayoutableRenderableEntity first) {
            this.first = first;
            return this;
        }

        public SplitComponentBuilder second(LayoutableRenderableEntity second) {
            this.second = second;
            return this;
        }

        public SplitComponentBuilder preferredLocation(Point preferredLocation) {
            this.preferredLocation$value = preferredLocation;
            this.preferredLocation$set = true;
            return this;
        }

        public SplitComponentBuilder preferredSize(Dimension preferredSize) {
            this.preferredSize$value = preferredSize;
            this.preferredSize$set = true;
            return this;
        }

        public SplitComponentBuilder orientation(ComponentOrientation orientation) {
            this.orientation$value = orientation;
            this.orientation$set = true;
            return this;
        }

        public SplitComponentBuilder gap(Point gap) {
            this.gap$value = gap;
            this.gap$set = true;
            return this;
        }

        public SplitComponentBuilder bounds(Rectangle bounds) {
            this.bounds$value = bounds;
            this.bounds$set = true;
            return this;
        }

        public SplitComponent build() {
            Point preferredLocation$value = this.preferredLocation$value;
            if (!this.preferredLocation$set) {
                preferredLocation$value = SplitComponent.$default$preferredLocation();
            }
            Dimension preferredSize$value = this.preferredSize$value;
            if (!this.preferredSize$set) {
                preferredSize$value = SplitComponent.$default$preferredSize();
            }
            ComponentOrientation orientation$value = this.orientation$value;
            if (!this.orientation$set) {
                orientation$value = SplitComponent.$default$orientation();
            }
            Point gap$value = this.gap$value;
            if (!this.gap$set) {
                gap$value = SplitComponent.$default$gap();
            }
            Rectangle bounds$value = this.bounds$value;
            if (!this.bounds$set) {
                bounds$value = SplitComponent.$default$bounds();
            }
            return new SplitComponent(first, second, preferredLocation$value, preferredSize$value, orientation$value, gap$value, bounds$value);
        }

        public String toString() {
            return "SplitComponent.SplitComponentBuilder(first=" + this.first + ", second=" + this.second + ", preferredLocation$value=" + this.preferredLocation$value + ", preferredSize$value=" + this.preferredSize$value + ", orientation$value=" + this.orientation$value + ", gap$value=" + this.gap$value + ", bounds$value=" + this.bounds$value + ")";
        }
    }
}
