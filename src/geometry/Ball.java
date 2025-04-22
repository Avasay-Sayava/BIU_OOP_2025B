package geometry;

import biuoop.DrawSurface;
import collision.BallCollider;
import graphics.Colored;
import physics.PhysicalShape;
import physics.Velocity;
import util.Constants;
import util.LineUtils;
import util.NotUsedButYouSaidINeedToHaveItYey;

import java.awt.Color;

/**
 * This class represents a ball.
 */
public class Ball extends Point implements Colored, PhysicalShape {
    private final int radius;
    private Color color;
    private final Velocity velocity;
    private double area;
    private final BallCollider collider;

    /**
     * Copy constructor.
     *
     * @param other the ball to copy
     */
    public Ball(Ball other) {
        this(other.getCenter(), other.getRadius(), other.getColor());
    }

    /**
     * Constructor.
     *
     * @param center the center of the ball
     * @param radius the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int radius, Color color) {
        this(center.getX(), center.getY(), radius, color);
    }

    /**
     * Constructor.
     *
     * @param x      the x coordinate of the center of the ball
     * @param y      the y coordinate of the center of the ball
     * @param radius the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(double x, double y, int radius, Color color) {
        super(x, y);
        this.radius = radius;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.area = -1;
        this.collider = new BallCollider(this);
    }

    /**
     * @return the center of the ball
     */
    public Point getCenter() {
        return new Point(this);
    }

    /**
     * @return the radius of the ball
     */
    public int getRadius() {
        return this.radius;
    }

    /**
     * @return the radius of the ball
     */
    @NotUsedButYouSaidINeedToHaveItYey
    @SuppressWarnings("unused")
    public int getSize() {
        return getRadius();
    }

    /**
     * @param d the DrawSurface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillCircle((int) Math.round(getX()), (int) Math.round(getY()), getRadius());
    }

    /**
     * @return The color of the object
     */
    @Override
    public Color getColor() {
        return this.color;
    }

    /**
     * @param color The color to set
     */
    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return The velocity of the object
     */
    @Override
    public Velocity getVelocity() {
        return new Velocity(this.velocity);
    }

    /**
     * @param dx the x coordinate of the new point
     * @param dy the y coordinate of the new point
     */
    @Override
    public void setVelocity(double dx, double dy) {
        this.velocity.move(dx, dy);
        getCollider().updateSpeed();
    }

    /**
     * @param velocity the new velocity of the object
     */
    @Override
    public void setVelocity(Velocity velocity) {
        setVelocity(velocity.getX(), velocity.getY());
    }

    /**
     * Moves the object one step in the direction of its velocity.
     */
    @Override
    public void moveOneStep() {
        transform(this.velocity.withFPS(Constants.FPS));
        this.velocity.moveOneStep();
        getCollider().updateSpeed();
    }

    /**
     * @return The area of the ball
     */
    @Override
    public double getArea() {
        return Math.PI * this.radius * this.radius;
    }

    /**
     * @param accuracy the accuracy of the polygon. the greater, the better.
     * @return a polygon that represents the ball
     */
    @Override
    public Polygon getPolygon(int accuracy) {
        Point[] points = new Point[accuracy];
        for (int i = 0; i < accuracy; i++) {
            double angle = Math.toRadians(i);
            points[i] = new Point(getX() + getRadius() * Math.cos(angle),
                    getY() + getRadius() * Math.sin(angle));
        }
        return new Polygon(points);
    }

    /**
     * @param other the shape to check for intersection
     * @return true if the ball intersects with the other shape, false otherwise
     */
    @Override
    public boolean isIntersecting(Shape other) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * @param other the ball to check for intersection
     * @return true if the ball intersects with the other ball, false otherwise
     */
    public boolean isIntersecting(Ball other) {
        return this.getCenter().distance(other.getCenter()) < this.getRadius() + other.getRadius();
    }

    /**
     * @param line the line to check for intersection
     * @return true if the ball intersects with the line, false otherwise
     */
    @Override
    public Line isIntersecting(Polygon line) {
        Line[] lines = line.getLines();
        for (Line l : lines) {
            if (this.isIntersecting(l)) {
                return l;
            }
        }
        return null;
    }

    /**
     * @param line the line to check for intersection
     * @return true if the ball intersects with the line, false otherwise
     */
    @Override
    public boolean isIntersecting(Line line) {
        return intersectionType(line) != IntersectionType.NO_INTERSECTION;
    }

    /**
     * Calculates if the ball intersects with the line and returns accordingly.
     *
     * @param line the line to intersect with
     * @return ON_TOP if the ball intersects the line segment itself,
     * ON_VERTEX if the ball intersects one of the vertexes, and
     * NO_INTERSECTION if the ball doesn't intersect the line.
     */
    public IntersectionType intersectionType(Line line) {
        double startAngle = LineUtils.angleBetween(line, new Line(line.getStart(), this));
        double endAngle = LineUtils.angleBetween(line, new Line(this, line.getEnd()));
        if (startAngle < Math.PI / 2 && endAngle < Math.PI / 2) {
            return line.distance(this.getCenter()) <= this.getRadius() ? IntersectionType.ON_TOP :
                    IntersectionType.NO_INTERSECTION;
        } else {
            return Math.min(line.getStart().distance(this),
                    line.getEnd().distance(this)) <= this.getRadius() ? IntersectionType.ON_VERTEX :
                    IntersectionType.NO_INTERSECTION;
        }
    }

    /**
     * @return the bounding rectangle of the ball
     */
    @Override
    public Rectangle calculateBounds() {
        return new Rectangle(new Point(getX() - getRadius(), getY() - getRadius()),
                new Point(getX() + getRadius(), getY() + getRadius()));
    }

    /**
     * @return the area of the ball
     */
    @Override
    public double calculateArea() {
        if (this.area < 0) {
            this.area = getArea();
        }
        return this.area;
    }

    /**
     * @return the bounding rectangle of the ball
     */
    @Override
    public Rectangle getBounds() {
        return this.calculateBounds();
    }

    /**
     * @param point the point to check
     * @return true if the point is inside the ball, false otherwise
     */
    @Override
    public boolean contains(Point point) {
        return point.distance(this) < getRadius();
    }

    /**
     * @param point the point to check
     * @return true if the point is outside the ball, false otherwise
     */
    @Override
    public boolean doesntContain(Point point) {
        return point.distance(this) > getRadius();
    }

    /**
     * @return the collider of the ball
     */
    @Override
    public BallCollider getCollider() {
        return this.collider;
    }

    public enum IntersectionType {
        NO_INTERSECTION, ON_TOP, ON_VERTEX
    }
}
