package src.geometry;

import biuoop.DrawSurface;
import src.util.LineUtils;
import src.util.MathUtils;

/**
 * A class that represents a line in 2D space.
 */
public class Line {
    // The line's coordinates
    private final double x1;
    private final double y1;
    private final double x2;
    private final double y2;

    /**
     * Copy constructor.
     *
     * @param other The line to copy
     */
    public Line(Line other) {
        this(other.start(), other.end());
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
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    /**
     * @return The length of the line
     */
    public double length() {
        return this.start().distance(this.end());
    }

    /**
     * @return The middle point of the line
     */
    public Point middle() {
        return new Point((this.start().getX() + this.end().getX()) / 2,
                (this.start().getY() + this.end().getY()) / 2);
    }

    /**
     * @return The start point of the line
     */
    public Point start() {
        return new Point(this.x1, this.y1);
    }

    /**
     * @return The end point of the line
     */
    public Point end() {
        return new Point(this.x2, this.y2);
    }

    /**
     * @param other The line to check intersection with
     * @return True if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        return LineUtils.wcc(this, other) && LineUtils.wcc(other, this);
    }

    /**
     * @param lines The lines to check intersection with
     * @return True if any of the lines intersect, false otherwise
     */
    public static boolean isIntersecting(Line... lines) {
        boolean intersecting = true;
        for (int i = 0; i < lines.length && intersecting; i++) {
            for (int j = i + 1; j < lines.length && intersecting; j++) {
                intersecting = lines[i].isIntersecting(lines[j]);
            }
        }
        return intersecting;
    }

    /**
     * @param other The line to check intersection with
     * @return The intersection point if the lines intersect, null otherwise
     */
    public Point intersectionWith(Line other) {
        if (isIntersecting(other)) {
            double dx1 = this.start().getX() - this.end().getX();
            double dy1 = this.start().getY() - this.end().getY();
            double dx2 = other.start().getX() - other.end().getX();
            double dy2 = other.start().getY() - other.end().getY();

            if (!MathUtils.doubleEquals(dy1 * dx2, dy2 * dx1)) {
                double d = dx1 * dy2 - dy1 * dx2;
                double c1 = this.start().getX() * this.end().getY() - this.end().getX() * this.start().getY();
                double c2 = other.start().getX() * other.end().getY() - other.end().getX() * other.start().getY();

                double x = (c1 * dx2 - c2 * dx1) / d;
                double y = (c1 * dy2 - c2 * dy1) / d;

                return new Point(x, y);
            }

            if ((this.start().equals(other.start()) || this.end().equals(other.end()))
                    && (MathUtils.sign(this.end().getX() - this.start().getX())
                    != MathUtils.sign(other.end().getX() - other.start().getX())
                    || MathUtils.sign(this.end().getY() - this.start().getY())
                    != MathUtils.sign(other.end().getY() - other.start().getY()))) {
                return this.start().equals(other.start()) ? this.start() : this.end();
            }

            if ((this.start().equals(other.end()) || this.end().equals(other.start()))
                    && (MathUtils.sign(this.end().getX() - this.start().getX())
                    == MathUtils.sign(other.end().getX() - other.start().getX())
                    || MathUtils.sign(this.end().getY() - this.start().getY())
                    == MathUtils.sign(other.end().getY() - other.start().getY()))) {
                return this.start().equals(other.end()) ? this.start() : this.end();
            }
        }
        return null;
    }

    /**
     * @param other The line to compare to
     * @return True if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return other != null
                && (this.start().equals(other.start()) && this.end().equals(other.end())
                || this.start().equals(other.end()) && this.end().equals(other.start()));
    }

    /**
     * Draw the line.
     *
     * @param d The draw surface
     */
    public void draw(DrawSurface d) {
        d.drawLine((int) Math.round(this.start().getX()),
                (int) Math.round(this.start().getY()),
                (int) Math.round(this.end().getX()),
                (int) Math.round(this.end().getY()));
    }
}
