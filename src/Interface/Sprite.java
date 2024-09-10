// 209533041 Or Haibi
package Interface;
import biuoop.DrawSurface;

/**
 * The interface Interface.Sprite.
 */
public interface Sprite {
    /**
     * Draw On.
     * Draw the sprite to the screen
     * @param d the sureface we want to draw on it
     */
    void drawOn(DrawSurface d);

    /**
     * Time Passed.
     * notify the sprite that time has passed
     */
    void timePassed();
}