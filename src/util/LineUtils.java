package src.util;

import src.geometry.Line;

/**
 * The utils for the Line class.
 */
public class LineUtils {
    /**
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
