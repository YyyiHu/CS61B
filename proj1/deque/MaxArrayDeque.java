package deque;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> extends ArrayDeque<T> implements Comparator<T> {
    private Comparator<T> arrayComparator;


    /**
     * Creates a MaxArrayDeque with the given Comparator.
     *
     * @param c the given Comparator.
     */
    public MaxArrayDeque(Comparator<T> c) {
        arrayComparator = c;
    }

    @Override
    public int compare(T a, T b) {
        if (a == null && b == null) {
            return 0;
        } else if (a == null) {
            return -1;
        } else if (b == null) {
            return 1;
        }

        if (a instanceof Comparable && b instanceof Comparable) {
            Comparable<T> comparableA = (Comparable<T>) a;
            return comparableA.compareTo(b);
        }
        // Return a default value if elements are not comparable
        return 0;
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
