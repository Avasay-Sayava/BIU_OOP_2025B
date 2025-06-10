package collision;

/**
 * HitNotifier is an interface that defines a notifier for hit events.
 * It is used to notify when a hit occurs, allowing listeners to be added or removed.
 */
public interface HitNotifier {
    /**
     * Adds a hit listener to the notifier.
     *
     * @param hl the hit listener to add
     */
    void addHitListener(HitListener hl);

    /**
     * Removes a hit listener from the notifier.
     *
     * @param hl the hit listener to remove
     */
    void removeHitListener(HitListener hl);
}
