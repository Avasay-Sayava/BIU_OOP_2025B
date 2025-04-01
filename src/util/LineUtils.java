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
        return PointUtils.isClockwise(first.start(), first.end(), second.start())
                != PointUtils.isClockwiseOrCollinear(first.start(), first.end(), second.end())
                || PointUtils.isClockwiseOrCollinear(first.start(), first.end(), second.start())
                != PointUtils.isClockwise(first.start(), first.end(), second.end());
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
}
