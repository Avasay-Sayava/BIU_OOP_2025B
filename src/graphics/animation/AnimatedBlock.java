package graphics.animation;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import graphics.Block;

import java.awt.Color;

/**
 * An animated block is a block with an animator.
 */
public class AnimatedBlock extends Block implements AnimatedSprite {
    private final Animator animator;

    /**
     * Copy constructor.
     *
     * @param other the animated block to copy
     */
    public AnimatedBlock(AnimatedBlock other) {
        this(other, other.getAnimator());
    }

    /**
     * Copy constructor.
     *
     * @param other    the block to copy
     * @param animator the animator to copy
     */
    public AnimatedBlock(Block other, Animator animator) {
        this(other, other.getColor(), animator);
    }

    /**
     * Copy constructor.
     *
     * @param other    the rectangle to copy
     * @param color    the color of the block
     * @param animator the animator to copy
     */
    public AnimatedBlock(Rectangle other, Color color, Animator animator) {
        this(other.topLeft(), other.bottomRight(), color, animator);
    }

    /**
     * Constructor.
     *
     * @param topLeft  the top left point of the rectangle
     * @param width    the width of the rectangle
     * @param height   the height of the rectangle
     * @param color    the color of the block
     * @param animator the animator to copy
     */
    public AnimatedBlock(Point topLeft, double width, double height, Color color, Animator animator) {
        this(topLeft, new Point(width, height).add(topLeft), color, animator);
    }

    /**
     * Constructor.
     *
     * @param point1   the first point
     * @param point2   the second point
     * @param color    the color of the block
     * @param animator the animator to copy
     */
    public AnimatedBlock(Point point1, Point point2, Color color, Animator animator) {
        this(point1.getX(), point1.getY(), point2.getX(), point2.getY(), color, animator);
    }

    /**
     * Constructor.
     *
     * @param x1       the x coordinate of the first point
     * @param y1       the y coordinate of the first point
     * @param x2       the x coordinate of the second point
     * @param y2       the y coordinate of the second point
     * @param color    the color of the block
     * @param animator the animator to copy
     */
    public AnimatedBlock(double x1, double y1, double x2, double y2, Color color, Animator animator) {
        super(x1, y1, x2, y2, color);
        this.animator = animator;
    }

    /**
     * passes the time doing a useless thing.
     */
    @Override
    public void timePassed() {

    }

    /**
     * @return the animator of the block
     */
    @Override
    public Animator getAnimator() {
        return this.animator;
    }

    @Override
    public void drawOn(DrawSurface d) {
        super.drawOn(d);
        d.drawImage((int) Math.round(topLeft().getX()), (int) Math.round(topLeft().getY()), this.animator.get());
    }
}
