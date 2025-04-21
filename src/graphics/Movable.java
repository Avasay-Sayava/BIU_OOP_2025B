package graphics;

import geometry.Point;

/**
 * This interface represents an object that can be moved.
 */
public interface Movable {
    /**
     * Move the object to the given point.
     *
     * @param p the new point of the object
     */
    void move(Point p);

    /**
     * Move the object to the given point.
     *
     * @param x the x coordinate of the new point
     * @param y the y coordinate of the new point
     */
    void move(double x, double y);

    /**
     * Move the object by the given point.
     *
     * @param delta the point to move by
     */
    void transform(Point delta);

    /**
     * Move the object by the given delta.
     *
     * @param dx the delta x
     * @param dy the delta y
     */
    void transform(double dx, double dy);
}
