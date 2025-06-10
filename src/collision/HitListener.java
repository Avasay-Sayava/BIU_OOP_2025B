package collision;

import graphics.Ball;
import graphics.Block;

/**
 * HitListener is an interface that defines a listener for hit events.
 * It is used to notify when a block is hit by a ball.
 */
public interface HitListener {
    /**
     * This method is called whenever a block is hit by a ball.
     *
     * @param beingHit the block that was hit
     * @param hitter   the ball that hit the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
