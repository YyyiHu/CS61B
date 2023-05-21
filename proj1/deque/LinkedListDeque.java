package deque;

public class LinkedListDeque {
    private static class IntNode<T> {
        T item;
        IntNode next;

        /**
         * This class represents an IntNode.
         * @param item the item stored in the IntNode
         * @param next the next IntNode
         */
        public IntNode(T item, IntNode next) {
            this.item = item;
            this.next = next;
        }






    }
    private int size;
    private IntNode sentinel;


    // Constructor for an empty linked list deque
    public LinkedListDeque(){

    }
}
