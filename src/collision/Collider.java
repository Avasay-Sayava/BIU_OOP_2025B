package collision;

import geometry.Line;
import geometry.Point;
import geometry.Shape;
import physics.PhysicalShape;
import physics.Velocity;

/**
 * This class represents a collider for a shape.
 *
 * @param <T> the type of the shape
 */
public abstract class Collider<T extends PhysicalShape> {
    private final T shape;
    private Velocity changed;

    /**
     * Constructor.
     *
     * @param shape the shape to collide
     */
    public Collider(T shape) {
        this.shape = shape;
        this.changed = shape.getVelocity();
    }

    protected void change(Point velocity) {
        this.changed.transform(velocity);
    }

    /**
     * Collides with a shape.
     *
     * @param object the object to collide with
     */
    public abstract void collide(Shape object);

    /**
     * Collides with a line.
     *
     * @param object the line to collide with
     */
    public abstract void collide(Line object);

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
