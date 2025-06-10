package graphics;

import biuoop.DrawSurface;
import geometry.Point;

/**
 * A Text is a point with a text to display.
 */
public class Text extends Point implements Sprite, Drawable {
    private final String text;

    /**
     * Copy constructor.
     *
     * @param other The point to copy
     * @param text  The text to display
     */
    public Text(Point other, String text) {
        this(other.getX(), other.getY(), text);
    }

    /**
     * Constructor.
     *
     * @param x    The x coordinate
     * @param y    The y coordinate
     * @param text The text to display
     */
    public Text(double x, double y, String text) {
        super(x, y);
        this.text = text;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawText((int) Math.round(getX()), (int) Math.round(getY()), this.text, 20);
    }

    /**
     * Yappppp.
     */
    @Override
    public void timePassed() {
    }
}
