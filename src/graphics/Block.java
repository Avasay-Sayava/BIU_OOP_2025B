package graphics;

import biuoop.DrawSurface;
import collision.HitListener;
import collision.HitNotifier;
import geometry.Point;
import geometry.Rectangle;
import main.Game;
import main.Main;
import physics.Velocity;
import util.DisgustingButYouSaidINeedToHaveItYey;
import util.ValueSet;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

/**
 * A block is a rectangle with a color.
 */
public class Block extends Rectangle implements Sprite, Colored, HitNotifier {
    private Color color;
    private final Set<HitListener> hitListeners;

    /**
     * Copy constructor.
     *
     * @param other the block to copy
     */
    public Block(Block other) {
        this(other, other.getColor());
    }

    /**
     * Copy constructor.
     *
     * @param other the rectangle to copy
     * @param color the color of the block
     */
    public Block(Rectangle other, Color color) {
        this(other.topLeft(), other.getWidth(), other.getHeight(), color);
    }

    /**
     * Constructor.
     *
     * @param topLeft the top left point of the rectangle
     * @param width   the width of the rectangle
     * @param height  the height of the rectangle
     * @param color   the color of the block
     */
    public Block(Point topLeft, double width, double height, Color color) {
        this(topLeft, new Point(topLeft).add(new Point(width, height)), color);
    }

    /**
     * Constructor.
     *
     * @param point1 the first point
     * @param point2 the second point
     * @param color  the color of the block
     */
    public Block(Point point1, Point point2, Color color) {
        this(point1.getX(), point1.getY(), point2.getX(), point2.getY(), color);
    }

    /**
     * Constructor.
     *
     * @param x1    the x coordinate of the first point
     * @param y1    the y coordinate of the first point
     * @param x2    the x coordinate of the second point
     * @param y2    the y coordinate of the second point
     * @param color the color of the block
     */
    public Block(double x1, double y1, double x2, double y2, Color color) {
        super(x1, y1, x2, y2);
        this.color = color;
        this.hitListeners = new ValueSet<>();
    }

    /**
     * @return The color of the object
     */
    @Override
    public Color getColor() {
        return this.color;
    }

    /**
     * @param color The color to set
     */
    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Checks if the color of this object is equal to the color of another Colored object.
     *
     * @param other The other Colored object to compare with
     * @return true if the colors are equal, false otherwise
     */
    @Override
    public boolean colorsEqual(Colored other) {
        return Objects.equals(this.getColor(), other.getColor());
    }

    /**
     * @param d the DrawSurface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        Point topLeft = topLeft();
        d.setColor(getColor());
        d.fillRectangle((int) Math.round(topLeft.getX()), (int) Math.round(topLeft.getY()),
                (int) Math.round(getWidth()), (int) Math.round(getHeight()));
        d.setColor(Color.BLACK);
        d.drawRectangle((int) Math.round(topLeft.getX()), (int) Math.round(topLeft.getY()),
                (int) Math.round(getWidth()), (int) Math.round(getHeight()));
    }

    /**
     * @param ball the ball to check
     * @return true if the ball's color matches this block's color, false otherwise
     */
    @DisgustingButYouSaidINeedToHaveItYey
    @SuppressWarnings("unused")
    public boolean ballColorMatch(Ball ball) {
        return colorsEqual(ball);
    }

    /**
     * passes the time doing a useless thing.
     */
    @Override
    public void timePassed() {

    }

    /**
     * Adds this block to the game.
     *
     * @param game the game to add this block to
     */
    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * Removes this block from the game.
     *
     * @param game the game to remove this block from
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
        game.removeCollidable(this);
        game.recalculateCollisions();
    }

    private void notifyHit(Ball hitter) {
        new ArrayList<>(this.hitListeners).forEach(hl -> hl.hitEvent(this, hitter));
    }

    /**
     * Handles a hit event.
     *
     * @param hitter            the ball that hit this block
     * @param ignored           a point that is ignored in this implementation
     * @param collisionVelocity the velocity of the collision
     * @return the new velocity after the hit
     */
    public Velocity hit(Ball hitter, Point ignored, Velocity collisionVelocity) {
        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }
        return collisionVelocity;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity collisionVelocity) {
        return hit(Main.getGame().findBall(collisionPoint).orElseThrow(), collisionPoint, collisionVelocity);
    }

    /**
     * Adds a hit listener to the notifier.
     *
     * @param hl the hit listener to add
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Removes a hit listener from the notifier.
     *
     * @param hl the hit listener to remove
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
