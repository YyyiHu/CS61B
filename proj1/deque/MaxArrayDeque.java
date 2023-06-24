package deque;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> arrayComparator;


    /**
     * Creates a MaxArrayDeque with the given Comparator.
     *
     * @param c the given Comparator.
     */
    public MaxArrayDeque(Comparator<T> c) {
        arrayComparator = c;
    }
    private static class ArrayComparator<T> implements Comparator<T> {
        /**
         * @param a the first object to be compared.
         * @param b the second object to be compared.
         * @return positive value if a is larger than b, 0 if a equals b, negative value if a
         * is less than b.
         */

        @Override
        public int compare(T a, T b) {
            int returnValue = 0;
            if (a == null && b == null) {
                returnValue = 0;
            } else if (a == null) {
                returnValue = -1;
            } else if (b == null) {
                returnValue = 1;
            }
            return returnValue;
        }

    }



    /**
     * Find the maximum element in the ArrayDeque.
     *
     * @return the maximum element in the deque. Null if the MaxArrayDeque is empty.
     */
    public T max() {
        if (this.isEmpty()) {
            return null;
        }
        Iterator<T> seer = iterator();
        T cur = null;
        T prev;
        T max = null;
        while (seer.hasNext()) {
            prev = cur;
            cur = seer.next();
            if (arrayComparator.compare(cur, prev) > 0) {
                max = cur;
            }
        }
        return max;
    }

    public T max(Comparator<T> c) {
        if (this.isEmpty()) {
            return null;
        }
        Iterator<T> seer = iterator();
        T cur = null;
        T prev;
        T max = null;
        while (seer.hasNext()) {
            prev = cur;
            cur = seer.next();
            if (c.compare(cur, prev) > 0) {
                max = cur;
            }
        }
        return max;
    }

}
