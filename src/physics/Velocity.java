package physics;

import geometry.Point;
import graphics.Movable;
import util.NotUsedButYouSaidINeedToHaveItYey;

/**
 * This class represents a velocity in 2D space.
 */
public class Velocity extends Point implements Movable, Physical {
    /**
     * Creates a new velocity from the given angle and speed.
     *
     * @param angle the angle of the velocity
     * @param speed the speed of the velocity
     * @return a velocity with the given angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        return new Velocity(Point.fromAngleAndSpeed(angle, speed));
    }

    /**
     * @return the angle of the velocity
     */
    public double getAngle() {
        return Math.atan2(getY(), getX());
    }

    /**
     * @return the speed of the velocity
     */
    public double getSpeed() {
        return Math.sqrt(getX() * getX() + getY() * getY());
    }

    /**
     * Copy constructor.
     *
     * @param other The point to copy
     */
    public Velocity(Point other) {
        this(other.getX(), other.getY());
    }

    /**
     * Constructor.
     *
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public Velocity(double x, double y) {
        super(x, y);
    }

    /**
     * Moves the object by the given dx and dy.
     *
     * @param p the point to move
     * @return the point after moving
     */
    @NotUsedButYouSaidINeedToHaveItYey
    @SuppressWarnings("unused")
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.getX(), p.getY() + this.getY());
    }

    /**
     * Moves the object by the given dx and dy.
     *
     * @param fps the frames per second
     * @return the point after moving
     */
    public Velocity withFPS(int fps) {
        return new Velocity(this.getX() / fps, this.getY() / fps);
    }


    /**
     * @return The velocity of the object
     */
    @Override
    public Velocity getVelocity() {
        return new Velocity(0, 0);
    }

    /**
     * @param dx the x coordinate of the new point
     * @param dy the y coordinate of the new point
     */
    @Override
    public void setVelocity(double dx, double dy) {
    }

    /**
     * @param velocity the new velocity of the object
     */
    @Override
    public void setVelocity(Velocity velocity) {
    }

    /**
     * Moves the object one step in the direction of its velocity (None).
     */
    @Override
    public void moveOneStep() {
    }

    /**
     * @param factor the scalar to multiply by
     * @return the point after multiplication
     */
    public Velocity multiply(double factor) {
        move(factor * getX(), factor * getY());
        return this;
    }
}
