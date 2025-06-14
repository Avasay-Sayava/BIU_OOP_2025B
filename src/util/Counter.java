package util;

/**
 * A simple counter class that can increase or decrease its value.
 * It is used to track scores or other numerical values in the game.
 */
public class Counter {
    private int value;

    /**
     * Constructor that initializes the counter with a value of 0.
     *
     * @param value the initial value of the counter
     */
    public Counter(int value) {
        this.value = value;
    }

    /**
     * Increases the counter by a specified number.
     *
     * @param number the number to increase the counter by
     */
    public void increase(int number) {
        this.value += number;
    }

    /**
     * Decreases the counter by a specified number.
     *
     * @param number the number to decrease the counter by
     */
    public void decrease(int number) {
        this.value -= number;
    }

    /**
     * @return the current value of the counter
     */
    public int getValue() {
        return this.value;
    }
}
