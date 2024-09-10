// 209533041 Or Haibi
package Interface;

import Objects.Ball;
import Objects.Block;
/**
 * The interface Interface.HitListener.
 */
public interface HitListener {
    /**
     * Hit Event.
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit the block that is being hit by the Ball.
     * @param hitter the ball that is doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}

