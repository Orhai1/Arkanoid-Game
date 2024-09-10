// 209533041 Or Haibi
package Logic;

import Interface.HitListener;
import Objects.Ball;
import Objects.Block;
import Remover.Counter;
/**
 * A listener that tracks the score when blocks are hit.
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;
    private static final int FIVE = 5;
    /**
     * Score Tracking Listener.
     * Construct of ScoreTrackingListener.
     * @param scoreCounter the counter that count the score in the game.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * Hit Event.
     * Increase the score by 5 for each block hit.
     * @param beingHit the block being hit.
     * @param hitter the ball that hits the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(FIVE);
    }
}

