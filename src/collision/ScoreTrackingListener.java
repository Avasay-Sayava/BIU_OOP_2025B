package collision;

import graphics.Ball;
import graphics.Block;
import util.Counter;

/**
 * ScoreTrackingListener is a HitListener that tracks the score of the game.
 * It increases the score by 5 points each time a block is hit.
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;

    /**
     * Constructor.
     *
     * @param scoreCounter the counter to track the score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method is called whenever a block is hit by a ball.
     * It increases the score by 5 points.
     *
     * @param beingHit the block that was hit
     * @param hitter   the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}
