package util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * A set that does not allow duplicate values (defined by equals).
 *
 * @param <E> the type of objects that are saved in the set
 */
public class ValueSet<E> implements Set<E> {
    private final ArrayList<E> list;

    /**
     * Constructor.
     */
    public ValueSet() {
        this.list = new ArrayList<>();
    }

    /**
     * Performs the given action for each element of the {@code Iterable}
     * until all elements have been processed or the action throws an
     * exception.  Actions are performed in the order of iteration, if that
     * order is specified.  Exceptions thrown by the action are relayed to the
     * caller.
     *
     * @param action The action to be performed for each element
     */
    @Override
    public void forEach(Consumer<? super E> action) {
        this.list.forEach(action);
    }

    /**
     * Returns an array containing all of the elements in this collection,
     * using the provided {@code generator} function to allocate the returned array.
     *
     * @param generator a function which produces a new array of the desired
     *                  type and the provided length
     * @return an array containing all of the elements in this collection
     */
    @Override
    public <T> T[] toArray(IntFunction<T[]> generator) {
        return this.list.toArray(generator);
    }

    /**
     * Removes all of the elements of this collection that satisfy the given
     * predicate (optional operation).  Errors or runtime exceptions thrown during
     * iteration or by the predicate are relayed to the caller.
     *
     * @param filter a predicate which returns {@code true} for elements to be
     *               removed
     * @return {@code true} if any elements were removed
     */
    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        return this.list.removeIf(filter);
    }

    /**
     * Returns a sequential {@code Stream} with this collection as its source.
     *
     * @return a sequential {@code Stream} over the elements in this collection
     */
    @Override
    public Stream<E> stream() {
        return this.list.stream();
    }

    /**
     * Returns a possibly parallel {@code Stream} with this collection as its
     * source.  It is allowable for this method to return a sequential stream.
     *
     * @return a possibly parallel {@code Stream} over the elements in this collection
     */
    @Override
    public Stream<E> parallelStream() {
        return this.list.parallelStream();
    }

    /**
     * Returns the number of elements in this set (its cardinality).  If this
     * set contains more than {@code Integer.MAX_VALUE} elements, returns
     * {@code Integer.MAX_VALUE}.
     *
     * @return the number of elements in this set (its cardinality)
     */
    @Override
    public int size() {
        return this.list.size();
    }

    /**
     * Returns {@code true} if this set contains no elements.
     *
     * @return {@code true} if this set contains no elements
     */
    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    /**
     * Returns {@code true} if this set contains the specified element.
     * More formally, returns {@code true} if and only if this set
     * contains an element {@code e} such that
     * {@code Objects.equals(o, e)}.
     *
     * @param o element whose presence in this set is to be tested
     * @return {@code true} if this set contains the specified element
     */
    @Override
    public boolean contains(Object o) {
        return this.list.contains(o);
    }

    /**
     * Returns an iterator over the elements in this set.  The elements are
     * returned in no particular order (unless this set is an instance of some
     * class that provides a guarantee).
     *
     * @return an iterator over the elements in this set
     */
    @Override
    public Iterator<E> iterator() {
        return this.list.iterator();
    }

    /**
     * Returns an array containing all of the elements in this set.
     * If this set makes any guarantees as to what order its elements
     * are returned by its iterator, this method must return the
     * elements in the same order.
     *
     * @return an array containing all the elements in this set
     */
    @Override
    public Object[] toArray() {
        return this.list.toArray();
    }

    /**
     * Returns an array containing all of the elements in this set; the
     * runtime type of the returned array is that of the specified array.
     * If the set fits in the specified array, it is returned therein.
     * Otherwise, a new array is allocated with the runtime type of the
     * specified array and the size of this set.
     *
     * @param a the array into which the elements of this set are to be
     *          stored, if it is big enough; otherwise, a new array of the same
     *          runtime type is allocated for this purpose.
     * @return an array containing all the elements in this set
     */
    @Override
    public <T> T[] toArray(T[] a) {
        return this.list.toArray(a);
    }

    /**
     * Adds the specified element to this set if it is not already present
     * (optional operation).  More formally, adds the specified element
     * {@code e} to this set if the set contains no element {@code e2}
     * such that
     * {@code Objects.equals(e, e2)}.
     * If this set already contains the element, the call leaves the set
     * unchanged and returns {@code false}.  In combination with the
     * restriction on constructors, this ensures that sets never contain
     * duplicate elements.
     *
     * @param e element to be added to this set
     * @return {@code true} if this set did not already contain the specified element
     */
    @Override
    public boolean add(E e) {
        if (!this.list.contains(e)) {
            return this.list.add(e);
        }
        return false;
    }

    /**
     * Removes the specified element from this set if it is present
     * (optional operation).  More formally, removes an element {@code e}
     * such that
     * {@code Objects.equals(o, e)}, if
     * this set contains such an element.  Returns {@code true} if this set
     * contained the element (or equivalently, if this set changed as a
     * result of the call).  (This set will not contain the element once the
     * call returns.)
     *
     * @param o object to be removed from this set, if present
     * @return {@code true} if this set contained the specified element
     */
    @Override
    public boolean remove(Object o) {
        Ref<Object> ref = new Ref<>(o);
        this.list.stream().filter(e -> e.equals(o)).findAny().ifPresent(ref::set);
        return this.list.remove(ref.get());
    }

    /**
     * Returns {@code true} if this set contains all of the elements of the
     * specified collection.  If the specified collection is also a set, this
     * method returns {@code true} if it is a <i>subset</i> of this set.
     *
     * @param c collection to be checked for containment in this set
     * @return {@code true} if this set contains all of the elements of the
     * specified collection
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        return this.list.containsAll(c);
    }

    /**
     * Adds all of the elements in the specified collection to this set if
     * they're not already present (optional operation).  If the specified
     * collection is also a set, the {@code addAll} operation effectively
     * modifies this set so that its value is the <i>union</i> of the two
     * sets.  The behavior of this operation is undefined if the specified
     * collection is modified while the operation is in progress.
     *
     * @param c collection containing elements to be added to this set
     * @return {@code true} if this set changed as a result of the call
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean changed = false;
        for (E e : c) {
            if (this.add(e)) {
                changed = true;
            }
        }
        return changed;
    }

    /**
     * Retains only the elements in this set that are contained in the
     * specified collection (optional operation).  In other words, removes
     * from this set all of its elements that are not contained in the
     * specified collection.  If the specified collection is also a set, this
     * operation effectively modifies this set so that its value is the
     * <i>intersection</i> of the two sets.
     *
     * @param c collection containing elements to be retained in this set
     * @return {@code true} if this set changed as a result of the call
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;
        for (E e : this.list) {
            if (!c.contains(e)) {
                this.remove(e);
                changed = true;
            }
        }
        return changed;
    }

    /**
     * Removes from this set all of its elements that are contained in the
     * specified collection (optional operation).  If the specified
     * collection is also a set, this operation effectively modifies this
     * set so that its value is the <i>asymmetric set difference</i> of
     * the two sets.
     *
     * @param c collection containing elements to be removed from this set
     * @return {@code true} if this set changed as a result of the call
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        for (Object e : c) {
            if (this.remove(e)) {
                changed = true;
            }
        }
        return changed;
    }

    /**
     * Removes all of the elements from this set (optional operation).
     * The set will be empty after this call returns.
     */
    @Override
    public void clear() {
        this.list.clear();
    }

    /**
     * Creates a {@code Spliterator} over the elements in this set.
     *
     * @return a {@code Spliterator} over the elements in this set
     */
    @Override
    public Spliterator<E> spliterator() {
        return this.list.spliterator();
    }
}
