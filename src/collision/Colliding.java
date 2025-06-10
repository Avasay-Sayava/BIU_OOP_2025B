package collision;

import geometry.Shape;
import physics.Physical;
import physics.Velocity;

/**
 * This interface represents a physical shape in 2D space.
 */
public interface Colliding extends Physical, Shape {
    /**
     * @return the collider of the object
     */
    Collider<? extends Colliding> getCollider();

    /**
     * @return the velocity of the object
     */
    Velocity getVelocity();
}
