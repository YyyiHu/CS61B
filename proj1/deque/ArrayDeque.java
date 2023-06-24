package deque;

import java.util.Iterator;

/**
 * This class represents a circular array.
 * @param <T> The type of ArrayDeque.
 */
public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private final int DEFAULT_CAPACITY = 8;
    private int size;
    private int front;
    private int back;
    private T[] items;

    private class ArrayIterator implements Iterator<T> {
        int seerPosition;
        int seerTimes;

        ArrayIterator() {
            seerPosition = front;
        }

        @Override
        public boolean hasNext() {
            return seerTimes < size;
        }
        @Override
        public T next() {
            T returnNext = items[seerPosition];
            seerPosition = (seerPosition + 1) % items.length;
            seerTimes++;
            return returnNext;
        }
    }

    /**
     * Creates an empty array deque.
     */
    public ArrayDeque() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
        front = 0;
        size = 0;
        back = front + size;
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        front = (front - 1 + items.length) % items.length;
        items[front] = item;
        size++;
        back = (front + size) % items.length;
    }


    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[back] = item;
        size++;
        back = (front + size) % items.length;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (items.length > 8 && (double) size / items.length < 0.25) {
            resize(items.length / 2);
        }
        T returnRemoveFirst = items[front];
        items[front] = null;
        front = (front + 1) % items.length;
        size--;
        back = (front + size) % items.length;
        return returnRemoveFirst;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (items.length > 8 && (double) size / items.length < 0.25) {
            resize(items.length / 2);
        }
        T returnRemoveLast = null;
        if (back == 0 && items[back] == null) {
            returnRemoveLast = items[items.length - 1];
            items[items.length - 1] = null;
        } else {
            returnRemoveLast = items[back];
            items[back] = null;
        }
        size--;
        back = (front + size) % items.length;
        return returnRemoveLast;
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    // Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    public T get(int index) {
        return items[(front + index) % items.length];
    }

    private void resize(int capacity) {
        T[] resizeItems = (T[]) new Object[capacity];
        Iterator seer = iterator();
        int i = 0;
        while (seer.hasNext()) {
            resizeItems[i] = (T) seer.next();
            i++;
        }
        items = resizeItems;
        front = 0;
        back = front + size;
    }

    // o is considered equal if it is a Deque and contains the same contents in the same order.
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof LinkedListDeque) {
            LinkedListDeque<T> anotherDeque = (LinkedListDeque<T>) o;
            if (anotherDeque.size() == this.size) {
                for (int i = 0; i < anotherDeque.size(); i++) {
                    if (!anotherDeque.get(i).equals(this.get(i))) {
                        return false;
                    }
                }
                return true;
            }
        }
        if (o instanceof ArrayDeque) {
            ArrayDeque<T> anotherDeque = (ArrayDeque<T>) o;
            if (anotherDeque.size() == this.size) {
                for (int i = 0; i < anotherDeque.size(); i++) {
                    if (!anotherDeque.get(i).equals(this.get(i))) {
                        return false;
                    }
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
        int printPosition = front;
        for (int printTimes = 0; printTimes < size - 1; printTimes++) {
            System.out.print(items[printPosition] + " ");
            printPosition = (printPosition + 1) % items.length;
        }
        System.out.println(items[printPosition]);
    }

    public Iterator<T> iterator() {
        return new ArrayIterator();
    }
}
