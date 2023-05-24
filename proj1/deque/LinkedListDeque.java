package deque;

public class LinkedListDeque<T> {
    private static class Node<T> {
        Node next;
        T item;
        Node prev;

        /**
         * This class represents a Node.
         * @param item the item stored in the Node
         * @param next the next Node
         * @param prev the prev Node
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

    }


    // Constructor for an empty linked list deque
    public LinkedListDeque() {
        sentinel = new Node<>(null, 18, null);
        sentinel.next = sentinel;
        size = 0;
    }

    //public T getRecursive(int index) {

    public static void main(String[] args) {
        LinkedListDeque deque = new LinkedListDeque();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
    }

}
