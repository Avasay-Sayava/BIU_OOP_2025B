package graphics;

import biuoop.DrawSurface;
import geometry.Circle;
import geometry.Point;

import java.awt.Color;
import java.util.Objects;

/**
 * A ball is a circle with a color.
 */
public class Ball extends Circle implements Colored, Sprite {
    private Color color;

    /**
     * Copy constructor.
     *
     * @param other the ball to copy
     */
    public Ball(Ball other) {
        this(other, other.getColor());
    }

    /**
     * Copy constructor from circle.
     *
     * @param other the circle to copy
     * @param color the color of the ball
     */
    public Ball(Circle other, Color color) {
        this(other, other.getRadius(), color);
    }

    /**
     * Constructor.
     *
     * @param center the center of the ball
     * @param radius the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int radius, Color color) {
        this(center.getX(), center.getY(), radius, color);
    }

    /**
     * Constructor.
     *
     * @param x      the x coordinate of the center of the ball
     * @param y      the y coordinate of the center of the ball
     * @param radius the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(double x, double y, int radius, Color color) {
        super(x, y, radius);
        this.color = color;
    }

    /**
     * @param d the DrawSurface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillCircle((int) Math.round(getX()), (int) Math.round(getY()), getRadius());
        d.setColor(Color.BLACK);
        d.drawCircle((int) Math.round(getX()), (int) Math.round(getY()), getRadius());
    }

    /**
     * @return The color of the object
     */
    @Override
    public Color getColor() {
        return this.color;
    }

    /**
     * @param color The color to set
     */
    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Checks if the color of this object is equal to the color of another Colored object.
     *
     * @param other The other Colored object to compare with
     * @return true if the colors are equal, false otherwise
     */
    @Override
    public boolean colorsEqual(Colored other) {
        return Objects.equals(this.getColor(), other.getColor());
    }

    /**
     * passes the time doing a useless thing.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }
}
