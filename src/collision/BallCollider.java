package collision;

import geometry.Ball;
import geometry.Line;
import geometry.Point;
import geometry.Shape;
import physics.Velocity;
import util.Constants;

import java.util.ArrayList;

/**
 * This class represents a collider for a ball.
 */
public class BallCollider extends Collider<Ball> {
    private final ArrayList<Line> lines;

    /**
     * Constructor.
     *
     * @param shape the ball to collide
     */
    public BallCollider(Ball shape) {
        super(shape);
        this.lines = new ArrayList<>();
    }

    /**
     * Collides with a shape.
     *
     * @param object the object to collide with
     */
    @Override
    public void collide(Shape object) {
        for (Line line : object.getPolygon(Constants.POLYGON_ACCURACY_FACTOR).getLines()) {
            if (this.getShape().isIntersecting(line)) {
                collide(line);
            }
        }
    }

    /**
     * Collides with a line.
     *
     * @param line the line to collide with
     */
    @Override
    public void collide(Line line) {
        this.lines.add(line);
    }

    /**
     * Changes this.changed according to the given line.
     *
     * @param line the line to collide with
     */
    private void calculateCollision(Line line) {
        double normalAngle = line.getAngle() + Math.PI / 2;
        Point normal = Point.fromAngleAndSpeed(normalAngle, 1);
        Velocity velocity = this.getShape().getVelocity();
        Point velocityProj = normal.copy().multiply(normal.dot(velocity));
        Point center = this.getShape().getCenter().subtract(line.getStart());
        Point centerProj = normal.copy().multiply(normal.dot(center)).normalize();
        Point newVelocity = centerProj.copy()
                .multiply(Constants.ELASTICITY_FACTOR * velocityProj.normal()).subtract(velocityProj);
        change(newVelocity);
    }

    /**
     * Applies the collision to the ball.
     */
    @Override
    public void apply() {
        boolean onTopIntersection = false;
        for (Line line : this.lines) {
            if (this.getShape().intersectionType(line) == Ball.IntersectionType.ON_TOP) {
                onTopIntersection = true;
                this.lines.stream().filter(l -> this.getShape().intersectionType(l)
                        == Ball.IntersectionType.ON_TOP).forEach(this::calculateCollision);
            }
        }
        if (!onTopIntersection) {
            for (Line line : this.lines) {
                calculateCollision(line);
            }
        }
        this.lines.clear();
        super.apply();
    }
}
