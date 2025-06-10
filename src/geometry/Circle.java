package geometry;

import collision.CircleCollider;
import collision.Colliding;
import physics.Velocity;
import util.Constants;
import util.LineUtils;
import util.NotUsedButYouSaidINeedToHaveItYey;

/**
 * This class represents a circle.
 */
public class Circle extends Point implements Colliding {
    private final int radius;
    private final Velocity velocity;
    private double area;
    private final Point lastCenter;
    private final CircleCollider collider;

    /**
     * Copy constructor.
     *
     * @param other the circle to copy
     */
    public Circle(Circle other) {
        this(other.getCenter(), other.getRadius());
    }

    /**
     * Constructor.
     *
     * @param center the center of the circle
     * @param radius the radius of the circle
     */
    public Circle(Point center, int radius) {
        this(center.getX(), center.getY(), radius);
    }

    /**
     * Constructor.
     *
     * @param x      the x coordinate of the center of the circle
     * @param y      the y coordinate of the center of the circle
     * @param radius the radius of the circle
     */
    public Circle(double x, double y, int radius) {
        super(x, y);
        this.radius = radius;
        this.velocity = new Velocity(0, 0);
        this.area = -1;
        this.collider = new CircleCollider(this);
        this.lastCenter = this.copy();
    }

    /**
     * @return the center of the circle
     */
    @Override
    public Point getCenter() {
        return new Point(this);
    }

    /**
     * @return the radius of the circle
     */
    public int getRadius() {
        return this.radius;
    }

    /**
     * @return the radius of the circle
     */
    @NotUsedButYouSaidINeedToHaveItYey
    @SuppressWarnings("unused")
    public int getSize() {
        return getRadius();
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
        this.getCollider().apply();
        transform(this.velocity.withFPS(Constants.FPS));
        this.velocity.moveOneStep();
        getCollider().updateSpeed();
    }

    /**
     * @return The last center of the circle
     */
    public Point getLastCenter() {
        return new Point(this.lastCenter);
    }

    /**
     * @return The area of the circle
     */
    @Override
    public double getArea() {
        return Math.PI * this.radius * this.radius;
    }

    /**
     * @param accuracy the accuracy of the polygon. the greater, the better.
     * @return a polygon that represents the circle
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
     * @return true if the circle intersects with the other shape, false otherwise
     */
    @Override
    public boolean isIntersecting(Shape other) {
        return isIntersecting(other.getPolygon(Constants.POLYGON_ACCURACY_FACTOR));
    }

    /**
     * @param other the shape to check for intersection
     * @return true if the circle intersects with the other shape, false otherwise
     */
    public boolean isIntersecting(Polygon other) {
        return getIntersecting(other) != null;
    }

    /**
     * @param other the circle to check for intersection
     * @return true if the circle intersects with the other circle, false otherwise
     */
    public boolean isIntersecting(Circle other) {
        return this.getCenter().distance(other.getCenter()) < this.getRadius() + other.getRadius();
    }

    /**
     * @param line the line to check for intersection
     * @return the intersecting line if the circle intersects with the line, null otherwise
     */
    @Override
    public Line getIntersecting(Polygon line) {
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
     * @return true if the circle intersects with the line, false otherwise
     */
    @Override
    public boolean isIntersecting(Line line) {
        return intersectionType(line) != IntersectionType.NO_INTERSECTION;
    }

    /**
     * Calculates if the circle intersects with the line and returns accordingly.
     *
     * @param line the line to intersect with
     * @return ON_TOP if the circle intersects the line segment itself,
     * ON_VERTEX if the circle intersects one of the vertexes, and
     * NO_INTERSECTION if the circle doesn't intersect the line.
     */
    public IntersectionType intersectionType(Line line) {
        if (new Line(this, this.lastCenter).isIntersecting(line)) {
            return IntersectionType.MOVEMENT_INTERSECTION;
        }
        double startAngle = LineUtils.angleBetween(line, new Line(line.getStart(), this));
        double endAngle = LineUtils.angleBetween(line, new Line(this, line.getEnd()));
        if (startAngle <= Math.PI / 2 && endAngle <= Math.PI / 2) {
            return line.distance(this.getCenter()) <= this.getRadius() ? IntersectionType.ON_TOP
                    : IntersectionType.NO_INTERSECTION;
        } else {
            return Math.min(line.getStart().distance(this),
                    line.getEnd().distance(this)) <= this.getRadius() ? IntersectionType.ON_VERTEX
                    : IntersectionType.NO_INTERSECTION;
        }
    }

    /**
     * @return the bounding rectangle of the circle
     */
    @Override
    public Rectangle calculateBounds() {
        return new Rectangle(new Point(getX() - getRadius(), getY() - getRadius()),
                new Point(getX() + getRadius(), getY() + getRadius()));
    }

    /**
     * @return the area of the circle
     */
    @Override
    public double calculateArea() {
        if (this.area < 0) {
            this.area = getArea();
        }
        return this.area;
    }

    /**
     * @return the bounding rectangle of the circle
     */
    @Override
    public Rectangle getBounds() {
        return this.calculateBounds();
    }

    /**
     * @param point the point to check
     * @return true if the point is inside the circle, false otherwise
     */
    @Override
    public boolean contains(Point point) {
        return point.distance(this) < getRadius();
    }

    /**
     * @param point the point to check
     * @return true if the point is outside the circle, false otherwise
     */
    @Override
    public boolean doesntContain(Point point) {
        return point.distance(this) > getRadius();
    }

    /**
     * @return the collider of the circle
     */
    @Override
    public CircleCollider getCollider() {
        return this.collider;
    }

    /**
     * Updates the last center of the circle.
     */
    public void updateLastCenter() {
        this.lastCenter.move(this);
    }

    /**
     * The type of intersection between a circle and a line.
     */
    public enum IntersectionType {
        NO_INTERSECTION, ON_TOP, MOVEMENT_INTERSECTION, ON_VERTEX
    }
}
