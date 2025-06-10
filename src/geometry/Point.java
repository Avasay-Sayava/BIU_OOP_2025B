package geometry;

import graphics.Movable;
import util.MathUtils;
import util.Pair;

/**
 * A class that represents a point in 2D space.
 */
public class Point extends Pair<Double, Double> implements Movable {
    /**
     * Create a point from an angle and speed.
     *
     * @param angle the angle in radians
     * @param speed the speed
     * @return a point with the given angle and speed
     */
    public static Point fromAngleAndSpeed(double angle, double speed) {
        return new Point(Math.cos(angle) * speed, Math.sin(angle) * speed);
    }

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
        super(x, y);
    }

    /**
     * Calculate the distance between this point and another point.
     *
     * @param other The other point
     * @return The distance between the two points
     */
    public double distance(Point other) {
        return this.copy().subtract(other).normal();
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
        return getFirst();
    }

    /**
     * Get the y coordinate of the point.
     *
     * @return The y coordinate
     */
    public double getY() {
        return getSecond();
    }

    /**
     * Set the x coordinate of the point.
     *
     * @param x The x coordinate
     */
    public void setX(double x) {
        setFirst(x);
    }

    /**
     * Set the y coordinate of the point.
     *
     * @param y The y coordinate
     */
    public void setY(double y) {
        setSecond(y);
    }

    /**
     * Move the object to the given point.
     *
     * @param p the new point of the object
     */
    @Override
    public void move(Point p) {
        move(p.getX(), p.getY());
    }

    /**
     * Move the object to the given point.
     *
     * @param x the x coordinate of the new point
     * @param y the y coordinate of the new point
     */
    @Override
    public void move(double x, double y) {
        setX(x);
        setY(y);
    }

    /**
     * Move the object by the given point.
     *
     * @param dp the point to move by
     */
    @Override
    public void transform(Point dp) {
        transform(dp.getX(), dp.getY());
    }

    /**
     * Move the object by the given delta.
     *
     * @param dx the delta x
     * @param dy the delta y
     */
    @Override
    public void transform(double dx, double dy) {
        move(getX() + dx, getY() + dy);
    }

    /**
     * Multiply the point by a scalar.
     *
     * @param scalar the scalar to multiply by
     * @return the point after multiplication
     */
    public Point multiply(double scalar) {
        move(getX() * scalar, getY() * scalar);
        return this;
    }

    /**
     * Multiply the point by a scalar.
     *
     * @param other the point to multiply by
     * @return the point after multiplication
     */
    public Point multiply(Point other) {
        move(getX() * other.getX(), getY() * other.getY());
        return this;
    }

    /**
     * Calculate the dot product of this point and another point.
     *
     * @param other the other point
     * @return the dot product
     */
    public double dot(Point other) {
        return getX() * other.getX() + getY() * other.getY();
    }

    /**
     * Add another point to this point.
     *
     * @param other the other point
     * @return this point after addition
     */
    public Point add(Point other) {
        transform(other);
        return this;
    }

    /**
     * Subtract another point from this point.
     *
     * @param other the other point
     * @return this point after subtraction
     */
    public Point subtract(Point other) {
        add(other.copy().multiply(-1));
        return this;
    }

    /**
     * Calculate the normal of this point.
     *
     * @return the normal of this point
     */
    public double normal() {
        return Math.sqrt(this.dot(this));
    }

    /**
     * Normalize this point.
     *
     * @return the normalized point
     */
    public Point normalize() {
        double length = normal();
        if (length == 0) {
            return this;
        }
        return multiply(1 / length);
    }

    /**
     * Create a clone of the point.
     *
     * @return a new point with the same coordinates
     */
    public Point copy() {
        return new Point(this);
    }
}
