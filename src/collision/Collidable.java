package collision;

import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import physics.Velocity;
import util.DisgustingButYouSaidINeedToHaveItYey;

/**
 * This interface represents a collidable object in 2D space.
 */
public interface Collidable extends Shape {
    /**
     * @return the bounds of the object
     */
    @DisgustingButYouSaidINeedToHaveItYey
    @SuppressWarnings("unused")
    default Rectangle getCollisionRectangle() {
        return getBounds();
    }

    /**
     * This method is called when the object collides with another object.
     *
     * @param collisionPoint    the point of collision
     * @param collisionVelocity the velocity of the object at the time of collision
     * @return the new velocity of the object after the collision
     */
    Velocity hit(Point collisionPoint, Velocity collisionVelocity);
}
