// 209533041 Or Haibi
package Logic;
import GameSet.GameLevel;
import Interface.LevelInformation;
import biuoop.DrawSurface;
import java.awt.Color;
import Interface.Sprite;
import Remover.Counter;
/**
 * Represents a score indicator in the game.
 * Displays the current score at the top of the screen.
 */
public class ScoreIndicator implements Sprite {
    private final Counter score;
    private final LevelInformation levelInfo;
    private static final int ZERO = 0;
    private static final int HEIGHT = 15;
    private static final int SIZE_STR = 12;
    private static final int WIDTH = 800;
    private static final int MIDDLE = 380;
    private static final int RIGHT_SIDE = 600;
    /**
     * Score Indicator.
     * Constructs a new ScoreIndicator.
     * @param score Counter to count the score.
     * @param levelInfo the current level in the game.
     */
    public ScoreIndicator(Counter score, LevelInformation levelInfo) {
        this.score = score;
        this.levelInfo = levelInfo;
    }
    /**
     * Draw On
     * Draws the score indicator on the given surface.
     * @param surface the surface to draw on
     */
    public void drawOn(DrawSurface surface) {
        //draw the rectangle that we will write on it the score
        surface.setColor(Color.WHITE);
        surface.fillRectangle(ZERO, ZERO, WIDTH, HEIGHT);
        //draw the string that show the score
        surface.setColor(Color.BLACK);
        surface.drawText(MIDDLE, SIZE_STR, "Score:" + this.score.getValue(),
                SIZE_STR);
        surface.drawText(RIGHT_SIDE, SIZE_STR, levelInfo.levelName(), SIZE_STR);
    }
    /**
     * Time Passed
     * This method is empty because the score indicator doesn't change over
     * time.
     */
    public void timePassed() {
    }
    /**
     * Add Game.
     * Adds the score indicator to the game by adding it as a sprite.
     * @param game the game we want to add the score indicator to.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
