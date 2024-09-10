// 209533041 Or Haibi
package GameSet;

import Interface.Sprite;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * The type GameSet.SpriteCollection.
 */
public class SpriteCollection {
    //create an array of sprites.
    private final List<Sprite> spriteCollection =  new ArrayList<>();
    private static final int ZERO = 0;

    /**
     * Add sprite.
     *
     * @param s the sprite we want to add to the collection
     */
    public void addSprite(Sprite s) {
        this.spriteCollection.add(s);
    }
    /**
     * Remove sprite.
     *
     * @param s the sprite we want to remove
     */
    public void removeSprite(Sprite s) {
        this.spriteCollection.remove(s);
    }
    /**
     * Notify all time passed.
     * Call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = ZERO; i < spriteCollection.size(); i++) {
            spriteCollection.get(i).timePassed();
        }
    }

    /**
     * Draw all on.
     * Call drawOn(d) on all sprites.
     * @param d the sureface we draw the on it the sprites
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : spriteCollection) {
            sprite.drawOn(d);
        }
    }
}