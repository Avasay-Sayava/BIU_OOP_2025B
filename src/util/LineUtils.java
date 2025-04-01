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
}
