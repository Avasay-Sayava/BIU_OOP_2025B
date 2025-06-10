package physics;

import util.DisgustingButYouSaidINeedToHaveItYey;

/**
 * This interface represents a physical object.
 */
public interface Physical {

    /**
     * @return The velocity of the object
     */
    Velocity getVelocity();

    /**
     * @param dx the x coordinate of the new point
     * @param dy the y coordinate of the new point
     */
    void setVelocity(double dx, double dy);

    /**
     * @param velocity the new velocity of the object
     */
    void setVelocity(Velocity velocity);

    /**
     * Moves the object one step in the direction of its velocity.
     */
    @DisgustingButYouSaidINeedToHaveItYey
    void moveOneStep();
}
