// 209533041 Or Haibi
package Interface;

import Logic.Point;
import Logic.Rectangle;
import Logic.Velocity;
import Objects.Ball;

/**
 * The interface Interface.Collidable.
 */
public interface Collidable {
    /**
     * Gets collision rectangle.
     *
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Hit velocity.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @param hitter the ball that hits the objects
     * @return the new velocity according the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint,
                 Velocity currentVelocity);
}

