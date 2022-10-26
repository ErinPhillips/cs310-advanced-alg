/*
 * Author: Erin Phillips
 * CSCI 310 Assignment 3
 * Algorithm to select the kth smallest element from the array
 * Please refer to readme.md for more information
 * 
 * 
 * References were made to the textbook for the Lomuto Partition Algorithm.
 * As well as notes taken during the review of the assignment in class.
 * I certify this work is entirely my own other than previously stated.
 */
 

import java.util.*; // Arrays, ArrayList

public class HW3 {
    
    /*
     * Prints the given array to console
     */
     private static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));

     }

    /*
     * Prints to console the given array partition using left and right indecies. 
     * @params: AnyType[]arr - an array of elements of AnyType
     *          int left - the left-most index of the partition
     *          int right - the right-mist index of the partition
     * @returns: void
     */
     private static void printPartition(int[] arr, int left, int right) {
        System.out.printf("Partition from index %d to %d: [", left, right);
        for( int i = left; i < right; i++ ) {
            System.out.printf("%d, ", arr[i]);
        }
        System.out.printf("%d]\n", arr[right]);
    }

    /*
     * Sorts the given array by insertion sort algorithm
     * @params: int[] arr - the array to be sorted
     * @returns: void - sorts in place
     */
    private static void insertionSort(int[] arr) {
        int j;
        for( int i = 1; i < arr.length; i++ ) {
            int temp = arr[i];
            j = i - 1; 
            while( j >= 0 && (arr[j] > temp) ) {
                arr[j+1] = arr[j];
                j--;   
            }
            arr[j+1] = temp;
        }
        printArray(arr);
    }

    /*
     * Sorts the given array partition by insertion sort algorithm
     * @params: int[] arr - the original array
     *          int left - the left-most index of the partition
     *          int right - the right-mist index of the partition
     * @returns: void - sorts in place
     */
    private static void insertionSort(int[] arr, int left, int right) {
        int j;
        for( int i = left; i <= right; i++ ) {
            int temp = arr[i];
            j = i - 1;
            while ( (j >= left) && (arr[j] > temp) ) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = temp;
        }
        printPartition(arr, left, right);
    }

    /*
     * Divides the given array into |A| / 5 sequences of 5 elements plus possibly one sequence
     * with fewer than 5 elements. 
     * Each sequence is sorted using insertion sort and the median of each is stored.
     * Function then returns the median of medians. 
     * @params: int[] arr - the original array
     * @returns: int[] - array of medians
     */
    private static int[] divide(int[] arr) {
        int numSets = (arr.length - 1) / 5;
        int[] medians = new int[numSets + 1];
        int sets = 0;
        int i = 0;
        while(sets < numSets) {
            insertionSort(arr, i, i + 4);
            medians[sets] = arr[i+2];
            i = i + 5;
            sets++;
        }
        if( i <= arr.length ) {
            insertionSort(arr, i, arr.length - 1);
            int lastMed = i + ( ( (arr.length - 1) - i ) / 2 );
            medians[sets] = arr[lastMed];
        }
        return(medians);        
    }

    /*
     * Helper method to swap element of a list
     * @params: int[] arr - the array
     *          int i, int j - the indecies of elements to be swapped
     * @returns: void - swaps in place
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /*
     * Based on Lomuto's Partitioning Algorithm from the textbook. 
     * @params: int[] arr - the array to partition
     *          int m - the value of the pivot
     * @returns: the index of the median
     */
    private static int partition(int[] arr, int m) {
        // move pivot to the end of the array
        // This is crucial for Lomuto's algorithm to work properly. 
        // Although this adds to the time complexity, I wasn't sure
        // how else to implement this. 
        ArrayList<Integer> temp = new ArrayList<>(arr.length);
        for (int i : arr) { temp.add(i); }
        int indexP = temp.indexOf(m);
        swap(arr, arr.length - 1, indexP);

        int i = -1;
        for (int j = 0; j < arr.length; j++) {
            if(arr[j] == m) { swap(arr, j, arr.length - 1); }
            if (arr[j] < m) {
                i++;
                swap(arr, i, j);
            }
        }
        // place pivot in correct position
        swap(arr, i + 1, arr.length - 1);
        System.out.printf("After partition around %d: ", m);
        printArray(arr);
        return i + 1; // index of median
    }


    private static int select(int[] arr, int k) {
        int sizeS1; int sizeS3;

        if (arr.length <= 5 ) {
            insertionSort(arr);
            return arr[k];
        }
        else {
            // get median of array
            int[] M = divide(arr);
            System.out.println("Array of medians: ");
            int m = select(M, M.length/2);

            // partition array around median and extract size of S1 (# elements smaller than pivot)
            sizeS1 = partition(arr, m);

            // size of s2 (# elements larger than pivot )
            sizeS3 = arr.length - 1 - sizeS1;

            System.out.printf("Size of S1: %d. Size of S2: %d\n", sizeS1, sizeS3);
           
            if (sizeS1 == k) { // the pivot is the kth element
                return m;
            }

            else if ( sizeS1 >= k ) { // the kth element is in s1
                int[] s1 = Arrays.copyOfRange(arr, 0, sizeS1);
                System.out.println("Subarray to be searched: ");
                printArray(s1);
                return select(s1, k);
            }

            else { // the kth element is in s3
                int[] s3 = Arrays.copyOfRange(arr, sizeS1 + 1, arr.length);
                System.out.println("Subarray to be searched: ");
                printArray(s3);
                return select(s3, (k - sizeS1 - 1));
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("-------------------------------BEGIN TESTING----------------------------------");
    

        System.out.println("------------------------------TEST 1----------------------------------");
        // ----------------------TEST 1-----------------------------
        System.out.print("TEST 1 - RANDOM ARRAY OF SIZE 5: ");
        int[] a1 = {1, 10, 2, 11, 4};
        printArray(a1);

        System.out.println("[1] Finding the minimum value in the list (k = 1).");
        System.out.printf("MIN ELEMENT FOUND: %d\n\n", select(a1, 0));

        int[] a2 = {1, 10, 2, 11, 4};
        printArray(a2);
        System.out.printf("[2] Finding the maximum value in the list (k = %d).\n", a2.length - 1);
        System.out.printf("MAX ELEMENT FOUND: %d\n\n", select(a2, a2.length - 1));

        int[] a3 = {1, 10, 2, 11, 4};
        printArray(a3);
        System.out.printf("[3] Finding the median value in the list (k = %d).\n", a3.length/2);
        System.out.printf("MEDIAN ELEMENT FOUND: %d\n\n", select(a3, a3.length/2));

        System.out.println("------------------------------TEST 2----------------------------------");
        //----------------------TEST 2-----------------------------
        System.out.print("TEST 2 - RANDOM ARRAY OF SIZE 15: ");
        int[] b1 = {100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86};
        printArray(b1);

        System.out.println("[1] Finding the minimum value in the list (k = 1).");
        System.out.printf("MIN ELEMENT FOUND: %d\n\n", select(b1, 0));

        int[] b2 = {100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86};
        printArray(b2);
        System.out.printf("[2] Finding the maximum value in the list (k = %d).\n", b2.length - 1);
        System.out.printf("MAX ELEMENT FOUND: %d\n\n", select(b2, b2.length - 1));

        int[] b3 = {100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86};
        printArray(b3);
        System.out.printf("[3] Finding the median value in the list (k = %d).\n", b3.length/2); 
        System.out.printf("MEDIAN ELEMENT FOUND: %d\n\n", select(b3, b3.length/2));

        System.out.println("------------------------------TEST 3----------------------------------");
        // ----------------------TEST 3-----------------------------
        System.out.print("TEST 3 - RANDOM ARRAY OF SIZE 22: ");
        int[] c1 = {65, 18, 50, 39, 14, 32, 59, 43, 34, 24, 57, 
                    33, 11, 63, 77, 66, 51, 89, 3, 68, 73, 30};
        printArray(c1);

        System.out.println("[1] Finding the minimum value in the list (k = 1).");
        System.out.printf("MIN ELEMENT FOUND: %d\n\n", select(c1, 0));

        int[] c2 = {65, 18, 50, 39, 14, 32, 59, 43, 34, 24, 57, 
                    33, 11, 63, 77, 66, 51, 89, 3, 68, 73, 30};
        printArray(c2);
        System.out.printf("[2] Finding the maximum value in the list (k = %d).\n", c2.length - 1);
        System.out.printf("MAX ELEMENT FOUND: %d\n\n", select(c2, c2.length - 1));

        int[] c3 = {65, 18, 50, 39, 14, 32, 59, 43, 34, 24, 57, 
                    33, 11, 63, 77, 66, 51, 89, 3, 68, 73, 30};
        printArray(c3);
        System.out.printf("[3] Finding the median value in the list (k = %d).\n", c3.length/2);
        System.out.printf("MEDIAN ELEMENT FOUND: %d\n\n", select(c3, c3.length/2));

        System.out.println("-------------------------------END TESTING----------------------------------");
    }
}