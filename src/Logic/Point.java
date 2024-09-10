// 209533041 Or Haibi
package Logic;

/**
 * The type Logic.Point.
 */
public class Point {
    private double x;
    private double y;

    /**
     * The method, Logic.Point, is a constructor.
     *
     * @param x the x value of the point
     * @param y the y value of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * The method, distance, calculates the distance between two points.
     *
     * @param other the second point
     * @return the distance between the two points.
     */
    public double distance(Point other) {
        double disy = this.y - other.y;
        double disx = this.x - other.x;
        return Math.sqrt(disx * disx + disy * disy);
    }

    /**
     * The method, equals, checks if the points (x and y) are equal.
     * In addition, the method also checks the points are equal when X or Y is
     * equal to an almost round point.
     *
     * @param other the other point we want to check with
     * @return true is the points are equal and false if not.
     */
    public boolean equals(Point other) {
        return (Math.abs(this.x - other.x) < 0.00000000000001) && (Math.abs(this
                .y - other.y) < 0.00000000000001);
    }

    /**
     * The method, getX, return the x value of this point.
     *
     * @return the this.x.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Sets x.
     * The method, setX, giving new value fo x of the point
     *
     * @param x the x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * The method, getY, return the y value of this point.
     *
     * @return the this.y.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets y.
     * The method, setY, giving new value fo y of the point
     *
     * @param y the y
     */
    public void setY(int y) {
        this.y = y;
    }
}