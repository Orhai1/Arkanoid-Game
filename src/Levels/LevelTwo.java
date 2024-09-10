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
 * The type Level two.
 */
public class LevelTwo implements LevelInformation {
    private static final int ZERO = 0;
    private static final int TWO = 2;
    private static final int BALL_SPEED = 3;
    private static final int PADDLE_SPEED = 10;
    private static final int NUMBER_OF_BALLS = 10;
    private static final int EDGE_SPACE = 10;
    private static final int NUMBER_OF_BLOCKS = 15;
    private static final int BLOCK_HEIGHT = 25;
    private static final int BLOCK_WIDTH = 52;
    private static final int Y_BLOCK = 150;
    private static final int PADDLE_WIDTH = 300;
    private static final int ANGLE_L = 295;
    private static final int ANGLE_R = 8;
    private static final int HIEGHT_FRAME = 630;
    private static final int WIDTH_FRAME = 830;
    /**
     * The constant LIGHT_BLUE.
     */
    public static final Color LIGHT_BLUE = new Color(51, 204, 255);
    private final Color[] colors = {Color.RED, Color.RED, Color.ORANGE,
            Color.ORANGE, Color.YELLOW, Color.YELLOW, Color.GREEN,
            Color.GREEN, Color.GREEN, Color.BLUE, Color.BLUE, Color.PINK,
            Color.PINK, LIGHT_BLUE, LIGHT_BLUE};
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
        //reset angle for the balls
        int angleL = ANGLE_L;
        int angleR = ANGLE_R;
        //create velocities for the balls on the left side
        for (int i = ZERO; i < NUMBER_OF_BALLS / TWO; i++) {
            angleL = angleL + EDGE_SPACE;
            velocities.add(Velocity.fromAngleAndSpeed(angleL, BALL_SPEED));
        }
        //create velocities for the balls on the right side
        for (int i = ZERO; i < NUMBER_OF_BALLS / TWO; i++) {
            angleR = angleR + EDGE_SPACE;
            velocities.add(Velocity.fromAngleAndSpeed(angleR, BALL_SPEED));
        }
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
        return "Level 2: Wide Easy";
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
        return new Block(rect, Color.WHITE);
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
        int blockWidth = BLOCK_WIDTH;
        for (int i = ZERO; i < NUMBER_OF_BLOCKS; i++) {
            int blockX = EDGE_SPACE + i * blockWidth;
            blocks.add(new Block(new Rectangle(new Point(blockX, Y_BLOCK),
                    blockWidth, BLOCK_HEIGHT), colors[i]));
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
