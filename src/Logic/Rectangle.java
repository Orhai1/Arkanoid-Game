// 209533041 Or Haibi
package Logic;


import java.util.ArrayList;
import java.util.List;

/**
 * The type Logic.Rectangle.
 */
public class Rectangle {
    private final Point upperLeft;
    private final double width;
    private final double height;
    private static final double EPSILON = 0.0001;

    /**
     * Instantiates a new Logic.Rectangle.
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft the upper left of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Intersection points java . util . list.
     *
     * @param line the line we want to check with the intersection point
     * @return the java . util . list- List of intersection points (possibly
     * empty) with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        //create an array of points. these points are points of intersection
        //with the rectangle.
        List<Point> intersectionPoints = new ArrayList<>();
        //checks if the line has intersection point with the upper line
        Line upperLine = new Line(this.upperLeft,
                new Point(this.upperLeft.getX() + this.width, this.upperLeft.
                        getY()));
        Point intersectionuUpper = line.intersectionWith(upperLine);
        //checks if there is a point (that is no null) and that is within the
        //bounds of the rectangle
        if (intersectionuUpper != null) {
            intersectionPoints.add(intersectionuUpper);
        }
        //checks if the line has intersection point with the bottom line
        Line bottomLine = new Line(new Point(this.upperLeft.getX(), this.
                upperLeft.getY() + this.height),
                new Point(this.upperLeft.getX() + this.width, this.
                        upperLeft.getY() + this.height));
        Point intersectionBottom = line.intersectionWith(bottomLine);
        //checks if there is a point (that is no null) and that is within the
        //bounds of the rectangle
        if (intersectionBottom != null) {
            intersectionPoints.add(intersectionBottom);
        }
        //checks if the line has intersection point with the left line
        Line leftLine = new Line(this.upperLeft, new Point(this.upperLeft.
                getX(), this.upperLeft.getY() + this.height));
        Point intersectionLeft = line.intersectionWith(leftLine);
        //checks if there is a point (that is no null) and that is within the
        //bounds of the rectangle
        if (intersectionLeft != null) {
            intersectionPoints.add(intersectionLeft);
        }
        //checks if the line has intersection point with the right line
        Line rightLine = new Line(new Point(this.upperLeft.getX() + this.
                width, this.upperLeft.getY()),
                new Point(this.upperLeft.getX() + this.width, this.
                        upperLeft.getY() + this.height));
        Point intersectionRight = line.intersectionWith(rightLine);
        //checks if there is a point (that is no null) and that is within the
        //bounds of the rectangle
        if (intersectionRight != null) {
            intersectionPoints.add(intersectionRight);
        }
        return intersectionPoints;
    }

    /**
     * Gets upper left.
     *
     * @return the upper left of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Gets width.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * Is Point Inside Rectangle.
     * Checks if a given point c with a specified radius is inside any of the
     * collision rectangles of the collidables objects.
     *
     * @param point      the point we want check if inside the rectangle.
     * @param radius the radius of the point.
     * @return boolean, returns true if the point is inside the rectangle,
     * and if not, returns false.
     */
    public boolean isPointInsideRectangle(Point point, int radius) {
        double x = point.getX();
        double y = point.getY();
        //upper left point
        double x1 = this.upperLeft.getX();
        double y1 = this.upperLeft.getY();
        //bottom left point
        double x2 = this.upperLeft.getX();
        double y2 = this.upperLeft.getY() + this.height;
        //upper right point
        double x3 = this.upperLeft.getX() + this.width;
        double y3 = this.upperLeft.getY();
        //bottom right point
        double x4 = this.upperLeft.getX() + this.width;
        double y4 = this.upperLeft.getY() + this.height;
        double nweR = (double) radius / 2;

        //check if the point is inside the rectangle boundaries
        if (x >= Math.min(x1, x3) - nweR && x <= Math.max(x1, x3) + nweR && y
                >= Math.min(y1, y2) - nweR && y <= Math.max(y1, y2) + nweR) {
            return true;
        }
        return false;
    }
    /**
     * Nums Equals .
     * If the difference between two doubles is smaller (than epsilon)
     * so the numbers are equals.
     * @param first the first num
     * @param sec the second num
     * @return true if the difference is smaller than epsilon, false otherwise.
     */
    public boolean numsEquals(double first, double sec) {
        return Math.abs(first - sec) < EPSILON;
    }

    /**
     * Compare.
     * Check if the first number is smaller the other number.
     * @param first the first num
     * @param sec the second num
     * @return true if the first num is smaller than the second num, false
     * otherwise.
     */
    public boolean compare(double first, double sec) {
        return Math.abs(first - sec) < EPSILON || first < sec;
    }
}

