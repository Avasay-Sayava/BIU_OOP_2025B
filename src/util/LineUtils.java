package util;

import geometry.Line;

/**
 * The utils for the Line class.
 */
public class LineUtils {
    /**
     * Checks if the two points of the second line are on different sides of the first line.
     * If at least one point is on the first line, it is considered to be on the opposite side of the other point.
     *
     * @param first  The first line
     * @param second The second line
     * @return If the lines semi-intersect
     */
    public static boolean wcc(Line first, Line second) {
        return PointUtils.isClockwise(first.getStart(), first.getEnd(), second.getStart())
                != PointUtils.isClockwiseOrCollinear(first.getStart(), first.getEnd(), second.getEnd())
                || PointUtils.isClockwiseOrCollinear(first.getStart(), first.getEnd(), second.getStart())
                != PointUtils.isClockwise(first.getStart(), first.getEnd(), second.getEnd());
    }

    /**
     * @param lines The lines to check intersection with
     * @return True if any of the lines intersect, false otherwise
     */
    public static boolean areIntersecting(Line... lines) {
        // check if the lines are null or have less than 2 lines - therefore no intersection
        if (lines == null || lines.length < 2) {
            return false;
        }

        // check if the all the lines intersect with each other
        boolean intersecting = true;
        for (int i = 0; i < lines.length && intersecting; i++) {
            intersecting = lines[i].isIntersecting(lines);
        }

        return intersecting;
    }

    /**
     * Calculates the angle between two lines.
     *
     * @param line1 the first line
     * @param line2 the second line
     * @return the angle between the two lines in radians
     */
    public static double angleBetween(Line line1, Line line2) {
        double angle1 = line1.getAngle() % (2 * Math.PI);
        double angle2 = line2.getAngle() % (2 * Math.PI);
        double angle = Math.abs(angle1 - angle2) % (2 * Math.PI);
        return Math.min(angle, 2 * Math.PI - angle);
    }
}
