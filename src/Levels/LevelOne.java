// 209533041 Or Haibi
package Levels;
import Interface.LevelInformation;
import Interface.Sprite;
import Logic.Point;
import Logic.Rectangle;
import Logic.Velocity;
import Objects.Block;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level one.
 */
public class LevelOne implements LevelInformation {
    private static final int ZERO = 0;
    private static final int NUM_BALLS = 1;
    private static final int NUM_BLOCKS = 1;
    private static final int BALL_SPEED = 2;
    private static final int PADDLE_SPEED = 10;
    private static final int BLOCK_SIZE = 30;
    private static final int PADDLE_WIDTH = 100;
    private static final int Y_BLOCK = 175;
    private static final int X_BLOCK = 380;
    private static final int WIDTH_FRAME = 830;
    private static final int HIEGHT_FRAME = 630;

    /**
     * Number Of Balls.
     *
     * @return the number of balls in this level.
     */
    public int numberOfBalls() {
        return NUM_BALLS;
    }
    /**
     * Initial Ball Velocities.
     *
     * @return a list of the initial velocities for the balls.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(ZERO, -BALL_SPEED));
        return velocities;
    }
    /**
     * Paddle Speed.
     *
     * @return the speed of the paddle.
     */
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }
    /**
     * Paddle Width.
     *
     * @return the width of the paddle.
     */
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }
    /**
     * Level Name.
     *
     * @return the name of the level.
     */
    public String levelName() {
        return "Level Name: Direct Hit";
    }
    /**
     * Get Background.
     *
     * @return Sprite, the background of the level, that of a sprite type.
     */
    public Sprite getBackground() {
        //create the block (the size) that should be the background.
        Rectangle rect = new Rectangle(new Point(ZERO, ZERO), WIDTH_FRAME,
                HIEGHT_FRAME);
        return new Block(rect, Color.BLACK);
    }
    /**
     * Blocks.
     * Create the blocks in the level.
     * @return a list of blocks in the level.
     */
    public List<Block> blocks() {
        //create the blocks list of the level
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block(new Rectangle(new Point(X_BLOCK, Y_BLOCK),
                BLOCK_SIZE, BLOCK_SIZE), Color.RED));
        return blocks;
    }
    /**
     * Number Of Blocks To Remove.
     *
     * @return the number of blocks that need to be removed to pass the level.
     */
    public int numberOfBlocksToRemove() {
        return NUM_BLOCKS;
    }
}
