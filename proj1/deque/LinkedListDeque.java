package deque;

/**
 * This is a class representing a double-ended linkedlist.
 * @param <T>
 */
public class LinkedListDeque<T> {

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

    public void addFirst(T item) {
        Node newNode = new Node(null, item, null);
        newNode.prev = sentinel;
        newNode.next = sentinel.next;
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    public void addLast(T item) {
        Node newNode = new Node<>(null, item, null);
        sentinel.prev.next = newNode;
        newNode.prev = sentinel.prev;
        newNode.next = sentinel;
        sentinel.prev = newNode;
        size++;
    }

    // Return true id deque is empty, false otherwise
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    // Prints the items in the deque from first to last, separated by a space
    public void printDeque() {
        Node printNode = sentinel.next;
        while (printNode != sentinel) {
            System.out.print(printNode.item + " ");
            printNode = printNode.next;
        }
        System.out.println();
    }

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

    // Gets the item at the given index, where 0 is the front
    // If no such item exists, returns null
    public T get(int index) {
        if (sentinel.next == sentinel) {
            return null;
        }
        Node node = sentinel;
        for(int i = 0; i <= index; i++) {
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
     *  Creates a new empty double-ended linkedlist.
     */
    public LinkedListDeque() {
        sentinel = new Node<>(null, 18, null);
        sentinel.next = sentinel;
        size = 0;
    }



    public static void main(String[] args) {
        LinkedListDeque deque = new LinkedListDeque();
        deque.addFirst(3);
        deque.addFirst(2);
        deque.addFirst(1);
        deque.addLast(4);
        deque.addLast(5);
        deque.printDeque();
        deque.removeFirst();
        deque.removeLast();
        deque.printDeque();
        System.out.println(deque.get(1));
        System.out.println(deque.getRecursive(1));
    }

}
