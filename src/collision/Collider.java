package collision;

import geometry.Line;
import geometry.Point;
import physics.Velocity;

/**
 * This class represents a collider for a shape.
 *
 * @param <T> the type of the shape
 */
public abstract class Collider<T extends Colliding> {
    private final T shape;
    private final Velocity changed;

    /**
     * Constructor.
     *
     * @param shape the shape to collide
     */
    public Collider(T shape) {
        this.shape = shape;
        this.changed = shape.getVelocity();
    }

    /**
     * Changes the velocity of the shape.
     *
     * @param velocity the velocity to change
     */
    protected void change(Point velocity) {
        this.changed.transform(velocity);
    }

    /**
     * Collides with a collidable.
     *
     * @param object the object to collide with
     */
    public abstract void defaultCollide(Collidable object);

    /**
     * Collides with a collidable.
     *
     * @param object the object to collide with
     */
    public void collide(Collidable object) {
        defaultCollide(object);
    }

    /**
     * Collides with a line.
     *
     * @param object the line to collide with
     */
    public void collide(Line object) {
        defaultCollide(object);
    }

    /**
     * @return the shape or the collider
     */
    public T getShape() {
        return this.shape;
    }

    /**
     * Applies the changes to the shape.
     */
    public void apply() {
        this.shape.setVelocity(this.changed);
    }

    /**
     * Updates the speed of the shape.
     */
    public void updateSpeed() {
        this.changed.move(this.shape.getVelocity());
    }
}
