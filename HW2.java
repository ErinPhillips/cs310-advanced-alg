/*
 * Erin Phillips
 * CSCI 310 HW2
 * HW2.java
 * 
 * This program demonstrates two types of brute force search algorithms. 
 * 
 * I certify this work is entirely my own. 
 */
import java.util.Scanner;

public class HW2 {

        /*
        * Brute force algorithm. Body of text is scanned for each character found. 
        * On the order of n^2 --> O(n^2)
        * 
        */
    public static void bruteForce(char start, char end, String body) {
        // Initialize substring count
        int substringCount = 0; 

        // Begin looping through text body from left to right
        for (int i = 0; i < body.length() - 1; i++) {
            // Compare each letter to start char
            if (body.charAt(i) == start) {
                // If start char is found, continue searching for end char
                for (int j = i; j < body.length() - 1; j++) {
                    // end char found
                    if (body.charAt(j) == end) {
                        // sub string is found, incrememnt counter
                        substringCount++;
                    }
                }
            }
        }
        System.out.println("Algo 1 || Number of substrings found: " + substringCount);
    }
        /*
        * More efficient brute force algorithm. Body of text is only scanned once. 
        * Time complexity - On the order of N --> O(n)
        */
    public static void bruteForceBetter(char start, char end, String body) {
        int startCharCount = 0; // Initialize start char count
        int substringCount = 0; // Initialize substring count
        
        for (int i = 0; i < body.length() - 1; i++) {
            // start substring is found
            if (body.charAt(i) == start){
                startCharCount++;
            }
            // end subtstring found
            if (body.charAt(i) == end) {
                substringCount += startCharCount; 
            }
        }
        System.out.println("Algo 2 || Number of substrings found: " + substringCount);        
    }

    public static void main(String[] args) {

        //---------------------Begin USER INPUT--------------------------------
        Scanner sc = new Scanner(System.in);
        char start;
        char end;
        String textBody;
        System.out.println("Please enter a char that represent the start of the substring: ");
        start = sc.nextLine().charAt(0);
        System.out.println("Please enter a char that represent the end of the substring: ");
        end = sc.nextLine().charAt(0);
        System.out.println("Please enter the body of text you would like to search: ");
        textBody = sc.nextLine().toLowerCase();

        bruteForce(start, end, textBody);
        bruteForceBetter(start, end, textBody);

        //---------------------Begin TESTING--------------------------------
        char start1 = 'h';
        char end1 = 'd';
        String text1 = "hello world hello world";
        System.out.println("\n--------------- Begin Test 1: Average Case --------------");
        System.out.println("Start substring: " + start1 + " || End substring: " + end1);
        System.out.println("Text to be searched: " + text1);
        bruteForce(start1, end1, text1);
        bruteForceBetter(start1, end1, text1);

        char start2 = 'a';
        char end2 = 't';
        String text2 = "atatatatat";
        System.out.println("\n--------------- Begin Test 2: Worst Case --------------");
        System.out.println("Start substring: " + start2 + " || End substring: " + end2);
        System.out.println("Text to be searched: " + text2);
        bruteForce(start2, end2, text2);
        bruteForceBetter(start2, end2, text2);

        char start3 = 'f';
        char end3 = 'l';
        String text3 = "i love computer science";
        System.out.println("\n--------------- Begin Test 3: Best Case --------------");
        System.out.println("Start substring: " + start3 + " || End substring: " + end3);
        System.out.println("Text to be searched: " + text3);
        bruteForce(start3, end3, text3);
        bruteForceBetter(start3, end3, text3);

         //---------------------End TESTING--------------------------------
    
        sc.close();
        }
   
}