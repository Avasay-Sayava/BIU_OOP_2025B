package collision;

import graphics.Ball;
import graphics.Block;
import main.Game;
import util.Counter;

/**
 * A class that removes a ball from the game when it is hit.
 * It also decreases the number of remaining balls in the game.
 */
public class BallRemover implements HitListener {
    private final Game game;
    private final Counter remainingBalls;

    /**
     * Constructor for BallRemover.
     *
     * @param game    the game instance
     * @param counter the counter that keeps track of the number of remaining balls
     */
    public BallRemover(Game game, Counter counter) {
        this.game = game;
        this.remainingBalls = counter;
    }

    /**
     * This method is called whenever a block is hit by a ball.
     * It removes the ball from the game and decreases the number of remaining balls.
     *
     * @param beingHit the block that was hit
     * @param hitter   the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.game.removeSprite(hitter);
        this.game.removeColliding(hitter);
        this.remainingBalls.decrease(1);
        if (remainingBalls.getValue() == 0) {
            this.game.end();
        }
    }
}
