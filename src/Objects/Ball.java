// 209533041 Or Haibi
package Objects;

import GameSet.GameEnvironment;
import GameSet.GameLevel;
import Logic.CollisionInfo;
import Logic.Line;
import Logic.Point;
import Interface.Sprite;
import Logic.Velocity;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Objects.Ball.
 */
public class Ball implements Sprite {
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TEN = 10;
    private static final int WIDTH = 800;
    private static final int TOP_HIGHT = 25;
    private static final int VELOCITY = 30;
    private Point center;
    private final int radius;
    private final Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * Instantiates a new Objects.Ball (constructor).
     *
     * @param center the center point of the ball
     * @param r      the value of the radius of the center point of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.radius = r;
        this.center = center;
        this.color = color;
        this.velocity = new Velocity(VELOCITY, VELOCITY);
    }

    /**
     * Gets x.
     *
     * @return the x value of the center point.
     */
    public int getX() {
        double x = this.center.getX();
        return (int) x;
    }

    /**
     * Gets y.
     *
     * @return the y value of the center point.
     */
    public int getY() {
        double y = this.center.getY();
        return (int) y;
    }

    /**
     * Gets size.
     *
     * @return the size of the radius of the ball.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Draw on.
     * drawing the ball according to point and color.
     *
     * @param surface the surface where drawing the ball.
     */
    public void drawOn(DrawSurface surface) {
        //the circumference of the ball
        surface.setColor(Color.BLACK);
        surface.fillCircle(this.getX(), this.getY(), this.getSize() + ONE);
        //draw the ball
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.getSize() - ONE);
    }

    /**
     * TimePassed.
     * Responsible for the "life time" of the ball
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Sets velocity.
     * constructor
     *
     * @param velocity the velocity of the ball
     */
    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    /**
     * Sets velocity.
     * creates new velocity by dx adn dy
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Sets game environment.
     *
     * @param gameEnvironment the game environment
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Check Center.
     * Checks the range of the frame and change the velocity and the point
     * center accordingly
     */
    public void checkCenter() {
        //if is out of right range
        if (this.center.getX() >= WIDTH - TEN) {
            this.center = new Point(WIDTH - TEN - this.radius,
                    this.center.getY());
            this.setVelocity(-getVelocity().getDx(), getVelocity().getDy());
        }
        //if is out of left range
        if (this.center.getX() <= ZERO + TEN) {
            this.center = new Point(TEN + this.radius, this.center.getY());
            this.setVelocity(-getVelocity().getDx(), getVelocity().getDy());
        }
        //if is out of upper range
        if (this.center.getY() <= ZERO + TOP_HIGHT) {
            this.center = new Point(TEN + this.center.getX(), this.radius);
            this.setVelocity(getVelocity().getDx(), -getVelocity().getDy());
        } else {
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }

    /**
     * Checking.
     * Checks the potential trajectory of the ball and returns a line
     * representing the path it would take.
     *
     * @return Line representing the potential trajectory of the ball
     */
    public Line checking() {
        double epsilon = 0.00001;
        double newX = this.velocity.getDx();
        double newY = this.velocity.getDy();
        //check if the object is potentially moving out of the right range
        if (newX <= ZERO && newY >= ZERO) {
            //calculate the end point of the line
            Point endPoint =
                    new Point(this.center.getX() + newX - this.radius
                            - epsilon, this.center.getY() + newY
                            + this.radius + epsilon);
            return new Line(this.center, endPoint);
        }
        //check if the object is potentially moving out of the left range
        if (newX >= ZERO && newY <= ZERO) {
            //calculate the end point of the line
            Point endPoint =
                    new Point(this.center.getX() + newX + this.radius
                            + epsilon, this.center.getY() + newY
                            - this.radius - epsilon);
            return new Line(this.center, endPoint);
        }
        //check if the object is potentially moving out of the bottom or upper
        //range
        Point endPoint;
        if (newX >= ZERO && newY >= ZERO) {
            //calculate the end point of the line
            endPoint = new Point(this.center.getX() + newX + this.radius
                    + epsilon, this.center.getY() + newY + this.radius
                    + epsilon);
        } else {
            //calculate the end point of the line
            endPoint = new Point(this.center.getX() + newX - this.radius
                    - epsilon, this.center.getY() + newY - this.radius
                    - epsilon);
        }
        return new Line(this.center, endPoint);
    }

    /**
     * Move one step.
     * moves the ball and every move of a step check if it is within the drawing
     * frame or on the frame block. If not, it changes the velocity of the ball
     * in order not to go out of the frame.
     */
    public void moveOneStep() {
        this.center = this.getVelocity().applyToPoint(this.center);
        //calculate the ball trajectory
        Line trajectory = checking();
        //check if there are collisions along the trajectory
        CollisionInfo collision = this.gameEnvironment.
                getClosestCollision(trajectory);
        if (collision != null) {
            this.velocity = collision.collisionObject().hit(this,
                    collision.collisionPoint(), this.getVelocity());
            //check if the ball is inside a block
            if (gameEnvironment.checkP(this.center, this.radius)) {
                this.velocity = new Velocity(-this.velocity.getDx(),
                        -this.velocity.getDy());
            }
        } else {
            //update the ball's center to avoid going out of the frame
            checkCenter();
        }
    }
    /**
     * Add to game.
     * Add the ball as a sprite to the game
     *
     * @param game the game we want to add him the srpites
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * Remove FromGame.
     * Remove the ball as a sprite from the game
     *
     * @param game the game we want to remove
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}

