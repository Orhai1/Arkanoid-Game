// 209533041 Or Haibi
package Interface;

import biuoop.DrawSurface;

/**
 * The interface Animation.
 */
public interface Animation {
    /**
     * Do One Frame.
     * Create one frame of animation.
     *
     * @param d the DrawSurface to draw on it the frame (the whole game).
     */
    void doOneFrame(DrawSurface d);
    /**
     * Should stop boolean.
     * Check if the animation should stop.
     * @return the boolean true if the animation should stop, false otherwise.
     */
    boolean shouldStop();
}
