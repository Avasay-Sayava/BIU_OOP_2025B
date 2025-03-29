package src.geometry;

import biuoop.DrawSurface;
import src.util.MathUtils;

/**
 * A class that represents a point in 2D space.
 */
public class Point {
    // The point's coordinates
    private final double x;
    private final double y;

    /**
     * Copy constructor.
     *
     * @param other The point to copy
     */
    public Point(Point other) {
        this(other.x, other.y);
    }

    /**
     * Constructor.
     *
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculate the distance between this point and another point.
     *
     * @param other The other point
     * @return The distance between the two points
     */
    public double distance(Point other) {
        return Math.sqrt((this.x - other.x) * (this.x - other.x)
                + (this.y - other.y) * (this.y - other.y));
    }

    /**
     * Check if this point is equal to another point.
     *
     * @param other The other point
     * @return True if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        return other != null
                && MathUtils.doubleEquals(this.x, other.x)
                && MathUtils.doubleEquals(this.y, other.y);
    }

    /**
     * Get the x coordinate of the point.
     *
     * @return The x coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * Get the y coordinate of the point.
     *
     * @return The y coordinate
     */
    public double getY() {
        return this.y;
    }

    /**
     * Draw the point on the given surface.
     *
     * @param d      The surface to draw on
     * @param radius The radius of the point circle
     */
    public void draw(DrawSurface d, int radius) {
        d.fillCircle((int) this.x, (int) this.y, radius);
    }
}
