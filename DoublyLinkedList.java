/**
     * LinkedList class implements a doubly-linked list.
     * This doubly-linked list implementation uses dummy first and
     * last nodes - that is these nodes do not store data - just
     * eliminate some edge conditions.
     * <p>
     * AnyType can be any Object type.
     * <p>
     * Note: This is a simplified version of the MyLinkedList class in the Weiss textbook,
     * modified by R. McCauley
     *
     * Note: Modified by Erin Phillips
     * Updates include: adder, remover, & getter for first and last position/data node.
     */

public class DoublyLinkedList<AnyType> {

    /**********************************************************************
     * This is the doubly-linked list node - implemented via an inner class, so
     * its fields are publicly available to the DoublyLinkedList methods
     */
    private static class Node<AnyType> {
        // Node constructor
        public Node(AnyType d, Node<AnyType> p, Node<AnyType> n) {
            data = d;
            prev = p;
            next = n;
        }

        public AnyType data;
        public Node<AnyType> prev;
        public Node<AnyType> next;
    }

    /**********************************************************************/

    // Doubly linked list instance variables
    private int theSize;        // keeps track of how many elements stored
    private Node<AnyType> head; // head node contains no data
    private Node<AnyType> tail; // tail node contains no data


    /**
     * Constructor for an empty LinkedList.
     */
    public DoublyLinkedList() {
        doClear();
    }

    private void clear() {
        doClear();
    }

    /**
     * Change the size of this collection to zero.
     */
    public void doClear() {
        // create a new head node
        head = new Node<>(null, null, null);
        // create a new tail node and make it reference the head node
        tail = new Node<>(null, head, null);
        // make the head node reference the tail node
        head.next = tail;

        theSize = 0; // set size to reflect empty list

    }

    /**
     * Returns the number of items in this collection.
     *
     * @return the number of items in this collection.
     */
    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Adds an item to the end of this collection.
     *
     * @param x any object.
     * @return true.
     */
    public boolean add(AnyType x) {
        add(size(), x);
        return true;
    }

    /**
     * Adds an item to this collection, at specified position.
     * Items at or after that position are slid one position higher.
     *
     * @param x   any object.
     * @param idx position to add at.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    public void add(int idx, AnyType x) {
        addBefore(getNode(idx, 0, size()), x);
    }

    /**
     * Adds an item to this collection, at specified position p.
     * Items at or after that position are slid one position higher.
     *
     * @param p Node to add before.
     * @param x any object.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    private void addBefore(Node<AnyType> p, AnyType x) {
        Node<AnyType> newNode = new Node<>(x, p.prev, p);
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
    }


    /**
     * Returns the item at position idx.
     *
     * @param idx the index to search in.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType get(int idx) {
        return getNode(idx).data;
    }

    /**
     * Changes the item at position idx.
     *
     * @param idx    the index to change.
     * @param newVal the new value.
     * @return the old value.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType set(int idx, AnyType newVal) {
        Node<AnyType> p = getNode(idx);
        AnyType oldVal = p.data;

        p.data = newVal;
        return oldVal;
    }

    /**
     * Gets the Node at position idx, which must range from 0 to size( ) - 1.
     *
     * @param idx index to search at.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size( ) - 1, inclusive.
     */
    private Node<AnyType> getNode(int idx) {
        return getNode(idx, 0, size() - 1);
    }

    /**
     * Gets the Node at position idx, which must range from lower to upper.
     *
     * @param idx   index to search at.
     * @param lower lowest valid index.
     * @param upper highest valid index.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between lower and upper, inclusive.
     */
    private Node<AnyType> getNode(int idx, int lower, int upper) {
        Node<AnyType> p;

        if (idx < lower || idx > upper)
            throw new IndexOutOfBoundsException("getNode index: " + idx + "; size: " + size());

        if (idx < size() / 2) {
            p = head.next;
            for (int i = 0; i < idx; i++)
                p = p.next;
        } else {
            p = tail;
            for (int i = size(); i > idx; i--)
                p = p.prev;
        }

        return p;
    }

    /**
     * Removes an item from this collection.
     *
     * @param idx the index of the object.
     * @return the item was removed from the collection.
     */
    public AnyType remove(int idx) {
        return remove(getNode(idx));
    }

    /**
     * Removes the object contained in Node p.
     *
     * @param p the Node containing the object.
     * @return the item was removed from the collection.
     */
    private AnyType remove(Node<AnyType> p) {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;

        return p.data;
    }

    /**
     * Returns a String representation of this collection.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        Node<AnyType> walker = head.next; // start at first data node
        while (walker != tail) {
            sb.append(walker.data).append(" ");
            walker = walker.next;
        }
//        sb.append("]\n[ ");
//         walker = walker.prev;
//         while (walker != head) {
//             sb.append(walker.data).append(" ");
//             walker = walker.prev;
//         }
//         sb.append("]");

        return new String(sb);
    }

    /**
     * Adds a new data node with el in the first position
     * @param el The node containing the object
     */
    public void addFirst(AnyType el) {
        add(0, el);
    }

    /**
     * Adds a new data node with el in the last position
     * @param el The node containing the object
     */
    public void addLast(AnyType el) {
        add(theSize, el);

    }

    /**
     * Removes the first data node
     */
    public void removeFirst() {
        remove(0);
    }

    /**
     * Removes the last data node
     */
    public void removeLast(){
        remove(theSize - 1);
    }

    /**
     * Getter for the first node
     * @return first data node
     */
    public AnyType getFirst() {
        return get(0);
    }

    /**
     * Getter for the last node
     * @return last data node
     */
    public AnyType getLast() {
        return get(theSize - 1);
    }

    // main method to test DoublyLinkedList
    public static void main(String[] args) {
        DoublyLinkedList<Integer> lst = new DoublyLinkedList<>();

        for (int i = 0; i < 10; i++)
            lst.add(i);
        System.out.println("After adding 0 through 9");
        System.out.println(lst);
        for (int i = 20; i < 30; i++)
            lst.add(0, i);
        System.out.println("After adding 20 through 29, each at position 0");
        System.out.println(lst);

        // Test addFirst
        lst.addFirst(30);
        System.out.println("After adding 30 in the first position");
        System.out.println(lst);

        // Test addLast
        lst.addLast(10);
        System.out.println("After adding 10 in the last position");
        System.out.println(lst);

        //Test getFirst and getLast
        System.out.println("The first element is: " + lst.getFirst());
        System.out.println("The last element is: " + lst.getLast());

        // Test removeFirst
        lst.removeFirst();
        System.out.println("After removing the first element");
        System.out.println(lst);

        // Test removeLast
        lst.removeLast();
        System.out.println("After removing the last element");
        System.out.println(lst);


        lst.remove(0);
        lst.remove(lst.size() - 1);
        System.out.println("After removing the first and the last elements");
        System.out.println(lst);

    }
}
