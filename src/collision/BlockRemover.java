package collision;

import graphics.Ball;
import graphics.Block;
import main.Game;
import util.Counter;

/**
 * A class that removes a block from the game when it is hit.
 * It also decreases the number of remaining blocks in the game.
 */
public class BlockRemover implements HitListener {
    private final Game game;
    private final Counter remainingBlocks;

    /**
     * Constructor.
     *
     * @param game            the game to remove the block from
     * @param remainingBlocks the counter for the remaining blocks
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * This method is called whenever a block is hit by a ball.
     * If the ball's color does not match the block's color, the block is removed from the game,
     * and the remaining blocks counter is decreased.
     *
     * @param beingHit the block that was hit
     * @param hitter   the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (hitter.isIntersecting(beingHit) && !hitter.getColor().equals(beingHit.getColor())) {
            hitter.setColor(beingHit.getColor());
            beingHit.removeFromGame(this.game);
            this.remainingBlocks.decrease(1);
            if (this.remainingBlocks.getValue() <= 0) {
                this.game.end();
            }
        }
    }
}
