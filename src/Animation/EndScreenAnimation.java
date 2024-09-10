// 209533041 Or Haibi
package Animation;
import Interface.Animation;
import Remover.Counter;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type End screen animation.
 */
public class EndScreenAnimation implements Animation {
    private static final int ZERO = 0;
    private static final int FONT_SIZE = 32;
    private static final int TEXT_X_Y = 200;
    private static final int HEIGHT = 600;
    private static final int WIDTH = 800;
    private boolean shouldStop;
    private final boolean win;
    private final Counter score;
    private final KeyboardSensor keyboardSensor;

    /**
     * End Screen Animation.
     * Instantiates a new EndScreenAnimation.
     *
     * @param k     the KeyboardSensor used to detect key presses.
     * @param score the Counter object representing the player's score.
     * @param win   a boolean indicating whether the player won the game.
     */
    public EndScreenAnimation(KeyboardSensor k, Counter score,
                              Boolean win) {
        this.shouldStop = false;
        this.score = score;
        this.win = win;
        this.keyboardSensor = k;
    }

    /**
     * Do One Frame.
     * Performs one frame of the animation.
     *
     * @param surface the DrawSurface on which to draw the frame.
     */
    public void doOneFrame(DrawSurface surface) {
        //create the end screen, on iu we will print the score
        surface.setColor(Color.gray);
        surface.fillRectangle(ZERO, ZERO, WIDTH, HEIGHT);
        //if the player win
        if (this.win) {
            surface.setColor(Color.BLACK);
            surface.drawText(TEXT_X_Y, TEXT_X_Y, "You Win! Your score is "
                    + score.getValue(), FONT_SIZE);
            //if the player lose
        } else {
            surface.setColor(Color.BLACK);
            surface.drawText(TEXT_X_Y, TEXT_X_Y, "Game Over. Your score is "
                    + score.getValue(), FONT_SIZE);
        }
        //check if the space key is pressed on the keyboard
        if (this.keyboardSensor.isPressed(keyboardSensor.SPACE_KEY)) {
            //if the space key is pressed, set the 'shouldStop' to true
            this.shouldStop = true;
        }
    }
    /**
     * Should Stop.
     * Checks if the animation should stop.
     *
     * @return true if the animation should stop, false otherwise.
     */
    public boolean shouldStop() {
        return this.shouldStop;
    }
}

