package deque;

import java.util.Iterator;

/**
 * This is a class representing a double-ended LinkedList.
 * @param <T> The type of LinkedListDeque.
 */
public class LinkedListDeque<T> implements Deque<T>, Iterable<T>  {

    /**
     * This is a class representing a Node, which contains a previous Node, an item and a next Node.
     * @param <T> The type of the item stored in the Node.
     */
    private static class Node<T> {
        Node next;
        T item;
        Node prev;


        /**
         * Creates a new Node.
         * @param prev The previous Node in the sequence.
         * @param item The item stored in the Node.
         * @param next The next Node in the sequence.
         */
        private Node(Node prev, T item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }

    }
    private int size;
    private Node sentinel;

    @Override
    public void addFirst(T item) {
        Node newNode = new Node(null, item, null);
        newNode.prev = sentinel;
        newNode.next = sentinel.next;
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    @Override
    public void addLast(T item) {
        Node newNode = new Node<>(null, item, null);
        sentinel.prev.next = newNode;
        newNode.prev = sentinel.prev;
        newNode.next = sentinel;
        sentinel.prev = newNode;
        size++;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    public void printDeque() {
        Node printNode = sentinel.next;
        while (printNode != sentinel.prev) {
            System.out.print(printNode.item + " ");
            printNode = printNode.next;
        }
        System.out.println(printNode.item);
    }

    @Override
    // Removes and returns the item at the front of the deque
    // If no such item exists, returns null
    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        T first = (T) sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return first;
    }

    @Override
    public T removeLast() {
        if (sentinel.next == sentinel) {
            return null;
        }
        T last = (T) sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return last;
    }

    @Override
    // Gets the item at the given index, where 0 is the front
    // If no such item exists, returns null
    public T get(int index) {
        if (this.isEmpty()) {
            return null;
        }
        if (index > size - 1) {
            return null;
        }
        Node node = sentinel;
        for (int i = 0; i <= index; i++) {
            node = node.next;
        }
        return (T) node.item;
    }

    public T getRecursive(int index) {
        LinkedListDeque deque = new LinkedListDeque();
        deque.sentinel = this.sentinel.next;
        if (this.sentinel.next == this.sentinel) {
            return null;
        } else if (index == 0) {
            return (T) deque.sentinel.item;
        } else {
            return (T) deque.getRecursive(index - 1);
        }
    }


    /**
     * Creates a new empty double-ended linkedlist.
     */
    public LinkedListDeque() {
        sentinel = new Node<>(null, 18, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /**
     * Creates and returns an iterator.
     * @return A new iterator.
     */

    @Override
    public Iterator<T> iterator() {
        return new DequeIterator();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Deque) {
            Deque<T> anotherDeque = (Deque<T>) o;
            if (this.size() != anotherDeque.size()) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                T myItem = this.get(i);
                T anotherItem = anotherDeque.get(i);
                if (!myItem.equals(anotherItem)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private class DequeIterator implements Iterator<T> {
        int pos;

        @Override
        public boolean hasNext() {
            return pos < size;
        }

        @Override
        public T next() {
            T returnItem = get(pos);
            pos++;
            return returnItem;
        }

        DequeIterator() {
            pos = 0;
        }
    }
}
