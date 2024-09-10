// 209533041 Or Haibi
package GameSet;

import Animation.AnimationRunner;
import Animation.KeyPressStoppableAnimation;
import Interface.Animation;
import Interface.LevelInformation;
import Animation.EndScreenAnimation;
import Remover.Counter;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {
    private final AnimationRunner animationRunner;
    private final KeyboardSensor keyboardSensor;
    private final Counter score;
    private boolean win;
    private final GUI gui;
    private static final int ZERO = 0;

    /**
     * Game Flow.
     * Instantiates a new Game flow (Constructor to Game flow).
     * @param ar      the AnimationRunner used to run animations.
     * @param ks      the KeyboardSensor used to detect key presses.
     * @param gui     the GUI object representing the game window.
     * @param counter the Counter object to track the score.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui,
                    Counter counter) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.score = counter;
        this.win = true;
        this.gui = gui;
    }
    /**
     * Run Levels.
     * Runs the game levels.
     * @param levels the list of LevelInformation objects representing the
     * levels to be played.
     */
    public void runLevels(List<LevelInformation> levels) {
        //running the game levels one by one
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor,
                    this.animationRunner, this.score);
            //initialize the level and prepare it for running
            level.initialize();
            level.run();
            //check if the level should stop
            if (level.shouldStop()) {
                //if the level stop because there is no balls in the game
                //anymore so the player lost
                if (level.getRemainsBall() == ZERO) {
                    this.win = false;
                    break;
                }
                //if the level stop but not because the balls ran out, so the
                //player did not lose and therefore this.win remains true
            }
        }
        //create an end screen animation based on the game outcome
        Animation end = new EndScreenAnimation(this.keyboardSensor, this.score,
                this.win);
        Animation key = new KeyPressStoppableAnimation(this.keyboardSensor,
                KeyboardSensor.SPACE_KEY, end);
        this.animationRunner.run(key);
        this.gui.close();
    }
}


