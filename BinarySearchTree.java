// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

// Modified by Erin Phillips
// ******************PUBLIC OPERATIONS*********************
// int nodesWithOneChild( )   --> Return sum of nodes with one child
// void printLevelOrder( )    --> Print tree in level order
// ******************PRIVATE OPERATIONS********************
// int nodesWithOneChild( x ) --> Helper method; starts search at x
// ********************************************************
// I CERTIFY THAT THESE UPDATES ARE ENTIRELY MY OWN WORK


/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 *
 * @author Mark Allen Weiss
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {

    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType> {
        // Constructors
        BinaryNode(AnyType theElement) {
            this(theElement, null, null);
        }

        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt) {
            element = theElement;
            left = lt;
            right = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }

    /**
     * The tree root.
     */
    private BinaryNode<AnyType> root;

    /**
     * Construct the tree.
     */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     *
     * @param x the item to insert.
     */
    public void insert(AnyType x) {
        root = insert(x, root);
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     *
     * @param x the item to remove.
     */
    public void remove(AnyType x) {
        root = remove(x, root);
    }

    /**
     * Find the smallest item in the tree.
     *
     * @return smallest item or null if empty.
     */
    public AnyType findMin() {
        if (isEmpty())
            return null;
        //throw new UnderflowException();
        return findMin(root).element;
    }

    /**
     * Find the largest item in the tree.
     *
     * @return the largest item of null if empty.
     */
    public AnyType findMax() {
        if (isEmpty())
            return null;
        // throw new UnderflowException();
        return findMax(root).element;
    }

    /**
     * Find an item in the tree.
     *
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains(AnyType x) {
        return contains(x, root);
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty() {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree() {
        if (isEmpty())
            System.out.println("Empty tree");
        else
            printTree(root);
    }

    /**
     * Internal method to insert into a subtree.
     *
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return new BinaryNode<>(x, null, null);

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            t.left = insert(x, t.left);
        else if (compareResult > 0)
            t.right = insert(x, t.right);
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     *
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return null;   // Item not found; do nothing

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            t.left = remove(x, t.left);
        else if (compareResult > 0)
            t.right = remove(x, t.right);
        else if (t.left != null && t.right != null) // Two children
        {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else
            t = (t.left != null) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     *
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {
        if (t == null)
            return null;
        else if (t.left == null)
            return t;
        return findMin(t.left);
    }

    /**
     * Internal method to find the largest item in a subtree.
     *
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {
        if (t != null)
            while (t.right != null)
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     *
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return false;

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            return contains(x, t.left);
        else if (compareResult > 0)
            return contains(x, t.right);
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     *
     * @param t the node that roots the subtree.
     */
    private void printTree(BinaryNode<AnyType> t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }

    /**
     * Internal method to compute height of a subtree.
     *
     * @param t the node that roots the subtree.
     */
    private int height(BinaryNode<AnyType> t) {
        if (t == null)
            return -1;
        else
            return 1 + Math.max(height(t.left), height(t.right));
    }

    /**
     * Find nodes that have a single child
     * @return sum of single-child nodes
     */
    public int nodesWithOneChild() {
        if (isEmpty())
            return 0;
        return nodesWithOneChild(this.root);
    }

    /**
     * Internal method to find nodes with one child
     * @param t the node that roots the subtree
     * @return sum of single-child nodes
     */
    private int nodesWithOneChild(BinaryNode<AnyType> t) {
        // Checks if current node is valid
        if(t == null) {
            return 0; // node is null
        }
        // Checks if current node has only one child (exclusive or)
        else if (t.left == null ^ t.right == null) {
            // node has one child -> returns 1 + results of
            // recursive calls to left and right potential children
            return 1 + nodesWithOneChild(t.left) + nodesWithOneChild(t.right);
        }
        // node has 2 children, no incrementation
        // recursive call to left and right potential children
        return nodesWithOneChild(t.left) + nodesWithOneChild(t.right);
    }

    /**
     * Public method to print the BinarySearchTree in level order
     * Prints as a sequence, not as a structured "tree"
     */
    public void printLevelOrder(){
        // Checks if tree is empty
        if (isEmpty())
            System.out.println("Empty Tree");

        // Queue of type DoublyLinkedList -- holds BinaryNodes
        DoublyLinkedList<BinaryNode<AnyType>> nodeQueue = new DoublyLinkedList<>();
        nodeQueue.add(root); // Enqueue root

        // Loop for level order traversal
        while (!nodeQueue.isEmpty()) {
            // Temp node to store the current node travelled to
            BinaryNode<AnyType> curr = nodeQueue.getFirst();
            nodeQueue.removeFirst(); // dequeue the current node
            System.out.println(curr.element + " ");

            // Enqueue left child
            if (curr.left != null) {
                nodeQueue.addLast(curr.left);
            }
            // Enqueue right child
            if (curr.right != null) {
                nodeQueue.addLast(curr.right);
            }
        }
    }

    // Test program
    public static void main(String[] args) {
        System.out.println("BinarySearchTree.java Testing:\n");

        // Create new BinarySearchTree
        BinarySearchTree<Integer> t = new BinarySearchTree<>();
        t.printTree(); // show tree is empty
        // Insert values into tree
        t.insert(1); t.insert(3); t.insert(2); t.insert(4);
        t.insert(5); t.insert(7); t.insert(6); t.insert(8);
        // print tree
        System.out.println("After inserting 1, 3, 2, 4, 5, 7, 6, 8:");
        t.printTree();

        // testing printLevelOrder()
        // output should be 1, 3, 2, 4, 5, 7, 6, 8
        System.out.println("Test 1: printLevelOrder()");
        t.printLevelOrder();

        // testing nodesWithOneChild()
        // output should be 3
        System.out.println("Test 2: nodesWithOneChild()");
        System.out.println("Tree has " + t.nodesWithOneChild() + " node(s) with one child");

        // clear tree for new test values
        t.makeEmpty(); t.printTree(); System.out.println();
        // Insert values into tree
        t.insert(5); t.insert(4); t.insert(3); t.insert(2);
        t.insert(7); t.insert(6); t.insert(9); t.insert(8);
        // print tree
        System.out.println("After inserting 5, 4, 3, 2, 7, 6, 9, 8");
        t.printTree();

        // testing printLevelOrder()
        // output should be 5, 4, 7, 3, 6, 9, 2, 8
        System.out.println("Test 1: printLevelOrder()");
        t.printLevelOrder();

        // testing nodesWithOneChild()
        // output should be 3
        System.out.println("Test 2: nodesWithOneChild()");
        System.out.println("Tree has " + t.nodesWithOneChild() + " node(s) with one child");

        // clear tree for new test values
        t.makeEmpty(); t.printTree(); System.out.println();
        // Insert values into tree
        t.insert(1); t.insert(2); t.insert(3); t.insert(4);
        t.insert(5); t.insert(6); t.insert(7); t.insert(8);
        // print tree
        System.out.println("After inserting 1, 2, 3, 4, 5, 6, 7, 8");
        t.printTree();

        // testing printLevelOrder()
        // output should be 1, 2, 3, 4, 5, 6, 7, 8
        System.out.println("Test 1: printLevelOrder()");
        t.printLevelOrder();

        // testing nodesWithOneChild()
        // output should be 7
        System.out.println("Test 2: nodesWithOneChild()");
        System.out.println("Tree has " + t.nodesWithOneChild() + " node(s) with one child\n");

        System.out.println("End of testing.");
    }
}
