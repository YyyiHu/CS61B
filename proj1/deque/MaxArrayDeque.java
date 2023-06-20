package deque;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> arrayComparator;
    private static class ArrayComparator<T> implements Comparator<T> {
        /**
         * @param a the first object to be compared.
         * @param b the second object to be compared.
         * @return positive value if a is larger than b, 0 if a equals b, negative value if a
         * is less than b.
         */
        @Override
        public int compare(T a, T b) {
            if (a == null && b == null) {
                return 0;
            } else if (a == null) {
                return -1;
            } else if (b == null) {
                return 1;
            } else return (int) a - (int) b;
        }

    }

        /**
         * Creates a MaxArrayDeque with the given Comparator.
         *
         * @param c the given Comparator.
         */
        public MaxArrayDeque(Comparator<T> c) {
            arrayComparator = c;
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
            T curMax = null;
            T prevMax = null;
            T max = null;
            while (seer.hasNext()) {
                prevMax = curMax;
                curMax = seer.next();
                if (arrayComparator.compare(curMax, prevMax) > 0) {
                    max = curMax;
                }
            }
            return max;
        }

    public T max(Comparator<T> c) {
        if (this.isEmpty()) {
            return null;
        }
        Iterator<T> seer = iterator();
        T curMax = null;
        T prevMax = null;
        T max = null;
        while (seer.hasNext()) {
            prevMax = curMax;
            curMax = seer.next();
            if (c.compare(curMax, prevMax) > 0) {
                max = curMax;
            }
        }
        return max;
    }
    public static void main(String[] args) {
        Comparator<Integer> com = new ArrayComparator();
        MaxArrayDeque<Integer> test = new MaxArrayDeque<>(com);
        for (int i = 0; i < 9; i++) {
            test.addLast(10);
        }
        test.addFirst(100);
        for (int x = 110; x < 145; x++) {
            test.addFirst(x);
        }
        System.out.println(test.max());
    }
}
