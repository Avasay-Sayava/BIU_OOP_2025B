package graphics.animation;

/**
 * A class that represents a timestamp.
 * It is used to measure the time elapsed since the creation of the timestamp.
 */
public class Timestamp {
    private long startTime;

    /**
     * Constructor.
     */
    public Timestamp() {
        this(System.nanoTime());
    }

    /**
     * Copy constructor.
     *
     * @param timestamp the timestamp to copy
     */
    public Timestamp(Timestamp timestamp) {
        this(timestamp.getStartTime());
    }

    /**
     * Constructor.
     *
     * @param millis the time in milliseconds
     * @param nanos  the remaining time in nanoseconds
     */
    public Timestamp(long millis, int nanos) {
        this((millis * 1_000_000) + nanos);
    }

    /**
     * Constructor.
     *
     * @param timeNanos the time in nanoseconds
     */
    public Timestamp(long timeNanos) {
        this.startTime = timeNanos;
    }

    /**
     * @return the time of the timestamp
     */
    public long getTimeNanos() {
        return System.nanoTime() - this.startTime;
    }

    /**
     * @return the time of the timestamp
     */
    public long getStartTime() {
        return this.startTime;
    }

    /**
     * @return the time of the timestamp
     */
    public long getTimeMillis() {
        return Math.round(getTimeNanos() / 1E6);
    }

    /**
     * Resets the timestamp to the current time.
     */
    public void reset() {
        this.startTime = System.nanoTime();
    }
}
