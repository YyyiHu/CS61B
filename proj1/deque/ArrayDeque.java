package deque;


import java.util.Iterator;

/**
 * This class represents a circular array.
 */
public class ArrayDeque<Item> implements Iterable<Item> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private int currentFirst;
    private int currentLast;

    private Item[] items;
    private double usage;

    private class ArrayIterator implements Iterator<Item> {
        int seerPosition;
        int seerTimes;

        public ArrayIterator() {
            seerTimes = 0;
            seerPosition = currentFirst;
        }

        @Override
        public boolean hasNext() {
            return seerTimes < size;
        }

        public Item next() {
            Item returnNext = null;
            while (items[seerPosition] == null) {
                seerPosition = (seerPosition + 1) % items.length;
            }
            returnNext = items[seerPosition];
            seerPosition = (seerPosition + 1) % items.length;
            seerTimes++;
            return returnNext;
        }
    }

    /**
     * Creates an empty array deque.
     */
    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
        nextFirst = 5;
        nextLast = 6;
        currentFirst = 5;
        currentLast = 6;
        usage = size / items.length;
    }



    public void addFirst(Item item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        currentFirst = nextFirst;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
        size++;
    }

    public void addLast(Item item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        currentLast = nextLast;
        nextLast = (nextLast + 1) % items.length;
        size++;
    }

    public void removeFirst() {
        items[currentFirst] = null;
        nextFirst = currentFirst;
        currentFirst = (currentFirst + 1) % items.length;
        size--;
        if (items.length > 8 && usage < 0.25) {
            resize(items.length / 2);
        }
    }

    public void removeLast() {
        items[currentLast] = null;
        nextLast = currentLast;
        currentLast = (currentLast - 1 + items.length) % items.length;
        size--;
        if (items.length > 8 && usage < 0.25) {
            resize(items.length / 2);
        }
    }

    public int size() {
        return size;
    }

    /**
     * Detect if an ArrayDeque is empty.
     * @return True if there is no item stored in the ArrayDeque.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    public Item get(int index) {
        return items[index];
    }

    private void resize(int capacity) {
        Item[] resizeItems = (Item[]) new Object[capacity];
        Iterator<Item> seer = this.iterator();
        int i = 0;
        while (seer.hasNext()) {
            resizeItems[i] = seer.next();
            i++;
        }
        items = resizeItems;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof ArrayDeque) {
            ArrayDeque<Item> anotherArray = (ArrayDeque<Item>) o;
            if (anotherArray.items.length == this.items.length) {
                int comapreNoNull = currentFirst;
                if (items[currentFirst] == null && anotherArray.items[currentFirst] == null) {
                    comapreNoNull = (comapreNoNull + 1 ) % items.length;
                }
                for (int i = 0; i < size; i++) {
                    if (!items[comapreNoNull].equals(anotherArray.items[comapreNoNull])) {
                        return false;
                    }
                    comapreNoNull = (comapreNoNull + 1 ) % items.length;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    public void printDeque() {
        int printPosition = currentFirst;
         while (items[printPosition] == null) {
             printPosition = (printPosition + 1) % items.length;
         }
         for (int printTimes = 0; printTimes < size - 1; printTimes++) {
             System.out.print(items[printPosition] + " ");
             printPosition = (printPosition + 1) % items.length;
         }
        System.out.println(items[printPosition]);
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        ArrayDeque<Integer> deque2 = new ArrayDeque<>();
        for (int i = 0; i < 8; i++) {
            deque.addFirst(i);
        }
        for (int i = 0; i < 8; i++) {
            deque2.addFirst(i);
        }
        System.out.println(deque.equals(deque2));
        deque.printDeque();
        System.out.println(deque.size);
    }

}
