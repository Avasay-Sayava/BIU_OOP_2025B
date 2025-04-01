package util;

import geometry.Point;

/**
 * The utils for the Point class.
 */
public class PointUtils {

    /**
     * @param p1 The first point
     * @param p2 The second point
     * @param p3 The third point
     * @return If the points are clockwise
     */
    public static boolean isClockwise(Point p1, Point p2, Point p3) {
        return (p2.getX() - p1.getX()) * (p3.getY() - p1.getY())
                > (p2.getY() - p1.getY()) * (p3.getX() - p1.getX());
    }

    /**
     * @param p1 The first point
     * @param p2 The second point
     * @param p3 The third point
     * @return If the points are clockwise or collinear
     */
    public static boolean isClockwiseOrCollinear(Point p1, Point p2, Point p3) {
        return (p2.getX() - p1.getX()) * (p3.getY() - p1.getY())
                >= (p2.getY() - p1.getY()) * (p3.getX() - p1.getX());
    }
}
