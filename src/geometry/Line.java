package geometry;

import biuoop.DrawSurface;
import collision.Collidable;
import graphics.Drawable;
import physics.Velocity;
import util.Constants;
import util.LineUtils;
import util.MathUtils;
import util.Pair;

/**
 * A class that represents a line in 2D space.
 */
public class Line extends Pair<Point, Point> implements Drawable, Collidable {
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
     * @return the center of the shape
     */
    @Override
    public double getArea() {
        return 0;
    }

    /**
     * @param accuracy the accuracy of the polygon
     * @return a polygon that represents the shape
     */
    @Override
    public Polygon getPolygon(int accuracy) {
        return new Polygon(this.getStart(), this.getEnd());
    }

    /**
     * @param other the shape to check for intersection
     * @return true if the shape intersects with the other shape, false otherwise
     */
    @Override
    public boolean isIntersecting(Shape other) {
        return other.isIntersecting(this);
    }

    /**
     * @param other The line to check intersection with
     * @return True if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        return LineUtils.wcc(this, other) && LineUtils.wcc(other, this);
    }

    /**
     * @return the lines of the shape
     */
    @Override
    public Rectangle calculateBounds() {
        return new Rectangle(getStart(), getEnd());
    }

    /**
     * @return the area of the shape
     */
    @Override
    public double calculateArea() {
        return 0;
    }

    /**
     * @return the perimeter of the shape
     */
    @Override
    public Rectangle getBounds() {
        return this.calculateBounds();
    }

    /**
     * @param point the point to check
     * @return true if the shape contains the point, false otherwise
     */
    @Override
    public boolean contains(Point point) {
        return false;
    }

    /**
     * @param point the point to check
     * @return true if the shape does not contain the point, false otherwise
     */
    @Override
    public boolean doesntContain(Point point) {
        return !new Line(point, point).isIntersecting(this);
    }

    /**
     * @return the center of the shape
     */
    @Override
    public Point getCenter() {
        return middle();
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

    /**
     * @param rect the rectangle to check intersection with
     * @return The closest intersection point to the start of the line
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        return rect.intersectionPoints(this).stream()
                .min((p1, p2) -> Double.compare(p1.distance(getStart()),
                        p2.distance(getStart()))).orElse(null);
    }

    /**
     * This method is called when the object collides with another object.
     *
     * @param collisionPoint    the point of collision
     * @param collisionVelocity the velocity of the object at the time of collision
     * @return the new velocity of the object after the collision
     */
    @Override
    public Velocity hit(Point collisionPoint, Velocity collisionVelocity) {
        double normalAngle = this.getAngle() + Math.PI / 2;
        Point normal = Point.fromAngleAndSpeed(normalAngle, 1);
        Point velocityProj = normal.copy().multiply(normal.dot(collisionVelocity));
        Point center = collisionPoint.subtract(this.getStart());
        Point centerProj = normal.copy().multiply(normal.dot(center)).normalize();
        Point changed = centerProj.copy().multiply(Constants.ELASTICITY_FACTOR
                * velocityProj.normal()).subtract(velocityProj).add(collisionVelocity);
        return new Velocity(changed);
    }

    /**
     * Check if this line can be merged with another line.
     *
     * @param other The line to check
     * @return True if the lines are mergeable, false otherwise
     */
    public boolean isMergable(Line other) {
        Point[] thisEnds = {this.getStart(), this.getEnd()};
        Point[] otherEnds = {other.getStart(), other.getEnd()};

        for (Point p1 : thisEnds) {
            for (Point p2 : otherEnds) {
                if (p1.equals(p2)) {
                    Point dir1 = this.getOtherPoint(p1).subtract(p1);
                    Point dir2 = other.getOtherPoint(p2).subtract(p2);
                    double cross = dir1.getX() * dir2.getY() - dir1.getY() * dir2.getX();
                    return Math.abs(cross) < MathUtils.COMPARISON_THRESHOLD;
                }
            }
        }
        return false;
    }

    /**
     * Merge this line with another line if they are mergeable.
     *
     * @param other The line to merge with
     * @return A new line that is the result of the merge
     * @throws IllegalStateException if the lines are not mergeable
     */
    public Line merge(Line other) {
        Point[] thisEnds = {this.getStart(), this.getEnd()};
        Point[] otherEnds = {other.getStart(), other.getEnd()};

        for (Point p1 : thisEnds) {
            for (Point p2 : otherEnds) {
                if (p1.equals(p2)) {
                    Point thisOther = this.getOtherPoint(p1);
                    Point otherOther = other.getOtherPoint(p2);
                    return new Line(thisOther, otherOther);
                }
            }
        }
        throw new IllegalStateException("Lines are not mergeable.");
    }

    /**
     * Get the other point of the line that is not the given point.
     *
     * @param p The point to check
     * @return The other point of the line
     * @throws IllegalArgumentException if the point is not part of the line
     */
    Point getOtherPoint(Point p) {
        if (p.equals(this.getStart())) {
            return this.getEnd();
        }
        if (p.equals(this.getEnd())) {
            return this.getStart();
        }
        throw new IllegalArgumentException("Point is not part of the line.");
    }
}
