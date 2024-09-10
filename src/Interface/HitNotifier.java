// 209533041 Or Haibi
package Interface;

/**
 * The interface Interface.HitNotifier.
 */
public interface HitNotifier {
    /**
     * Add HitListener.
     * Add hl as a listener to hit events.
     * @param hl the object we want to add to the list of listeners
     */
    void addHitListener(Interface.HitListener hl);

    /**
     * Remove HitListener.
     * Remove hl from the list of listeners to hit events.
     * @param hl the object we want to remove.
     */
    void removeHitListener(Interface.HitListener hl);
}
