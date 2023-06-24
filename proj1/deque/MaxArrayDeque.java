package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private Comparator<T> comparator;

    /**
     * Creates a MaxArrayDeque with the given Comparator.
     *
     * @param c the given Comparator.
     */
    public MaxArrayDeque(Comparator<T> c) {
        super();
        comparator = c;
    }

    /**
     * Returns the maximum element in the deque as governed by the previously given Comparator.
     * If the MaxArrayDeque is empty, simply returns null.
     *
     * @return the maximum element in the deque.
     */
    public T max() {
        if (isEmpty()) {
            return null;
        }
        T maxItem = this.get(0);
        for (int i = 1; i < size(); i++) {
            if (comparator.compare(this.get(i), maxItem) > 0) {
                maxItem = this.get(i);
            }
        }
        return maxItem;
    }

    /**
     * Returns the maximum element in the deque as governed by the parameter Comparator c.
     * If the MaxArrayDeque is empty, simply returns null.
     *
     * @param c the Comparator to be used for finding the maximum element.
     * @return the maximum element in the deque.
     */
    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T maxItem = this.get(0);
        for (int i = 1; i < size(); i++) {
            if (c.compare(this.get(i), maxItem) > 0) {
                maxItem = this.get(i);
            }
        }
        return maxItem;
    }
}
