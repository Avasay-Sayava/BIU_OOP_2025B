package graphics;

import biuoop.KeyboardSensor;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import main.Game;
import physics.Physical;
import physics.Velocity;
import util.Constants;
import util.DisgustingButYouSaidINeedToHaveItYey;

import java.awt.Color;

/**
 * A paddle is a block that can move left and right.
 */
public class Paddle extends Block implements Physical {
    private final KeyboardSensor keyboard;
    private final Velocity velocity;

    /**
     * Copy constructor.
     *
     * @param other the paddle to copy
     */
    public Paddle(Paddle other) {
        this(other, other.getKeyboard());
    }

    /**
     * Copy constructor.
     *
     * @param other    the block to copy
     * @param keyboard the keyboard sensor to copy
     */
    public Paddle(Block other, KeyboardSensor keyboard) {
        this(other, other.getColor(), keyboard);
    }

    /**
     * Copy constructor.
     *
     * @param other    the rectangle to copy
     * @param color    the color of the block
     * @param keyboard the keyboard sensor to copy
     */
    public Paddle(Rectangle other, Color color, KeyboardSensor keyboard) {
        this(other.topLeft(), other.bottomRight(), color, keyboard);
    }

    /**
     * Constructor.
     *
     * @param topLeft  the top left point of the rectangle
     * @param width    the width of the rectangle
     * @param height   the height of the rectangle
     * @param color    the color of the block
     * @param keyboard the keyboard sensor to copy
     */
    public Paddle(Point topLeft, double width, double height, Color color, KeyboardSensor keyboard) {
        this(topLeft, new Point(width, height).add(topLeft), color, keyboard);
    }

    /**
     * Constructor.
     *
     * @param point1   the first point
     * @param point2   the second point
     * @param color    the color of the block
     * @param keyboard the keyboard sensor to copy
     */
    public Paddle(Point point1, Point point2, Color color, KeyboardSensor keyboard) {
        this(point1.getX(), point1.getY(), point2.getX(), point2.getY(), color, keyboard);
    }

    /**
     * Constructor.
     *
     * @param x1       the x coordinate of the first point
     * @param y1       the y coordinate of the first point
     * @param x2       the x coordinate of the second point
     * @param y2       the y coordinate of the second point
     * @param color    the color of the block
     * @param keyboard the keyboard sensor to copy
     */
    public Paddle(double x1, double y1, double x2, double y2, Color color, KeyboardSensor keyboard) {
        super(x1, y1, x2, y2, color);
        this.keyboard = keyboard;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * Changes the paddle's velocity to the left.
     */
    public void moveLeft() {
        this.velocity.move(-Constants.PADDLE_SPEED, 0);
    }

    /**
     * Changes the paddle's velocity to the right.
     */
    public void moveRight() {
        this.velocity.move(Constants.PADDLE_SPEED, 0);
    }

    /**
     * Stops the paddle's movement.
     */
    public void stop() {
        this.velocity.move(0, 0);
    }

    /**
     * A useless function that does nothing.
     *
     * @param g the game to add the paddle to
     */
    @DisgustingButYouSaidINeedToHaveItYey
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * @return The keyboard sensor of the paddle
     */
    public KeyboardSensor getKeyboard() {
        return this.keyboard;
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
    }

    /**
     * @param velocity the new velocity of the object
     */
    @Override
    public void setVelocity(Velocity velocity) {
        this.velocity.move(velocity);
    }

    /**
     * Moves the object one step in the direction of its velocity.
     */
    @Override
    public void moveOneStep() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY) ^ this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
                moveLeft();
            } else {
                moveRight();
            }
        } else {
            stop();
        }
        transform(this.velocity.withFPS(Constants.FPS));
    }


    /**
     * passes the time doing a useless thing.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * This method is called when the object collides with another object.
     *
     * @param collisionPoint    the point of collision
     * @param collisionVelocity the velocity of the object at the time of collision
     * @return the new velocity of the object after the collision
     */
    @Override
    public Velocity hit(Point collisionPoint, Velocity collisionVelocity) {
        double percentage = 1 - (collisionPoint.getX() - left()) / getWidth();
        percentage *= Constants.PADDLE_BOUNCING_RANGE;
        percentage += (1 - Constants.PADDLE_BOUNCING_RANGE) / 2;
        return new Line(left(), bottom(), right(), bottom()).hit(collisionPoint,
                Velocity.fromAngleAndSpeed(percentage * Math.PI, collisionVelocity.getSpeed()));
    }
}
