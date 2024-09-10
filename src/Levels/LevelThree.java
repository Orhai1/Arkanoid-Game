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
 * The type Level three.
 */
public class LevelThree implements LevelInformation {
    private static final int ZERO = 0;
    private static final double BALL_SPEED = 2.5;
    private static final int PADDLE_SPEED = 10;
    private static final int PADDLE_WIDTH = 180;
    private static final int NUMBER_OF_BALLS = 2;
    private static final int NUMBER_OF_BLOCKS = 40;
    private static final int MAX_ROWS = 5;
    private static final int MAX_COL = 10;
    private static final int WIDHT_BLOCK = 45;
    private static final int HEGHIT_BLOCK = 25;
    private static final int RANGE_X = 340;
    private static final int RANGE_Y = 125;
    private static final int WIDTH_FRAME = 830;
    private static final int HIEGHT_FRAME = 630;
    private static final int FIRST_ANGLE = 350;
    private static final int SEC_ANGLE = 20;
    /**
     * The constant DARK_GREEN.
     */
    public static final Color DARK_GREEN = new Color(0, 153, 0);
    private static final Color[] COLORS = {Color.GRAY, Color.RED, Color.YELLOW,
            Color.BLUE, Color.WHITE};

    /**
     * Number Of Balls.
     *
     * @return the number of balls in this level.
     */
    public int numberOfBalls() {
        return NUMBER_OF_BALLS;
    }

    /**
     * Initial Ball Velocities.
     *
     * @return a list of the initial velocities for the balls.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(FIRST_ANGLE, BALL_SPEED));
        velocities.add(Velocity.fromAngleAndSpeed(SEC_ANGLE, BALL_SPEED));
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
        return "Level 3: Green 3";
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
        return new Block(rect, DARK_GREEN);
    }

    /**
     * Blocks.
     * Create the blocks in the level.
     *
     * @return a list of blocks in the level.
     */
    public List<Block> blocks() {
        //create the blocks list of the level
        List<Block> blocks = new ArrayList<>();
        for (int i = ZERO; i < MAX_ROWS; i++) {
            for (int j = i; j < MAX_COL; j++) {
                Block block = new Block(new Rectangle(new Point(RANGE_X
                        + (WIDHT_BLOCK * j),
                        RANGE_Y + (i * HEGHIT_BLOCK)), WIDHT_BLOCK,
                        HEGHIT_BLOCK), COLORS[i]);
                blocks.add(block);
            }
        }
        return blocks;
    }

    /**
     * Number Of Blocks To Remove.
     *
     * @return the number of blocks that need to be removed to pass the level.
     */
    public int numberOfBlocksToRemove() {
        return NUMBER_OF_BLOCKS;
    }
}

