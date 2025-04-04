package geometry;

import biuoop.DrawSurface;
import util.MathUtils;

/**
 * A class that represents a point in 2D space.
 */
public class Point {
    // The point's coordinates
    private double x;
    private double y;

    /**
     * Copy constructor.
     *
     * @param other The point to copy
     */
    public Point(Point other) {
        this(other.getX(), other.getY());
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
        return Math.sqrt((this.getX() - other.getX()) * (this.getX() - other.getX())
                + (this.getY() - other.getY()) * (this.getY() - other.getY()));
    }

    /**
     * Check if this point is equal to another point.
     *
     * @param other The other point
     * @return True if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        return other != null
                && MathUtils.doubleEquals(this.getX(), other.getX())
                && MathUtils.doubleEquals(this.getY(), other.getY());
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
     * Set the x coordinate of the point.
     *
     * @param x The new x coordinate
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Set the y coordinate of the point.
     *
     * @param y The new y coordinate
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Draw the point on the given surface.
     *
     * @param d      The surface to draw on
     * @param radius The radius of the point circle
     */
    public void draw(DrawSurface d, int radius) {
        d.fillCircle((int) Math.round(this.getX()), (int) Math.round(this.getX()), radius);
    }
}
