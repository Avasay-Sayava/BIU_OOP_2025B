package util;

/**
 * This class contains utility methods for array manipulation.
 */
public class ArrayUtils {
    /**
     * Converts an array of strings to an array of doubles.
     *
     * @param org  the original array of strings
     * @param dest the destination array of doubles
     */
    public static void toDouble(String[] org, double[] dest) {
        for (int i = 0; i < org.length; i++) {
            try {
                dest[i] = Double.parseDouble(org[i]);
            } catch (NumberFormatException e) {
                new IllegalArgumentException("Invalid number format: " + org[i], e).printStackTrace();
            }
        }
    }

    /**
     * Converts an array of strings to an array of integers.
     *
     * @param org  the original array of strings
     * @param dest the destination array of integers
     */
    public static void toInteger(String[] org, int[] dest) {
        for (int i = 0; i < org.length; i++) {
            try {
                dest[i] = Integer.parseInt(org[i]);
            } catch (NumberFormatException e) {
                new IllegalArgumentException("Invalid number format: " + org[i], e).printStackTrace();
            }
        }
    }

    /**
     * Shuffles an array of integers.
     *
     * @param arr the array to shuffle
     */
    public static void shuffleInt(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int j = (int) (Math.random() * arr.length);
            int tmp = arr[j];
            arr[j] = arr[i];
            arr[i] = tmp;
        }
    }
}
