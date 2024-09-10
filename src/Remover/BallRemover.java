// 209533041 Or Haibi
package Remover;

import GameSet.GameLevel;
import Interface.HitListener;
import Objects.Ball;
import Objects.Block;

/**
 * Ball Remover class.
 */
public class BallRemover implements HitListener {
    private final GameLevel game;

    private final Counter remainBalls;
    private static final int ONE = 1;
    /**
     * Ball Remover.
     * BallRemover constructor.
     * @param game        the game
     * @param ballCounter the counter for remaining balls.
     */
    public BallRemover(GameLevel game, Counter ballCounter) {
        this.game = game;
        this.remainBalls = ballCounter;
    }
    /**
     * Hit Event.
     * This method is called whenever the ball hits a block.
     * It removes the ball from the game and decreases the ball counter.
     *
     * @param beingHit the block being hit
     * @param hitter   the ball that hits the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        //remove the ball from the game
        hitter.removeFromGame(this.game);
        //decrease the ball counter
        this.remainBalls.decrease(ONE);
    }
}

