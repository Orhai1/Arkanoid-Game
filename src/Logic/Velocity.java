// 209533041 Or Haibi
package Logic;

/**
 * The type Logic.Velocity.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Instantiates a new Logic.Velocity (constructor).
     *
     * @param dx the dx
     * @param dy the dy
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Apply to point.
     * moving the point - take a point with position (x,y) and return a new
     * point
     * with new position (x+dx, y+dy)
     *
     * @param p the point
     * @return the new point with the new values for x and y
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }


    /**
     * Gets dx.
     *
     * @return the dx
     */
    public double getDx() {
        return dx;
    }

    /**
     * Gets dy.
     *
     * @return the dy
     */
    public double getDy() {
        return dy;
    }

    /**
     * Sets dx.
     * set new dx according the input
     *
     * @param dx the new dx
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Sets dy.
     * set new dy according the input
     *
     * @param dy the new dy
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * From angle and speed velocity.
     * calculate the new velocity (with dx and dy) from angle and speed
     *
     * @param angle the angle of the ball
     * @param speed the speed of the ball
     * @return the new velocity of the ball
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = -speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }
}
