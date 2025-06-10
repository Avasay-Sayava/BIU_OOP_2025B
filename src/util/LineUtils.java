package util;

import geometry.Line;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Merges lines that are close enough to each other.
     * Lines are considered mergable if they are parallel and their distance is less than a certain threshold.
     *
     * @param lines The list of lines to merge
     * @return A new list of merged lines
     */
    public static List<Line> mergeLines(List<Line> lines) {
        boolean merged = true;

        while (merged) {
            merged = false;
            List<Line> newLines = new ArrayList<>();
            boolean[] used = new boolean[lines.size()];

            for (int i = 0; i < lines.size(); i++) {
                if (used[i]) {
                    continue;
                }

                Line l1 = lines.get(i);

                for (int j = 0; j < lines.size(); j++) {
                    if (i == j || used[j]) {
                        continue;
                    }

                    Line l2 = lines.get(j);
                    if (l1.isMergable(l2)) {
                        l1 = l1.merge(l2);
                        used[j] = true;
                        merged = true;
                    }
                }

                newLines.add(l1);
                used[i] = true;
            }

            lines = newLines;
        }

        return lines;
    }
}
