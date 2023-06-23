package deque;


import java.util.Iterator;

/**
 * This class represents a circular array.
 * @param <Item> The type of ArrayDeque.
 */
public class ArrayDeque<Item> implements Deque<Item>, Iterable<Item> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private Item[] items;
    private double usage;
    public int currentFirst;
    public int currentLast;

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
        @Override
        public Item next() {
            Item returnNext = items[seerPosition];
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
        nextLast = 0;
        nextFirst = 0;
        currentFirst = 0;
        currentLast = 0;
    }

    @Override
    public void addFirst(Item item) {
        if (size == items.length) {
            resize(size * 2);
        }
        if (nextFirst == 0 && items[0] != null) {
            nextFirst = 7;
        }
        items[nextFirst] = item;
        currentFirst = nextFirst;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
        size++;
    }


    @Override
    public void addLast(Item item) {
        if (size == items.length) {
            resize(size * 2);
        }
        if (nextLast == 0 && items[0] != null) {
            nextLast = 7;
        }
        items[nextLast] = item;
        currentLast = nextLast;
        nextLast = (nextLast + 1) % items.length;
        size++;
    }

    @Override
    public Item removeFirst() {
        if (size == 0) {
            return null;
        }
        Item returnRemoveFirst = items[currentFirst];
        items[currentFirst] = null;
        nextFirst = currentFirst;
        currentFirst = (currentFirst + 1) % items.length;
        size--;
        usage = (double) size / (double) items.length;
        if (items.length > 8 && usage < 0.25) {
            resize(items.length / 2);
        }
        return returnRemoveFirst;
    }

    @Override
    public Item removeLast() {
        if (size == 0) {
            return null;
        }
        Item returnRemoveLast = items[currentLast];
        items[currentLast] = null;
        nextLast = currentLast;
        currentLast = (currentLast - 1 + items.length) % items.length;
        size--;
        usage = (double) size / (double) items.length;
        if (items.length > 8 && usage < 0.25) {
            resize(items.length / 2);
        }
        return returnRemoveLast;
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public Item get(int index) {
        return items[index];
    }

    private void resize(int capacity) {
        Item[] resizeItems = (Item[]) new Object[capacity];
        Iterator<Item> seer = iterator();
        int i = 0;
        while (seer.hasNext()) {
            resizeItems[i] = seer.next();
            i++;
        }
        items = resizeItems;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof ArrayDeque) {
            ArrayDeque<Item> anotherArray = (ArrayDeque<Item>) o;
            if (anotherArray.items.length == this.items.length) {
                int comapreNoNull = currentFirst;
                if (items[currentFirst] == null && anotherArray.items[currentFirst] == null) {
                    comapreNoNull = (comapreNoNull + 1) % items.length;
                }
                for (int i = 0; i < size; i++) {
                    if (!items[comapreNoNull].equals(anotherArray.items[comapreNoNull])) {
                        return false;
                    }
                    comapreNoNull = (comapreNoNull + 1) % items.length;
                }
                return true;
            }
        }
        return false;
    }


    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    @Override
    public void printDeque() {
        if (size == 0) {
            return;
        }
        int printPosition = currentFirst;
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
        ArrayDeque<Integer> test = new ArrayDeque<>();
        test.addLast(0);
        test.addFirst(4);
        test.addFirst(3);
        test.addFirst(2);
        test.addLast(1);
        test.addFirst(1);
        test.addLast(2);
        test.printDeque();
    }
}
