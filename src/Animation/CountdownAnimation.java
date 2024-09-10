// 209533041 Or Haibi
package Animation;
import GameSet.SpriteCollection;
import biuoop.DrawSurface;
import Interface.Animation;

import java.awt.Color;

/**
 * The type Count Down Animation.
 */
public class CountdownAnimation implements Animation {
    private final double numOfSeconds;
    private final int countFrom;
    private final SpriteCollection gameScreen;
    private int currentCount;
    private long startTime;
    private boolean stop;
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int MIDDLE = 2;
    private static final int SIZE_TEXT = 80;
    private static final int THOUSAND = 1000;

    /**
     * Countdown Animation.
     * Constructor for the CountdownAnimation class.
     *
     * @param numOfSeconds the total time of the countdown in seconds.
     * @param countFrom    the number to count down from.
     * @param gameScreen   the SpriteCollection representing the game screen.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
                              SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.currentCount = countFrom;
        this.startTime = ZERO;
        this.stop = false;
    }

    /**
     * Do One Frame.
     * Perform one frame of the countdown animation.
     *
     * @param surface the DrawSurface to draw on.
     */
    public void doOneFrame(DrawSurface surface) {
        //calculate the time passed from the start of the animation
        long passedTime = System.currentTimeMillis() - startTime;

        //draw the game screen
        this.gameScreen.drawAllOn(surface);

        //show the current count on the screen
        surface.setColor(Color.YELLOW);
        surface.drawText(surface.getWidth() / MIDDLE, surface.getHeight()
                / MIDDLE, String.valueOf(this.currentCount), SIZE_TEXT);

        //if it's time to move to the next count
        if (passedTime >= (this.numOfSeconds / this.countFrom) * THOUSAND) {
            //decrease the count and reset the start time
            this.currentCount--;
            this.startTime = System.currentTimeMillis();
        }

        //if the countdown has finished
        if (this.currentCount < ONE) {
            this.stop = true;
        }
    }

    /**
     * Should Stop.
     * Check if the countdown animation should stop.
     *
     * @return true if the animation should stop, false otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
