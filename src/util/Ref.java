package util;

import java.util.function.Supplier;

/**
 * A class that represents a reference to a value.
 *
 * @param <T> the type of the value
 */
public class Ref<T> implements Supplier<T> {
    private T value;

    /**
     * Constructor.
     *
     * @param value the value to reference
     */
    public Ref(T value) {
        this.value = value;
    }

    /**
     * Sets a value.
     *
     * @param value the value to set
     */
    public void set(T value) {
        this.value = value;
    }

    /**
     * Gets a result.
     *
     * @return a result
     */
    @Override
    public T get() {
        return this.value;
    }
}
