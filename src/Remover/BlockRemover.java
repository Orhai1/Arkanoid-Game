// 209533041 Or Haibi
package Remover;

import GameSet.GameLevel;
import Interface.HitListener;
import Objects.Ball;
import Objects.Block;
/**
 * Block Remover class.
 */
public class BlockRemover implements HitListener {
    private final GameLevel game;

    private final Counter remainBlocks;
    private static final int ONE = 1;
    /**
     * Block Remover.
     * BlockRemover constructor.
     *
     * @param gameLevel     the game
     * @param removedBlocks the counter for remaining blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.game = gameLevel;
        this.remainBlocks = removedBlocks;
    }
    /**
     * Hit Event.
     * This method is called whenever a block is hit by a ball.
     * It removes the block from the game and decreases the block counter.
     *
     * @param beingHit the block being hit
     * @param hitter   the ball that hits the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        //remove this listener from the block
        beingHit.removeHitListener(this);
        //remove the block from the game
        beingHit.removeFromGame(this.game);
        //decrease the block counter
        this.remainBlocks.decrease(ONE);
    }
}

