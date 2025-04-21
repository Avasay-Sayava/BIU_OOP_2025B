package geometry;

import biuoop.DrawSurface;
import graphics.Drawable;

/**
 * A class that represents a rectangle in 2D space.
 */
public class Rectangle extends Polygon implements Drawable {
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
     * @param d the DrawSurface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        Point topLeft = topLeft();
        d.fillRectangle((int) Math.round(topLeft.getX()), (int) Math.round(topLeft.getY()),
                (int) Math.round(getWidth()), (int) Math.round(getHeight()));
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
     * @param point the point to check
     * @return true if the rectangle does not contain the point, false otherwise
     */
    @Override
    public boolean doesntContain(Point point) {
        return point.getX() < topLeft().getX() && point.getX() > topLeft().getX() + getWidth()
                && point.getY() < topLeft().getY() && point.getY() > topLeft().getY() + getHeight();
    }
}
