// 209533041 Or Haibi
package Logic;

/**
 * The type Logic.Line.
 */
public class Line {
    private static final int ZERO = 0;
    private final Point start;
    private final Point end;

    /**
     * The method, Logic.Line, is a constructor.
     *
     * @param start the start point of the line
     * @param end   the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * The method, start, return the value of the start point.
     *
     * @return the start point.
     */
    public Point start() {
        return this.start;
    }

    /**
     * The method, isIntersecting, checks if the lines are intersect.
     *
     * @param other the line we want check.
     * @return true is the lines are intersect and not if they aren't.
     */
    public boolean isIntersecting(Line other) {
        Point intersection = intersectionWith(other);
        return intersection != null;
    }

    /**
     * The method, intersectionWith, calculates the point intersection of two
     * straight lines.
     *
     * @param other the other line we want to calculate with the intersection point.
     * @return the intersection point and if there is no intersection point returns
     * null.
     */
    public Point intersectionWith(Line other) {
        double epsilon = 0.0001;
        ////placing the values into new variables respectively
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();
        // calculate the determinant of the lines
        double d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        //if the lines are not parallel but start or end at the same point
        //(the 4 methods of the following)
        if (this.start.equals(other.start)) {
            return this.start;
        }
        if (this.start.equals(other.end)) {
            return this.start;
        }
        if (this.end.equals(other.start)) {
            return this.end;
        }
        if (this.end.equals(other.end)) {
            return this.end;
        }
        //check if the lines are parallel
        if (d == ZERO) {
            return null;
        }
        //calculate the intersection point
        double xi = ((x3 - x4) * (x1 * y2 - y1 * x2)
                - (x1 - x2) * (x3 * y4 - y3 * x4)) / d;
        double yi = ((y3 - y4) * (x1 * y2 - y1 * x2)
                - (y1 - y2) * (x3 * y4 - y3 * x4)) / d;
        //check if the intersection point is within the bounds of the two lines
        if (xi >= Math.min(x1, x2) - epsilon && xi <= Math.max(x1, x2) + epsilon
                && xi >= Math.min(x3, x4) - epsilon && xi <= Math.max(x3, x4)
                + epsilon && yi >= Math.min(y1, y2) - epsilon && yi
                <= Math.max(y1, y2) + epsilon && yi >= Math.min(y3, y4)
                - epsilon && yi <= Math.max(y3, y4) + epsilon) {
            return new Point(xi, yi);
        } else {
            return null;
        }
    }

    /**
     * The method, equals, checks if two lines are equal.
     *
     * @param other the other line we want to check with if the lines are equal.
     * @return true if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        if (start.getX() == other.start.getX() && start.getY() == other.start.
                getY()) {
            if (end.getX() == other.end.getX() && end.getY() == other.end.
                    getY()) {
                return true;
            }
        }
        if (start.getX() == other.end.getX() && start.getY() == other.end.getY()
        ) {
            return end.getX() == other.start.getX() && end.getY() == other.start
                    .getY();
        }
        return false;
    }

    /**
     * The method, the Closest intersection to start of line point.
     * Checks the closest point of intersection between the line and the sides
     * of the rectangle.
     *
     * @param rect the rectangle with which we check an intersection point
     * @return the intersection point. if there is no intersection point
     * return null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //create an array of intersection points with the rectangle
        java.util.List<Point> intersectionPoints = rect.intersectionPoints(this);
        //if there is no  intersection points with the rectangle
        if (intersectionPoints.isEmpty()) {
            return null;
        }
        Point closestIntersection = intersectionPoints.get(ZERO);
        double closestDistance = this.start().distance(closestIntersection);
        //calculate the distance between each point to the start point
        for (Point point : intersectionPoints) {
            double distance = this.start.distance(point);
            if (distance < closestDistance) {
                closestIntersection = point;
                closestDistance = distance;
            }
        }
        return closestIntersection;
    }
}

