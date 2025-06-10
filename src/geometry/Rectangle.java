package geometry;

import collision.Collidable;
import util.ValueSet;
import util.DisgustingButYouSaidINeedToHaveItYey;

import java.util.List;
import java.util.Set;

/**
 * A class that represents a rectangle in 2D space.
 */
public class Rectangle extends Polygon implements Collidable {
    private final double width;
    private final double height;

    /**
     * Copy constructor.
     *
     * @param other the rectangle to copy
     */
    public Rectangle(Rectangle other) {
        this(other.getVertex(0), other.getVertex(2));
    }

    /**
     * Constructor.
     *
     * @param point1 the first point
     * @param point2 the second point
     */
    public Rectangle(Point point1, Point point2) {
        this(point1.getX(), point1.getY(), point2.getX(), point2.getY());
    }

    /**
     * Constructor.
     *
     * @param topLeft the top left point of the rectangle
     * @param width   the width of the rectangle
     * @param height  the height of the rectangle
     */
    public Rectangle(Point topLeft, double width, double height) {
        this(topLeft, topLeft.copy().add(new Point(width, height)));
    }

    /**
     * Constructor.
     *
     * @param x1 the x coordinate of the first point
     * @param y1 the y coordinate of the first point
     * @param x2 the x coordinate of the second point
     * @param y2 the y coordinate of the second point
     */
    public Rectangle(double x1, double y1, double x2, double y2) {
        super(new Point(x1, y1),
                new Point(x2, y1),
                new Point(x2, y2),
                new Point(x1, y2));
        this.width = Math.abs(x2 - x1);
        this.height = Math.abs(y2 - y1);
    }

    /**
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the top left point of the rectangle
     */
    public Point topLeft() {
        return new Point(Math.min(getVertex(0).getX(), getVertex(2).getX()),
                Math.min(getVertex(0).getY(), getVertex(2).getY()));
    }

    /**
     * @return the area of the rectangle
     */
    @Override
    public double calculateArea() {
        return this.width * this.height;
    }

    /**
     * @return the perimeter of the rectangle
     */
    @Override
    public Rectangle calculateBounds() {
        return new Rectangle(this);
    }

    /**
     * @param point the point to check
     * @return true if the rectangle contains the point, false otherwise
     */
    @Override
    public boolean contains(Point point) {
        return point.getX() > topLeft().getX() && point.getX() < topLeft().getX() + getWidth()
                && point.getY() > topLeft().getY() && point.getY() < topLeft().getY() + getHeight();
    }

    /**
     * @param circle the circle to check
     * @return true if the rectangle contains the circle, false otherwise
     */
    public boolean contains(Circle circle) {
        return circle.getX() > topLeft().getX() && circle.getX() < topLeft().getX() + getWidth()
                && circle.getY() > topLeft().getY() && circle.getY() < topLeft().getY() + getHeight()
                && !circle.isIntersecting(this);
    }

    /**
     * @param circle the circle to check
     * @return true if the rectangle does not contain the circle, false otherwise
     */
    @Override
    public boolean doesntContain(Point circle) {
        return circle.getX() <= topLeft().getX() || circle.getX() >= topLeft().getX() + getWidth()
                || circle.getY() <= topLeft().getY() || circle.getY() >= topLeft().getY() + getHeight();
    }

    /**
     * @param circle the circle to check
     * @return true if the rectangle does not contain the circle, false otherwise
     */
    public boolean doesntContain(Circle circle) {
        return (circle.getX() <= topLeft().getX() || circle.getX() >= topLeft().getX() + getWidth()
                || circle.getY() <= topLeft().getY() || circle.getY() >= topLeft().getY() + getHeight())
                && !circle.isIntersecting(this);
    }

    /**
     * @return the top left point of the rectangle
     */
    @DisgustingButYouSaidINeedToHaveItYey
    public Point getUpperLeft() {
        return topLeft();
    }

    /**
     * @return the bottom right point of the rectangle
     */
    public Point bottomRight() {
        return topLeft().add(new Point(getWidth(), getHeight()));
    }

    /**
     * @return the top right point of the rectangle
     */
    public Point topRight() {
        return new Point(bottomRight().getX(), topLeft().getY());
    }

    /**
     * @return the bottom left point of the rectangle
     */
    public Point bottomLeft() {
        return new Point(topLeft().getX(), bottomRight().getY());
    }

    /**
     * Calculates the intersection points of this rectangle with another line.
     *
     * @param other the line to check for intersection
     * @return a list of the intersection points with the line
     */
    public List<Point> intersectionPoints(Line other) {
        Set<Point> out = new ValueSet<>();
        for (Line line : getLines()) {
            Point intersection = other.intersectionWith(line);
            if (intersection != null) {
                out.add(intersection);
            }
        }
        return out.stream().toList();
    }

    /**
     * @return the leftmost side of the rectangle
     */
    public double left() {
        return topLeft().getX();
    }

    /**
     * @return the rightmost side of the rectangle
     */
    public double right() {
        return bottomRight().getX();
    }

    /**
     * @return the topmost side of the rectangle
     */
    public double top() {
        return topLeft().getY();
    }

    /**
     * @return the bottommost side of the rectangle
     */
    public double bottom() {
        return bottomRight().getY();
    }

    /**
     * Reduces the rectangle by a given value.
     *
     * @param value the value to reduce the rectangle by
     * @return the reduced rectangle
     */
    public Rectangle reduced(double value) {
        return new Rectangle(topLeft().add(new Point(value, value)),
                bottomRight().add(new Point(-value, -value)));
    }
}
