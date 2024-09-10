// 209533041 Or Haibi
package Animation;

import Interface.Animation;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The type Animation runner.
 */
public class AnimationRunner {
    private final GUI gui;
    private int framesPerSecond;
    private final Sleeper sleeper;
    private static final int ZERO = 0;
    private static final int SECONDS = 60;
    private static final int MILLI_SECONDS = 1000;

    /**
     * Instantiates a new Animation runner.
     * Constructs an AnimationRunner.
     *
     * @param gui             the GUI on which display the animation.
     * @param framesPerSecond the desired frame for the animation.
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
        this.sleeper = new Sleeper();
    }

    /**
     * Run.
     * Runs the given animation.
     * @param animation the animation we want to run
     */
    public void run(Animation animation) {
        this.framesPerSecond = MILLI_SECONDS / SECONDS;
        int millisecondsPerFrame = this.framesPerSecond;
        while (!animation.shouldStop()) {
            // timing
            long startTime = System.currentTimeMillis();
            //create the whole screen
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > ZERO) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}