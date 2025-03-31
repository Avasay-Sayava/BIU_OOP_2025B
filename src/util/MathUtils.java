package src.util;

/**
 * Utils for the Math subject.
 */
public class MathUtils {
    // in the Geometry check it was 1E-5, but in the assignment orders it was 1E-7 - so I chose randomly...
    public static final double COMPARISON_THRESHOLD = 1E-5;

    /**
     * @param a The first number
     * @param b The second number
     * @return If the numbers are equal, considering small error
     */
    public static boolean doubleEquals(double a, double b) {
        return Math.abs(a - b) <= COMPARISON_THRESHOLD;
    }

    /**
     * @param a The number to check
     * @return The sign of the number
     */
    public static int sign(double a) {
        return a == 0 ? 0 : a > 0 ? 1 : -1;
    }
}
