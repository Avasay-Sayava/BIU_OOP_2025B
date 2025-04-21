package geometry;

import biuoop.DrawSurface;
import graphics.Drawable;

public class Ball extends Point implements Drawable {
    private final int radius;

    /**
     * Copy constructor.
     *
     * @param other the ball to copy
     */
    public Ball(Ball other) {
        this(other.getCenter(), other.getRadius());
    }

    /**
     * Constructor.
     *
     * @param center the center of the ball
     * @param radius the radius of the ball
     */
    public Ball(Point center, int radius) {
        super(center);
        this.radius = radius;
    }

    // accessors
    public Point getCenter() {
        return new Point(this);
    }

    public void move(Point center) {
        setX(center.getX());
        setY(center.getY());
    }

    public int getRadius() {
        return this.radius;
    }

    /**
     * @param d the DrawSurface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.fillCircle((int) Math.round(getX()), (int) Math.round(getY()), getRadius());
    }
}
