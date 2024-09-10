// 209533041 Or Haibi
package Animation;

import Interface.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Pause screen.
 */
public class PauseScreen implements Animation {
    private final KeyboardSensor keyboard;
    private boolean stop;
    private static final int TWO = 2;
    private static final int MIDDLE_SCREEN = 165;
    private static final int SIZE_TEXT = 32;

    /**
     * Instantiates a new Pause screen.
     * Constructor for the PauseScreen.
     *
     * @param k the KeyboardSensor used to detect key presses.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }
    /**
     * Do One Frame
     * Perform one frame of the pause screen animation.
     *
     * @param d the DrawSurface to draw on.
     */
    public void doOneFrame(DrawSurface d) {
        //draw the "paused -- press space to continue" message on the screen
        d.drawText(MIDDLE_SCREEN, d.getHeight() / TWO,
                "paused -- press space to continue", SIZE_TEXT);
        //if the space key is pressed to resume the game
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }
    /**
     * Should Stop.
     * Check if the animation should stop.
     *
     * @return boolean true if the animation should stop, false otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}