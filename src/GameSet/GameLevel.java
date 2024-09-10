// 209533041 Or Haibi
package GameSet;

import Interface.Animation;
import Interface.Collidable;
import Interface.LevelInformation;
import Interface.Sprite;
import Logic.Point;
import Logic.Rectangle;
import Logic.ScoreIndicator;
import Logic.ScoreTrackingListener;
import Objects.Ball;
import Objects.Block;
import Objects.Paddle;
import Animation.AnimationRunner;
import Animation.PauseScreen;
import Animation.CountdownAnimation;
import Remover.BallRemover;
import Remover.Counter;
import Remover.BlockRemover;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;


import java.awt.Color;

/**
 * The type GameSet.Game.
 */
public class GameLevel implements Animation {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int SEC_COUNTING = 3;
    private static final int COUNTING = 4;
    private static final int RADIUS = 5;
    private static final int WIDTH_HEIGHT_FRAME = 10;
    private static final int PADDLE_HEIGHT = 20;
    private static final int TOP_BORDER = 25;
    private static final int SCORE_WINNER = 100;
    private static final int X_BALL = 395;
    private static final int Y_BALL = 575;
    private static final int Y_PADDLE = 580;
    private static final int RIGHT_FRAME = 790;



    /**
     * The Gui.
     */
    private final AnimationRunner runner;
    private boolean running;
    private final SpriteCollection sprites = new SpriteCollection();
    private final GameEnvironment environment = new GameEnvironment();
    private final KeyboardSensor keyboard;
    private Counter remainBlocks;
    private Counter remainBalls;
    private Counter score;
    private final LevelInformation levelInfo;

    /**
     * Instantiates a new GameLevel.
     *
     * @param levelInfo the LevelInformation representing the level settings.
     * @param key       the KeyboardSensor used to detect key presses.
     * @param run       the AnimationRunner used to run animations.
     * @param score     the Counter to track the score.
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor key,
                     AnimationRunner run, Counter score) {
        this.levelInfo = levelInfo;
        this.keyboard = key;
        this.runner = run;
        this.score = score;
    }

    /**
     * Gets game environment.
     *
     * @return the game environment
     */
    public GameEnvironment getGameEnvironment() {
        return this.environment;
    }

    /**
     * Gets Score.
     *
     * @param score score of the game
     */
    public void getScore(Counter score) {
        this.score = score;
    }

    /**
     * Add collidable.
     *
     * @param c the collidable we want to add to the game
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the sprite we want to sprite collection
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Remove Collidable.
     *
     * @param c the collidable we want to remove
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Remove Sprite.
     *
     * @param s the sprite we want to remove
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * Create Balls Paddle.
     * Create the paddle first.
     * Creating balls and add them to the game.
     */
    public void createBallsPaddle() {
        //create the paddle
        Point startPaddle =
                new Point((double) (((WIDTH - (TWO * WIDTH_HEIGHT_FRAME))
                        - this.levelInfo.paddleWidth()) / TWO), Y_PADDLE);
        Paddle paddle = new Paddle(new Rectangle(startPaddle, this.levelInfo.
                paddleWidth(), PADDLE_HEIGHT), Color.ORANGE, this.keyboard);
        //add the paddle to the game
        addCollidable(paddle);
        addSprite(paddle);
        //counter of the balls
        this.remainBalls = new Counter();
        //creating the balls according the numBalls
        for (int i = ZERO; i < this.levelInfo.numberOfBalls(); i++) {
            Ball ball = new Ball(new Point(X_BALL, Y_BALL), RADIUS, Color.WHITE);
            ball.setVelocity(this.levelInfo.initialBallVelocities().get(i).
                            getDx(),
                    this.levelInfo.initialBallVelocities().
                            get(i).getDy());
            //add the ball to tha game
            ball.setGameEnvironment(getGameEnvironment());
            ball.addToGame(this);
            //increase the counter of ball, because we create one ball
            this.remainBalls.increase(ONE);
        }
        //death-region block
        Block deathBlock = new Block(new Rectangle(new Point(ZERO, HEIGHT),
                WIDTH, ONE), Color.WHITE);
        //create the BallRemover and register it as a listener to the balls
        BallRemover ballRemover = new BallRemover(this, this.remainBalls);
        deathBlock.addToGame(this);
        deathBlock.addHitListener(ballRemover);
    }

    /**
     * Create Blocks.
     * Creating blocks and add them to the game.
     */
    public void createBlocks() {
        //create the BlockRemover and register it as a listener to all blocks
        this.remainBlocks = new Counter();
        ScoreTrackingListener scoreTrackingListener =
                new ScoreTrackingListener(this.score);
        BlockRemover blockRemover = new BlockRemover(this,
                this.remainBlocks);
        //create the frame blocks
        createFrameBlocks();
        //create and add the blocks to the game world in a pattern
        for (int i = ZERO; i < this.levelInfo.numberOfBlocksToRemove(); i++) {
            Block block = this.levelInfo.blocks().get(i);
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
            remainBlocks.increase(ONE);
        }
        //count the score
        ScoreIndicator scoreIndicator =
                new ScoreIndicator(this.score, this.levelInfo);
        scoreIndicator.addToGame(this);
    }

    /**
     * Create Frame Blocks.
     * Creating the frame blocks and add them to the game.
     */
    public void createFrameBlocks() {
        //create the double frame block
        Block rightBorder = new Block(new Rectangle(new Point(ZERO, ZERO),
                WIDTH_HEIGHT_FRAME,
                HEIGHT),
                Color.gray);
        Block leftBorder = new Block(new Rectangle(new Point(RIGHT_FRAME, ZERO),
                WIDTH_HEIGHT_FRAME,
                HEIGHT),
                Color.gray);
        Block topBorder = new Block(new Rectangle(new Point(WIDTH_HEIGHT_FRAME,
                ZERO),
                WIDTH, TOP_BORDER),
                Color.gray);
        //add the double frame block
        rightBorder.addToGame(this);
        leftBorder.addToGame(this);
        topBorder.addToGame(this);
    }

    /**
     * Initialize.
     * Calling to another method that create the balls, blocks and paddel.
     */
    public void initialize() {
        //set the background
        Sprite background = this.levelInfo.getBackground();
        this.sprites.addSprite(background);
        //create blocks, balls and paddle
        createBallsPaddle();
        createBlocks();
    }

    /**
     * Should Stop.
     *
     * @return true if the animation is not running.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Do One Frame.
     * Create one frame of animation.
     *
     * @param d the DrawSurface to draw on it the frame.
     */
    public void doOneFrame(DrawSurface d) {
        //drawing the sprites on the surface
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        //check if no more balls remaining
        if (remainBalls.getValue() == ZERO) {
            //stop the game
            this.running = false;
        }
        //check if no more blocks remaining
        if (remainBlocks.getValue() == ZERO) {
            //add 100 points for clearing the level
            this.score.increase(SCORE_WINNER);
            //stop the game
            this.running = false;
        }
        //if the key 'p' or 'P' or 'פ' being pressed
        if (this.keyboard.isPressed("p") || this.keyboard.isPressed("P")
                || this.keyboard.isPressed("פ")) {
            //start running the PauseScreen animation instead of the Game one
            this.runner.run(new PauseScreen(this.keyboard));
        }
    }

    /**
     * Run.
     * Starts the animation loop and runs the game.
     */
    public void run() {
        //countdown before the game start.
        CountdownAnimation countdownAnimation =
                new CountdownAnimation(SEC_COUNTING, COUNTING, this.sprites);
        this.runner.run(countdownAnimation);
        //sets the running flag to true, indicating that the animation is
        //running
        this.running = true;
        //use our runner to run the current animation, which is one turn of
        //the game
        this.runner.run(this);
    }

    /**
     * Gets Remains Ball.
     *
     * @return the remains ball that in the game.
     */
    public int getRemainsBall() {
        return this.remainBalls.getValue();
    }
}
