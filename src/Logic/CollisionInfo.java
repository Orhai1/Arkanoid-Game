// 209533041 Or Haibi
package Logic;

import Interface.Collidable;

/**
 * The type GameSet.GameEnvironment.
 */
public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collisionObject;

    /**
     * Instantiates a new Collision info.
     *
     * @param collisionPoint  the collision point
     * @param collisionObject the collision object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Collision point.
     *
     * @return the collision point;
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * Collision object collidable.
     *
     * @return the collidable object
     */
    public Collidable collisionObject() {

        return collisionObject;
    }
}

