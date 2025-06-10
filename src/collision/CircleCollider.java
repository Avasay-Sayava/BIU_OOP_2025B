package collision;

import geometry.Circle;
import geometry.Line;

/**
 * This class represents a collider for a ball.
 */
public class CircleCollider extends Collider<Circle> {
    private int count = 0;

    /**
     * Constructor.
     *
     * @param shape the ball to collide
     */
    public CircleCollider(Circle shape) {
        super(shape);
    }

    /**
     * Collides with a collidable.
     *
     * @param object the object to collide with
     */
    @Override
    public void defaultCollide(Collidable object) {
        this.count++;
        change(object.hit(this.getShape().getLastCenter(), this.getShape().getVelocity())
                .subtract(this.getShape().getVelocity()));
    }

    /**
     * Collides with a colliding object.
     *
     * @param line the object to collide with
     */
    @Override
    public void collide(Line line) {
        this.count++;
        defaultCollide(line);
    }

    /**
     * Applies the collision to the ball.
     */
    @Override
    public void apply() {
        if (this.count == 0) {
            this.getShape().updateLastCenter();
        } else {
            this.count = 0;
        }
        super.apply();
    }
}
