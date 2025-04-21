package util;

/**
 * This class contains utility methods for ball physics.
 */
public class BallUtils {
    /**
     * Calculates the speed of a ball based on its radius.
     *
     * @param radius the radius of the ball
     * @return the speed of the ball
     */
    public static double getSpeed(int radius) {
        return Constants.BALL_VELOCITY_FACTOR / (radius > 50 ? 50.0 : radius);
    }
}
