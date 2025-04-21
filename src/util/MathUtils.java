package util;

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

    /**
     * @param max The maximum number
     * @return A random integer between 0 and max
     */
    public static int randomInteger(int max) {
        return randomInteger(0, max);
    }

    /**
     * @param min the minimum number
     * @param max the maximum number
     * @return A random integer between min and max
     */
    public static int randomInteger(int min, int max) {
        return (int) Math.floor(randomDouble(min, max));
    }

    /**
     * @param max the maximum number
     * @return A random float between 0 and max
     */
    public static float randomFloat(int max) {
        return randomFloat(0, max);
    }

    /**
     * @param min the minimum number
     * @param max the maximum number
     * @return A random float between min and max
     */
    public static float randomFloat(float min, float max) {
        return (float) randomDouble(min, max);
    }

    /**
     * @param max the maximum number
     * @return A random double between 0 and max
     */
    public static double randomDouble(double max) {
        return randomDouble(0, max);
    }

    /**
     * @param min the minimum number
     * @param max the maximum number
     * @return A random double between min and max
     */
    public static double randomDouble(double min, double max) {
        return min + (Math.random() * (max - min));
    }
}
