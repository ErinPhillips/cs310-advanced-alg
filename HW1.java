import java.util.Arrays;

public class HW1 {
    public static int numberOfOnes(int N){
        // Recursive method that calculates and returns the number of ones in the
        // binary representation of an integer, passed as an argument
        if (N == 0) // base case
            return 0;
        if (N == 1) // base case
            return 1;
        // recursive call
        return numberOfOnes(N / 2) + numberOfOnes(N % 2);
    }

    public static int maximum(int[] a){
        // Method that compares values in an array, passed as an argument.
        // Computes and returns the maximum value.
        if (a.length == 1) // base case: array of size 1
            return a[0];
        if (a.length == 2) // base case: array of size 2
            return Math.max(a[0], a[1]);
        // call to helper method
        return maximum(a, 0);
    }
    private static int maximum(int[] a, int i) {
        // Recursive helper method for maximum method
        if (i >= a.length) return 0; // base case: end of array
        // recursive call: increases index
        return Math.max(a[i], maximum(a, i + 1));
    }
    public static int powerOfTwoA(int n){
        // computes 2^n based on formula
        // 2^n = 2(2^(n-1))
        if (n == 0) // base case
            return 1;
        if (n == 1) // base case
            return 2;
        // recursive call
        return 2 * powerOfTwoA(n - 1);
    }
    public static int powerOfTwoB(int n){
        // computes 2^n based on formula
        // 2^n = 2^(n-1) + 2^(n-1)
        if (n == 0) // base case
            return 1;
        if (n == 1) // base case
            return 2;
        // recursive call
        return powerOfTwoB(n - 1) + powerOfTwoB(n - 1);

    }

    public static void main(String[] args) {
        // TEST numberOfOnes
        int a = 0, b = 1, c = 5, d = 64;
        System.out.println("\nThe number of ones in the binary representation of ");
        System.out.println(a + " is " + numberOfOnes(a));
        System.out.println(b + " is " + numberOfOnes(b));
        System.out.println(c + " is " + numberOfOnes(c));
        System.out.println(d + " is " + numberOfOnes(d) + "\n");

        // TEST maximum
        int[] a1 = {0, 1, 2, 3, 4};
        int[] a2 = {15, 3, 17, 37, 8};
        int[] a3 = {2, 25, 60, 9, 1};
        int[] a4 = {8, 6, 4, 2, 0};
        System.out.println("The maximum value of ");
        System.out.println(Arrays.toString(a1) + " is " + maximum(a1));
        System.out.println(Arrays.toString(a2) + " is " + maximum(a2));
        System.out.println(Arrays.toString(a3) + " is " + maximum(a3));
        System.out.println(Arrays.toString(a4) + " is " + maximum(a4) + "\n");

        // TEST powerOfTwoA
        int e = 1, f = 3, g = 19, h = 5;
        System.out.println("Using the formula 2^n = 2 * 2^(n-1): \n2 to the power of ");
        System.out.println(e + " is " + powerOfTwoA(e));
        System.out.println(f + " is " + powerOfTwoA(f));
        System.out.println(g + " is " + powerOfTwoA(g));
        System.out.println(h + " is " + powerOfTwoA(h) + "\n");

        // TEST powerOfTwoB
        System.out.println("Using the formula 2^n = 2^(n-1) + 2^(n-1): \n2 to the power of ");
        System.out.println(e + " is " + powerOfTwoB(e));
        System.out.println(f + " is " + powerOfTwoB(f));
        System.out.println(g + " is " + powerOfTwoB(g));
        System.out.println(h + " is " + powerOfTwoB(h) + "\n");


}}
