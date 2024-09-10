// 209533041 Or Haibi
package GameSet;

import Interface.Collidable;
import Logic.CollisionInfo;
import Logic.Line;
import Logic.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * The type GameSet.GameEnvironment.
 */
public class GameEnvironment {
    //create an array of collidables.
    private final List<Collidable> collidables = new ArrayList<>();
    private static final int ZERO = 0;
    private static final int ONE = 1;

    /**
     * Add collidable.
     * Adding each collidable to the list (to the array)
     *
     * @param c the collidable we need to add
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Collidables list.
     *
     * @return the list of the collidables
     */
    public List<Collidable> collidables() {
        return new ArrayList<>(this.collidables);
    }

    /**
     * Gets collidables.
     *
     * @return the collidables
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * Gets the closest collision.
     * Find the closest collision point for each collidable
     *
     * @param trajectory the trajectory we want to find him the closet distance
     * @return the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestIntersection = null;
        Collidable closestCollidable = null;
        double closestDistance = Double.MAX_VALUE;
        //find the closest collision point for each collidable
        for (Collidable collidable : collidables) {
            Point intersection = trajectory.
                    closestIntersectionToStartOfLine(collidable
                            .getCollisionRectangle());
            if (intersection != null) {
                //checks if the intersection point is closer than the previously
                //found the closest intersection point
                if (intersection.distance(trajectory.start()) < closestDistance) {
                    closestDistance = intersection.distance(trajectory.start());
                    closestIntersection = intersection;
                    closestCollidable = collidable;
                }
            }
        }
        //if there is no intersection between the trajectory to the collidable
        if (closestIntersection == null) {
            return null;
        }
        //returns a collisionInfo object containing the closest intersection point
        //and collidable object
        return new CollisionInfo(closestIntersection, closestCollidable);
    }
    /**
     * Remove Collidable.
     * Removing the collidable.
     *
     * @param c the collidable we want to remove.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * Check P.
     * Checks if a given point c with a specified radius is inside any of the
     * collision rectangles of the collidables objects.
     *
     * @param p      the point we want check if inside the collision rectangle.
     * @param radius the radius of the point.
     * @return boolean, returns true if there is a collision rectangle where
     * the point is inside. and if not, returns false.
     */
    public boolean checkP(Point p, int radius) {
        for (int i = ZERO; i < collidables.size() - ONE; i++) {
            //check if the point is inside the collision rectangle
            if (collidables.get(i).getCollisionRectangle().
                    isPointInsideRectangle(p, radius)) {
                return true;
            }
        }
        return false;
    }
}
