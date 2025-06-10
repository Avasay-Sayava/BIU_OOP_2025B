package util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * A map that does not allow duplicate values (defined by equals).
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public class ValueMap<K, V> implements Map<K, V> {
    private final HashMap<K, V> map;

    /**
     * Constructor.
     */
    public ValueMap() {
        this.map = new HashMap<>();
    }

    /**
     * @return the number of elements in the map
     */
    @Override
    public int size() {
        return this.map.size();
    }

    /**
     * @return true if the map is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    /**
     * @param key key whose presence in this map is to be tested
     * @return true if this map contains a mapping for the specified key
     */
    @Override
    public boolean containsKey(Object key) {
        Ref<Object> ref = new Ref<>(key);
        keySet().stream().filter(e -> e.equals(key)).findAny().ifPresent(ref::set);
        return this.map.containsKey(ref.get());
    }


    /**
     * @param value value whose presence in this map is to be tested
     * @return true if this map maps one or more keys to the specified value
     */
    @Override
    public boolean containsValue(Object value) {
        return this.map.containsValue(value);
    }

    /**
     * @param key the key whose associated value is to be returned
     * @return the value of the key if exists
     */
    @Override
    public V get(Object key) {
        Ref<Object> ref = new Ref<>(key);
        keySet().stream().filter(e -> e.equals(key)).findAny().ifPresent(ref::set);
        return this.map.get(ref.get());
    }

    /**
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with the specified key, or
     * {@code null} if there was no mapping for the key.
     */
    @Override
    public V put(K key, V value) {
        Ref<K> ref = new Ref<>(key);
        keySet().stream().filter(e -> e.equals(key)).findAny().ifPresent(ref::set);
        return this.map.put(ref.get(), value);
    }

    /**
     * Removes the mapping for a key from this map if it is present
     * (optional operation).  More formally, if this map contains a mapping
     * from key {@code k} to value {@code v} such that
     * {@code (key==null ? k==null : key.equals(k))} and
     * {@code (value==null ? v==null : value.equals(v))}, that mapping is
     * removed.  (The map can contain at most one such mapping.)
     *
     * @param key key with which the specified value is associated
     * @return the value associated with the specified key, or
     * {@code null} if there was no mapping for the key.
     */
    @Override
    public V remove(Object key) {
        Ref<Object> ref = new Ref<>(key);
        keySet().stream().filter(e -> e.equals(key)).findAny().ifPresent(ref::set);
        return this.map.remove(ref.get());
    }

    /**
     * Copies all of the mappings from the specified map to this map.
     *
     * @param m mappings to be stored in this map
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Removes all of the mappings from this map (optional operation).
     */
    @Override
    public void clear() {
        this.map.clear();
    }

    /**
     * Returns a set view of the keys contained in this map.
     *
     * @return a set of keys contained in this map
     */
    @Override
    public Set<K> keySet() {
        return this.map.keySet();
    }

    /**
     * Returns a collection view of the values contained in this map.
     *
     * @return a collection of values contained in this map
     */
    @Override
    public Collection<V> values() {
        return this.map.values();
    }

    /**
     * @return a set view of the mappings contained in this map
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
        return this.map.entrySet();
    }

    /**
     * @param key          the key whose associated value is to be returned
     * @param defaultValue the default mapping of the key
     * @return the value of the key if exists, otherwise the default value
     */
    @Override
    public V getOrDefault(Object key, V defaultValue) {
        Ref<Object> ref = new Ref<>(key);
        keySet().stream().filter(e -> e.equals(key)).findAny().ifPresent(ref::set);
        return this.map.getOrDefault(ref.get(), defaultValue);
    }

    /**
     * Performs the given action for each entry in this map until all entries
     * have been processed or the action throws an exception.  Unless
     * otherwise specified by the implementing class, actions are performed in
     * the order of entry set iteration (if an iteration order is specified.)
     *
     * @param action The action to be performed for each entry
     */
    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        this.map.forEach(action);
    }

    /**
     * Replaces each entry's value with the results of invoking the given bifunction.
     *
     * @param function the function to apply to each entry
     */
    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        this.map.replaceAll(function);
    }

    /**
     * Puts the key-value pair into the map.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with the specified key, or
     */
    @Override
    public V putIfAbsent(K key, V value) {
        Ref<K> ref = new Ref<>(key);
        keySet().stream().filter(e -> e.equals(key)).findAny().ifPresent(ref::set);
        return this.map.putIfAbsent(ref.get(), value);
    }

    /**
     * Removes the entry for the specified key with the specified value.
     *
     * @param key   key with which the specified value is associated
     * @param value value expected to be associated with the specified key
     * @return true if the operation was successful, false otherwise
     */
    @Override
    public boolean remove(Object key, Object value) {
        Ref<Object> ref = new Ref<>(key);
        keySet().stream().filter(e -> e.equals(key)).findAny().ifPresent(ref::set);
        return this.map.remove(ref.get(), value);
    }

    /**
     * Replaces the entry for the specified key only if currently
     * mapped to the specified value (optional operation).
     *
     * @param key      key with which the specified value is associated
     * @param oldValue value expected to be associated with the specified key
     * @param newValue value to be associated with the specified key
     * @return {@code true} if the value was replaced
     */
    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        Ref<K> ref = new Ref<>(key);
        keySet().stream().filter(e -> e.equals(key)).findAny().ifPresent(ref::set);
        return this.map.replace(ref.get(), oldValue, newValue);
    }

    /**
     * Replaces the entry for the specified key only if it is
     * currently mapped to some value (optional operation).
     *
     * @param key   key with which the specified value is associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with the specified key, or
     * {@code null} if there was no mapping for the key.
     * (A {@code null} return can also indicate that the map
     * previously associated {@code null} with the key,
     * if the implementation supports null values.)
     */
    @Override
    public V replace(K key, V value) {
        Ref<K> ref = new Ref<>(key);
        keySet().stream().filter(e -> e.equals(key)).findAny().ifPresent(ref::set);
        return this.map.replace(ref.get(), value);
    }

    /**
     * If the specified key is not already associated with a value (or is mapped
     * to {@code null}), attempts to compute its value using the given mapping
     * function and enters it into this map unless {@code null} (optional operation).
     *
     * @param key             key with which the specified value is to be associated
     * @param mappingFunction the mapping function to compute a value
     * @return the current (existing or computed) value associated with
     */
    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        Ref<K> ref = new Ref<>(key);
        keySet().stream().filter(e -> e.equals(key)).findAny().ifPresent(ref::set);
        return this.map.computeIfAbsent(ref.get(), mappingFunction);
    }

    /**
     * If the value for the specified key is present and non-null, attempts to
     * compute a new mapping given the key and its current mapped value
     * (optional operation).
     *
     * @param key               key with which the specified value is to be associated
     * @param remappingFunction the remapping function to compute a value
     * @return the new value associated with the specified key, or null if none
     */
    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        Ref<K> ref = new Ref<>(key);
        keySet().stream().filter(e -> e.equals(key)).findAny().ifPresent(ref::set);
        return this.map.computeIfPresent(ref.get(), remappingFunction);
    }

    /**
     * Attempts to compute a mapping for the specified key and its current
     * mapped value, or {@code null} if there is no current mapping (optional
     * operation). For example, to either create or append a {@code String}
     * msg to a value mapping:
     *
     * @param key               key with which the specified value is to be associated
     * @param remappingFunction the remapping function to compute a value
     * @return the new value associated with the specified key, or null if none
     */
    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        Ref<K> ref = new Ref<>(key);
        keySet().stream().filter(e -> e.equals(key)).findAny().ifPresent(ref::set);
        return this.map.compute(ref.get(), remappingFunction);
    }

    /**
     * If the specified key is not already associated with a value or is
     * associated with null, associates it with the given non-null value (optional
     * operation). Otherwise, replaces the associated value with the results of
     * the given remapping function, or removes if the result is {@code null}. This
     * method may be of use when combining multiple mapped values for a key.
     * @param key               key with which the resulting value is to be associated
     * @param value             the non-null value to be merged with the existing value
     *                          associated with the key or, if no existing value or a null value
     *                          is associated with the key, to be associated with the key
     * @param remappingFunction the remapping function to recompute a value if
     *                          present
     * @return the new value associated with the specified key, or null if no
     * value is associated with the key
     */
    @Override
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        Ref<K> ref = new Ref<>(key);
        keySet().stream().filter(e -> e.equals(key)).findAny().ifPresent(ref::set);
        return this.map.merge(ref.get(), value, remappingFunction);
    }
}
