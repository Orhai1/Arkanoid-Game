// 209533041 Or Haibi
package Remover;

import Interface.HitListener;
import Objects.Ball;
import Objects.Block;
/**
 * Printing Hit Listener.
 */
public class PrintingHitListener implements HitListener {
    /**
     * Hit Event.
     * This method is called whenever a block is hit by a ball.
     * It prints a message that a block was hit.
     *
     * @param beingHit the block being hit
     * @param hitter   the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}
