// 209533041 Or Haibi
package Objects;

import GameSet.GameLevel;
import Interface.Collidable;
import Logic.Point;
import Logic.Rectangle;
import Interface.Sprite;
import Logic.Velocity;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type Objects.Paddle.
 */
public class Paddle implements Sprite, Collidable {
    private final biuoop.KeyboardSensor keyboard;
    private Rectangle paddle;
    private final Color color;
    private static final int PADDLE_SPEED = 5;
    private static final int DOUBLE_FRAME_RANGE = 10;

    private static final int WIDTH = 800;
    private static final double D_TWO = 2.0;
    private static final double D_FIVE = 5.0;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int SIXTY_ANGEL = 60;
    private static final int THIRTY_ANGEL = 30;
    private static final int ROUND_ANGEL = 360;
    /**
     * Instantiates a new Objects.Paddle.
     *
     * @param rect     the rectangle
     * @param color    the color of the rectangle
     * @param keyboard the keyboard
     */
// Constructor
    public Paddle(Rectangle rect, Color color, KeyboardSensor keyboard) {
        this.paddle = rect;
        this.color = color;
        this.keyboard = keyboard;
    }

    /**
     * Move left.
     * Move the paddle left by a predefined amount
     */
    public void moveLeft() {
        double paddleSpeed = PADDLE_SPEED;
        double currentX = this.paddle.getUpperLeft().getX();
        //if out of frame from left side
        if (currentX - paddleSpeed >= DOUBLE_FRAME_RANGE) {
            this.paddle = new Rectangle(new Point(currentX - paddleSpeed,
                    this.paddle.getUpperLeft().getY()),
                    this.paddle.getWidth(), this.paddle.getHeight());
        } else {
            this.paddle = new Rectangle(new Point(DOUBLE_FRAME_RANGE,
                    this.paddle.getUpperLeft().getY()),
                    this.paddle.getWidth(), this.paddle.getHeight());
        }
    }

    /**
     * Move right.
     * Move the paddle right by a predefined amount
     */
    public void moveRight() {
        double paddleSpeed = PADDLE_SPEED;
        double currentX = this.paddle.getUpperLeft().getX();
        //if out of frame from right side
        if (currentX + this.paddle.getWidth() + paddleSpeed
                <= WIDTH - DOUBLE_FRAME_RANGE) {
            this.paddle = new Rectangle(new Point(currentX + paddleSpeed,
                    this.paddle.getUpperLeft().getY()),
                    this.paddle.getWidth(), this.paddle.getHeight());
        } else {
            this.paddle =
                    new Rectangle(new Point(WIDTH - DOUBLE_FRAME_RANGE - this
                            .paddle.getWidth(),
                            this.paddle.getUpperLeft().getY()), this.paddle.
                            getWidth(),
                            this.paddle.getHeight());
        }
    }
    /**
     * Time Passed.
     * Interface.Sprite interface methods
     * Responsible for the "life time" of the paddle
     */
    public void timePassed() {
        //move the paddle left or right based on player input
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }
    /**
     * Draw on.
     * drawing the blocks according to the left point of the rectangle, width
     * and height of the rectangle.
     *
     * @param d the surface where drawing the ball.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        int x = (int) this.paddle.getUpperLeft().getX();
        int y = (int) this.paddle.getUpperLeft().getY();
        int width = (int) this.paddle.getWidth();
        int height = (int) this.paddle.getHeight();
        d.fillRectangle(x, y, width, height);
    }
    /**
     * Get Collision Logic.Rectangle.
     * Interface.Collidable interface methods
     * @return the paddle (rectangle).
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }
    /**
     * Hit.
     * @param collisionPoint  the collision point between the paddle to the ball
     * @param currentVelocity the velocity of the ball
     * @param hitter the ball that hits the objects
     * @return the Logic.Velocity, new velocity after the hit
     */
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        double regionSize = this.paddle.getWidth() / D_FIVE;
        double paddleY = this.paddle.getUpperLeft().getY();
        double paddleCenter = this.paddle.getUpperLeft().getX() + (this.paddle
                .getWidth() / D_TWO);
        double distanceFromCenter = collisionPoint.getX() - paddleCenter;
        //if there was a collision between the paddle to the ball
        if (collisionPoint.getY() == paddleY) {
            //check which part of the paddle was hit, and change the velocity
            //accordingly
            //the collision is in region 1
            if (this.paddle.getUpperLeft().getX() <= collisionPoint.getX()
                    && collisionPoint
                    .getX() <= this.paddle.getUpperLeft().getX() + regionSize) {
                currentVelocity = Velocity.fromAngleAndSpeed(-SIXTY_ANGEL,
                        Math.sqrt(Math.pow(currentVelocity.getDx(), TWO) + Math.
                                pow(currentVelocity.getDy(), TWO)));
                return currentVelocity;
                //the collision is in region 2
            } else if (this.paddle.getUpperLeft().getX() + regionSize
                    <= collisionPoint
                    .getX() && collisionPoint.getX() <= this.paddle.
                    getUpperLeft()
                    .getX() + TWO * regionSize) {
                currentVelocity = Velocity.fromAngleAndSpeed(-THIRTY_ANGEL,
                        Math.sqrt(Math.pow(currentVelocity.getDx(), TWO) + Math.
                                pow(currentVelocity.getDy(), TWO)));
                return currentVelocity;
                //the collision is in region 3
            } else if (this.paddle.getUpperLeft().getX() + TWO * regionSize
                    <= collisionPoint
                    .getX() && collisionPoint.getX() <= this.paddle.
                    getUpperLeft()
                    .getX() + THREE * regionSize) {
                currentVelocity = Velocity.fromAngleAndSpeed(ROUND_ANGEL,
                        Math.sqrt(Math.pow(currentVelocity.getDx(), TWO) + Math.
                                pow(currentVelocity.getDy(), TWO)));
                return currentVelocity;
                //the collision is in region 4
            } else if (this.paddle.getUpperLeft().getX() + THREE * regionSize
                    <= collisionPoint
                    .getX() && collisionPoint.getX() <= this.paddle.
                    getUpperLeft().getX() + FOUR * regionSize) {
                currentVelocity = Velocity.fromAngleAndSpeed(THIRTY_ANGEL,
                        Math.sqrt(Math.pow(currentVelocity.getDx(), TWO) + Math.
                                pow(currentVelocity.getDy(), TWO)));
                return currentVelocity;
                //the collision is in region 5
            } else if (this.paddle.getUpperLeft().getX() + FOUR * regionSize
                    <= collisionPoint
                    .getX() && collisionPoint.getX() <= this.paddle.
                    getUpperLeft().getX() + this.paddle.getWidth()) {
                currentVelocity = Velocity.fromAngleAndSpeed(SIXTY_ANGEL,
                        Math.sqrt(Math.pow(currentVelocity.getDx(), TWO) + Math.
                                pow(currentVelocity.getDy(), TWO)));
                return currentVelocity;
            }
        }
        return currentVelocity;
    }

    /**
     * Add to game.
     * Add the paddle to the game.
     * @param game the game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }
    public double getX() {
        return this.paddle.getUpperLeft().getX();
    }
}

