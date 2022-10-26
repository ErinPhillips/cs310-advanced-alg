// BinaryHeap class - version 2
//
// CONSTRUCTION: with optional capacity (that defaults to 100)
//               or an array containing initial items
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// Comparable deleteMin( )--> Return and remove the smallest item
// Comparable findMin( )  --> Return smallest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

import java.util.Random;

/**
 * Implements a binary heap.
 * Note that all "matching" is based on the compareTo method.
 *
 * @author Mark Allen Weiss
 * @author McCauley - rewrote loops in PercolateDown and insert
 * @author Erin Phillips - modified main() to compare the efficiency of creating
 * a heap through calls to method insert, or through the method buildHeap.
 * Multiple tests ran with insertions in sorted, reverse-sorted, and random orders.
 */
public class BinaryHeap<AnyType extends Comparable<? super AnyType>> {

    private static final int DEFAULT_CAPACITY = 100;

    // instance variables for Binary Heap
    private int currentSize;      // Number of elements in heap
    private AnyType[] array; // The heap array


    /**
     * Construct the binary heap.
     */
    public BinaryHeap() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Construct the binary heap.
     *
     * @param capacity the capacity of the binary heap.
     */
    public BinaryHeap(int capacity) {
        currentSize = 0;
        array = (AnyType[]) new Comparable[capacity + 1];
    }

    /**
     * Construct the binary heap given an array of items, using BuildHeap.
     */
    public BinaryHeap(AnyType[] items) {
        currentSize = items.length;
        array = (AnyType[]) new Comparable[(currentSize + 2) * 11 / 10];

        int i = 1;
        for (AnyType item : items)
            array[i++] = item;
        buildHeap();
    }

    /**
     * Insert into the priority queue, maintaining heap order.
     * Duplicates are allowed.
     * @param x the item to insert.
     */
//    public void insert( AnyType x )
//    {
//        if( currentSize == array.length - 1 )
//            enlargeArray( array.length * 2 + 1 );

    // Percolate up
//        int hole = ++currentSize;
//        for( array[ 0 ] = x; x.compareTo( array[ hole / 2 ] ) < 0; hole /= 2 )
//            array[ hole ] = array[ hole / 2 ];
//        array[ hole ] = x;
//    }

    /**
     * Modified version converting "bad" for loop to readable "while" loop
     * Insert into the priority queue, maintaining heap order.
     * Duplicates are allowed.
     *
     * @param x the item to insert.
     */
    public void insert(AnyType x) {
        if (currentSize == array.length - 1)
            enlargeArray(array.length * 2 + 1);

        // Percolate up
        int hole = ++currentSize;
        array[0] = x;
        while (x.compareTo(array[hole / 2]) < 0) {
            array[hole] = array[hole / 2];
            hole /= 2;
        }
        array[hole] = x;
    }


    private void enlargeArray(int newSize) {
        AnyType[] old = array;
        array = (AnyType[]) new Comparable[newSize];
        for (int i = 0; i < old.length; i++)
            array[i] = old[i];
    }

    /**
     * Find the smallest item in the priority queue.
     *
     * @return the smallest item, or throw an UnderflowException if empty.
     */
    public AnyType findMin() {
//        if (isEmpty())
//            throw new UnderflowException();
        return array[1];
    }

    /**
     * Remove the smallest item from the priority queue.
     *
     * @return the smallest item, or throw an UnderflowException if empty.
     */
    public AnyType deleteMin() {
//        if (isEmpty())
//            throw new UnderflowException();

        AnyType minItem = findMin();
        array[1] = array[currentSize--];
        percolateDown(1);

        return minItem;
    }

    /**
     * Establish heap order property from an arbitrary
     * arrangement of items. Runs in linear time.
     */
    private void buildHeap() {
        for (int i = currentSize / 2; i > 0; i--)
            percolateDown(i);
    }

    /**
     * Test if the priority queue is logically empty.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return currentSize == 0;
    }

    /**
     * Make the priority queue logically empty.
     */
    public void makeEmpty() {
        currentSize = 0;
    }


    /**
     * Internal method to percolate down in the heap.
     * @param hole the index at which the percolator begins.
     */
//    private void percolateDown( int hole )
//    {
//        int child;
//        AnyType tmp = array[ hole ];

//        for( ; hole * 2 <= currentSize; hole = child )
//        {
//            child = hole * 2;
//            if( child != currentSize &&
//                    array[ child + 1 ].compareTo( array[ child ] ) < 0 )
//                child++;
//            if( array[ child ].compareTo( tmp ) < 0 )
//                array[ hole ] = array[ child ];
//            else
//                break;
//        }
//        array[ hole ] = tmp;
//    }

    /**
     * Modified version - converting "bad" for loop into readable "while" loop; still has "break"
     * Internal method to percolate down in the heap.
     *
     * @param hole the index at which the percolator begins.
     */
    private void percolateDown(int hole) {
        int child;
        AnyType tmp = array[hole];

        while (hole * 2 <= currentSize) {  // turn for loop into while loop
            child = hole * 2;
            if (child != currentSize &&
                    array[child + 1].compareTo(array[child]) < 0)
                child++;
            if (array[child].compareTo(tmp) < 0)
                array[hole] = array[child];
            else
                break;
            hole = child;
        }
        array[hole] = tmp;
    }


    // Test program
    public static void main(String[] args) {
        /*
        First tests executed on dataset of 100 integers
        Second tests executed on dataset of 1000 integers
        Can change the dataset to desired size by changing the array size declaration
         */

        // Arrays for datasets
        Integer[] ascendOrder = new Integer[1000];
        Integer[] descendOrder = new Integer[1000];
        Integer[] randomOrder = new Integer[1000];
        Random rand = new Random();

        // Building arrays
        for (int i = 0; i < ascendOrder.length; i++) {
            ascendOrder[i] = i + 1;
            descendOrder[descendOrder.length - 1 - i] = i + 1;
            randomOrder[i] = rand.nextInt(101);
        }


        // variables to store run times
        long start;
        long end;

        /*
         **************** INSERTION IN ASCENDING SORTED ORDER (1-100) ****************
         */

        // single insertion into heap
        start = System.nanoTime();
        BinaryHeap<Integer> insertHeap1 = new BinaryHeap<>();
        for (Integer integer : ascendOrder)
            insertHeap1.insert(integer);
        end = System.nanoTime();

        System.out.println("Binary Heap insertion one by one in sorted order: ");
        System.out.println("Elapsed time in nano seconds: " + (end - start));

        // insertion using array and buildHeap
        start = System.nanoTime();
        BinaryHeap<Integer> buildHeap1 = new BinaryHeap<>(ascendOrder);
        end = System.nanoTime();

        System.out.println("\nBinary Heap insertion using array in sorted order: ");
        System.out.println("Elapsed time in nano seconds: " + (end - start));

        /*
         **************** INSERTION IN DESCENDING SORTED ORDER (100 - 1) ****************
         */

        // single insertion into heap
        start = System.nanoTime();
        BinaryHeap<Integer> insertHeap2 = new BinaryHeap<>();
        for (Integer integer : descendOrder)
            insertHeap2.insert(integer);
        end = System.nanoTime();

        System.out.println("\nBinary Heap insertion one by one in descending sorted order: ");
        System.out.println("Elapsed time in nano seconds: " + (end - start));

        // insertion using array and buildHeap
        start = System.nanoTime();
        BinaryHeap<Integer> buildHeap2 = new BinaryHeap<>(descendOrder);
        end = System.nanoTime();
        System.out.println("\nBinary Heap insertion using array in descending sorted order: ");
        System.out.println("Elapsed time in nano seconds: " + (end - start));

        /*
         **************** INSERTION IN RANDOM ORDER (1-100) ****************
         */

        // single insertion into heap
        start = System.nanoTime();
        BinaryHeap<Integer> insertHeap3 = new BinaryHeap<>();
        for (Integer integer : randomOrder)
            insertHeap3.insert(integer);
        end = System.nanoTime();

        System.out.println("\nBinary Heap insertion one by one in random order: ");
        System.out.println("Elapsed time in nano seconds: " + (end - start));

        // insertion using array and buildHeap
        start = System.nanoTime();
        BinaryHeap<Integer> buildHeap3 = new BinaryHeap<>(randomOrder);
        end = System.nanoTime();

        System.out.println("\nBinary Heap insertion using array in random order: ");
        System.out.println("Elapsed time in nano seconds: " + (end - start));
    }
}