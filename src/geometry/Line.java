package geometry;

import biuoop.DrawSurface;
import graphics.Drawable;
import util.LineUtils;
import util.MathUtils;
import util.Pair;

/**
 * A class that represents a line in 2D space.
 */
public class Line extends Pair<Point, Point> implements Drawable {
    /**
     * Copy constructor.
     *
     * @param other The line to copy
     */
    public Line(Line other) {
        this(other.getStart(), other.getEnd());
    }

    /**
     * Constructor.
     *
     * @param start The start point
     * @param end   The end point
     */
    public Line(Point start, Point end) {
        this(start.getX(), start.getY(), end.getX(), end.getY());
    }

    /**
     * Constructor.
     *
     * @param x1 The x coordinate of the start point
     * @param y1 The y coordinate of the start point
     * @param x2 The x coordinate of the end point
     * @param y2 The y coordinate of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        super(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * @return The length of the line
     */
    public double length() {
        return getStart().distance(getEnd());
    }

    /**
     * @return The middle point of the line
     */
    public Point middle() {
        return new Point((getStart().getX() + getEnd().getX()) / 2,
                (getStart().getY() + getEnd().getY()) / 2);
    }

    /**
     * @return The start point of the line
     */
    public Point getStart() {
        return new Point(getFirst());
    }

    /**
     * @return The end point of the line
     */
    public Point getEnd() {
        return new Point(getSecond());
    }

    /**
     * @param other The line to check intersection with
     * @return True if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        return LineUtils.wcc(this, other) && LineUtils.wcc(other, this);
    }

    /**
     * @param other1 the first line to check intersection with
     * @param other2 the second line to check intersection with
     * @return True if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return isIntersecting(other1) && isIntersecting(other2);
    }

    /**
     * @param lines The lines to check intersection with
     * @return True if the all lines intersect with this line, false otherwise
     */
    public boolean isIntersecting(Line... lines) {
        if (lines == null || lines.length < 1) {
            return false;
        }

        boolean intersecting = true;
        for (int i = 0; i < lines.length && intersecting; i++) {
            intersecting = isIntersecting(lines[i]);
        }

        return intersecting;
    }

    /**
     * @param other The line to check intersection with
     * @return The intersection point if the lines intersect, null otherwise
     */
    public Point intersectionWith(Line other) {
        // if the lines do intersect
        if (isIntersecting(other)) {
            double dx1 = getStart().getX() - getEnd().getX();
            double dy1 = getStart().getY() - getEnd().getY();
            double dx2 = other.getStart().getX() - other.getEnd().getX();
            double dy2 = other.getStart().getY() - other.getEnd().getY();

            // check if the lines are parallel. if not, calculate the intersection point
            if (!MathUtils.doubleEquals(dy1 * dx2, dy2 * dx1)) {
                double d = dx1 * dy2 - dy1 * dx2;
                double c1 = getStart().getX() * getEnd().getY() - getEnd().getX() * getStart().getY();
                double c2 = other.getStart().getX() * other.getEnd().getY()
                        - other.getEnd().getX() * other.getStart().getY();

                double x = (c1 * dx2 - c2 * dx1) / d;
                double y = (c1 * dy2 - c2 * dy1) / d;

                return new Point(x, y);
            }

            // if the lines are parallel, check if they have only one intersection point (the end or start)
            // for start = start or end
            if (getStart().equals(other.getStart())
                    && (MathUtils.sign(getEnd().getX() - getStart().getX())
                    != MathUtils.sign(other.getEnd().getX() - other.getStart().getX())
                    || MathUtils.sign(getEnd().getY() - getStart().getY())
                    != MathUtils.sign(other.getEnd().getY() - other.getStart().getY()))
                    || getStart().equals(other.getEnd())
                    && (MathUtils.sign(getEnd().getX() - getStart().getX())
                    != MathUtils.sign(other.getStart().getX() - other.getEnd().getX())
                    || MathUtils.sign(getEnd().getY() - getStart().getY())
                    != MathUtils.sign(other.getStart().getY() - other.getEnd().getY()))) {
                return getStart();
            }

            // for end = start or end
            if (getEnd().equals(other.getStart())
                    && (MathUtils.sign(getStart().getX() - getEnd().getX())
                    != MathUtils.sign(other.getEnd().getX() - other.getStart().getX())
                    || MathUtils.sign(getStart().getY() - getEnd().getY())
                    != MathUtils.sign(other.getEnd().getY() - other.getStart().getY()))
                    || getEnd().equals(other.getEnd())
                    && (MathUtils.sign(getStart().getX() - getEnd().getX())
                    != MathUtils.sign(other.getStart().getX() - other.getEnd().getX())
                    || MathUtils.sign(getStart().getY() - getEnd().getY())
                    != MathUtils.sign(other.getStart().getY() - other.getEnd().getY()))) {
                return getEnd();
            }
        }

        // if the lines do not intersect, return null
        return null;
    }

    /**
     * @param other The line to compare to
     * @return True if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return other != null
                && (getStart().equals(other.getStart()) && getEnd().equals(other.getEnd())
                || getStart().equals(other.getEnd()) && getEnd().equals(other.getStart()));
    }

    /**
     * @param point The point to check
     * @return The distance from the line to the point
     */
    public double distance(Point point) {
        double dx = getEnd().getX() - getStart().getX();
        double dy = getEnd().getY() - getStart().getY();
        double c = getStart().getX() * getEnd().getY() - getEnd().getX() * getStart().getY();
        return Math.abs(dy * point.getX() - dx * point.getY() - c) / length();
    }

    /**
     * Draw the line.
     *
     * @param d The draw surface
     */
    public void drawOn(DrawSurface d) {
        d.drawLine((int) Math.round(getStart().getX()),
                (int) Math.round(getStart().getY()),
                (int) Math.round(getEnd().getX()),
                (int) Math.round(getEnd().getY()));
    }

    /**
     * @return The angle of the line in radians
     */
    public double getAngle() {
        return Math.atan2(getEnd().getY() - getStart().getY(),
                getEnd().getX() - getStart().getX());
    }
}
