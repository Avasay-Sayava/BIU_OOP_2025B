package util;

/**
 * This class represents a pair of objects.
 *
 * @param <S> the first object type
 * @param <T> the second object type
 */
public class Pair<S, T> {
    private S first;
    private T second;

    /**
     * Constructor.
     *
     * @param first  the first object
     * @param second the second object
     */
    public Pair(S first, T second) {
        this.first = first;
        this.second = second;
    }

    /**
     * @return the first object
     */
    protected S getFirst() {
        return this.first;
    }

    /**
     * @return the second object
     */
    protected T getSecond() {
        return this.second;
    }

    /**
     * @param first the first object
     */
    public void setFirst(S first) {
        this.first = first;
    }

    /**
     * @param second the second object
     */
    public void setSecond(T second) {
        this.second = second;
    }
}
