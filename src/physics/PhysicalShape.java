package physics;

import collision.Collider;
import geometry.Shape;

/**
 * This interface represents a physical shape in 2D space.
 */
public interface PhysicalShape extends Physical, Shape {
    /**
     * @return the collider of the object
     */
    Collider<? extends PhysicalShape> getCollider();

    /**
     * @return the velocity of the object
     */
    Velocity getVelocity();
}
