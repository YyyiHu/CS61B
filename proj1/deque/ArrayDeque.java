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
        @Override
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
        nextFirst = 7;
        nextLast = 0;
        currentFirst = 7;
        currentLast = 0;
        usage = size / items.length;
    }

    @Override
    public void addFirst(Item item) {
        if (size == items.length) {
            resize(size * 2);
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
        items[nextLast] = item;
        currentLast = nextLast;
        nextLast = (nextLast + 1) % items.length;
        size++;
    }

    @Override
    public Item removeFirst() {
        if (items == null) {
            return null;
        }
        if (items[currentFirst] == null) {
            return null;
        }
        Item returnRemoveFirst = items[currentFirst];
        items[currentFirst] = null;
        nextFirst = currentFirst;
        currentFirst = (currentFirst + 1) % items.length;
        size--;
        if (items.length > 8 && usage < 0.25) {
            resize(items.length / 2);
        }
        return returnRemoveFirst;
    }

    @Override
    public Item removeLast() {
        if (items == null) {
            return null;
        }
        if (items[currentLast] == null) {
            return null;
        }
        Item returnRemoveLast = items[currentLast];
        items[currentLast] = null;
        nextLast = currentLast;
        currentLast = (currentLast - 1 + items.length) % items.length;
        size--;
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
        ArrayDeque<Integer> test = new ArrayDeque<>();
        test.addLast(1);
        test.addLast(2);
        test.printDeque();
        ArrayDeque<Integer> test1 = new ArrayDeque<>();
        test1.addLast(1);
        test1.addLast(2);
        System.out.println(test.equals(test1));
        for (int i = 0; i < 11; i++) {
            test.addFirst(i);
        }
        test.printDeque();
    }
}
