package graphics.animation;

import biuoop.DrawSurface;
import geometry.Circle;
import geometry.Point;
import graphics.Ball;

import java.awt.Color;

/**
 * An animated ball is a ball with an animator.
 */
public class AnimatedBall extends Ball implements AnimatedSprite {
    private final Animator animator;

    /**
     * Copy constructor.
     *
     * @param other the animated ball to copy
     */
    public AnimatedBall(AnimatedBall other) {
        this(other, other.getAnimator());
    }

    /**
     * Copy constructor.
     *
     * @param other    the ball to copy
     * @param animator the animator to copy
     */
    public AnimatedBall(Ball other, Animator animator) {
        this(other, other.getColor(), animator);
    }

    /**
     * Copy constructor from circle.
     *
     * @param other    the circle to copy
     * @param color    the color of the ball
     * @param animator the animator to copy
     */
    public AnimatedBall(Circle other, Color color, Animator animator) {
        this(other, other.getRadius(), color, animator);
    }

    /**
     * Constructor.
     *
     * @param center   the center of the ball
     * @param radius   the radius of the ball
     * @param color    the color of the ball
     * @param animator the animator to copy
     */
    public AnimatedBall(Point center, int radius, Color color, Animator animator) {
        this(center.getX(), center.getY(), radius, color, animator);
    }

    /**
     * Constructor.
     *
     * @param x        the x coordinate of the center of the ball
     * @param y        the y coordinate of the center of the ball
     * @param radius   the radius of the ball
     * @param color    the color of the ball
     * @param animator the animator to copy
     */
    public AnimatedBall(double x, double y, int radius, Color color, Animator animator) {
        super(x, y, radius, color);
        this.animator = animator;
    }

    /**
     * passes the time doing a useless thing.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * @return the animator of the ball
     */
    @Override
    public Animator getAnimator() {
        return this.animator;
    }

    @Override
    public void drawOn(DrawSurface d) {
        super.drawOn(d);
        d.drawImage((int) Math.round(getX() - getRadius()),
                (int) Math.round(getY() - getRadius()), this.animator.get());
    }
}
