// 209533041 Or Haibi
package Interface;

import Logic.Velocity;
import Objects.Block;

import java.util.List;

/**
 * The interface Level information.
 */
public interface LevelInformation {
    /**
     * Number Of Balls.
     *
     * @return the numbers of balls.
     */
    int numberOfBalls();

    /**
     * Initial Ball Velocities.
     * The initial velocity of each ball.
     * @return the list of velocities of the balls.
     */
    List<Velocity> initialBallVelocities();

    /**
     * Paddle Speed.
     *
     * @return the speed of paddle.
     */
    int paddleSpeed();

    /**
     * Paddle Width.
     *
     * @return the int
     */
    int paddleWidth();

    /**
     * Level Name.
     * The level name will be displayed at the top of the screen.
     * @return the string
     */
    String levelName();

    /**
     * Gets Background.
     *
     * @return the background- a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * Blocks List.
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return the list blocks.
     */
    List<Block> blocks();

    /**
     * Number Of Blocks To Remove.
     * Number of blocks that should be removed before the level is considered
     * to be "cleared".
     * @return the number of blocks we need to remove.
     */
    int numberOfBlocksToRemove();
}
