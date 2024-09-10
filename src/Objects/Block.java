// 209533041 Or Haibi
package Objects;

import GameSet.GameLevel;
import Logic.Rectangle;
import Interface.HitListener;
import Interface.Collidable;
import Interface.Sprite;
import Interface.HitNotifier;
import Logic.Velocity;
import Logic.Point;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Objects.Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final List<HitListener> hitListeners = new ArrayList<>();
    private final Rectangle rect;
    private final Color color;
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;

    /**
     * Create new Objects.Block.
     *
     * @param rect  the rectangle that is the block
     * @param color the color thr block
     */
    public Block(Rectangle rect, Color color) {
        this.rect = rect;
        this.color = color;
    }

    /**
     * Get Collision Logic.Rectangle.
     *
     * @return the Logic.Rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }
    /**
     * Hit.
     *
     * @param collisionPoint  the collision point between the rectangle to
     *                        the ball
     * @param currentVelocity the velocity of the ball
     * @param hitter          the ball that hits the objects
     * @return the Logic.Velocity, new velocity after the hit
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity
            currentVelocity) {
        double x = collisionPoint.getX();
        double y = collisionPoint.getY();
        Point[] corners = getCorners(this.rect);
        this.notifyHit(hitter);
        //if the ball hits the corners
        if (collisionPoint.equals(corners[ZERO])
                || collisionPoint.equals(corners[ONE])
                || collisionPoint.equals(corners[TWO])
                || collisionPoint.equals(corners[THREE])) {
            return new Velocity(-currentVelocity.getDx(),
                    -currentVelocity.getDy());
        }
        //if the ball hits the bottom line
        if (rect.compare(rect.getUpperLeft().getX(), x) && rect.compare(x,
                (rect.getUpperLeft().getX() + rect.getWidth()))
                && rect.numsEquals(y, rect.getUpperLeft().getY()
                + rect.getHeight())) {
            return new Velocity(currentVelocity.getDx(),
                    -currentVelocity.getDy());
        }
        //if the ball hits the upper line
        if (rect.compare(rect.getUpperLeft().getX(), x) && rect.compare(x,
                (rect.getUpperLeft().getX() + rect.getWidth()))
                && rect.numsEquals(y, rect.getUpperLeft().getY())) {
            return new Velocity(currentVelocity.getDx(),
                    -currentVelocity.getDy());
        }
        //if the ball hits left or right lines
        return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
    }
    /**
     * Get Corners.
     *
     * @param rectangle that we want to find its corners
     * @return an array of points, which contains the points of the rectangle
     */
    public Point[] getCorners(Rectangle rectangle) {
        Point topLeft = rectangle.getUpperLeft();
        double width = rectangle.getWidth();
        double height = rectangle.getHeight();

        Point topRight = new Point(topLeft.getX() + width,
                topLeft.getY());
        Point bottomLeft = new Point(topLeft.getX(), topLeft.getY()
                + height);
        Point bottomRight = new Point(topLeft.getX() + width,
                topLeft.getY() + height);

        return new Point[]{topLeft, topRight, bottomLeft, bottomRight};
    }

    /**
     * Draw on.
     * drawing the blocks according to the left point of the rectangle, width
     * and height of the rectangle.
     *
     * @param d the surface where drawing the ball.
     */
    public void drawOn(DrawSurface d) {
        //set the color and fill the rectangle
        d.setColor(color);
        d.fillRectangle((int) rect.getUpperLeft().getX(),
                (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(),
                (int) rect.getHeight());
        //set the color and draw the rectangle outline
        d.setColor(Color.BLACK);
        d.drawRectangle((int) rect.getUpperLeft().getX(),
                (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(),
                (int) rect.getHeight());
    }

    /**
     * TimePassed.
     * Responsible for the "life time" of the ball
     */
    public void timePassed() {
    }

    /**
     * Add to game.
     *
     * @param game the game we want to add him
     */
    public void addToGame(GameLevel game) {
        //add the block as both a sprite and a collidable to the game
        game.addSprite(this);
        game.addCollidable(this);
    }
    /**
     * Notify Hit.
     * Notifies all registered hit listeners that a hit event has occurred.
     * passing the current block and the hitting ball as parameters.
     *
     * @param hitter the ball that hit the block
     */
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    /**
     * Add HitListener.
     *
     * @param hl the object we want to add.
     */
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }
    /**
     * Remove HitListener.
     *
     * @param hl the object we want to remove.
     */
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }
    /**
     * Remove From Game.
     * Remove the collidbles and the sprites.
     * @param game we remove.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
}
