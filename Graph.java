/*
 * Erin Phillips
 * CSCI 310 HW2
 * Graph.java
 * 
 * This program implements topological sorting using DFS 
 * References were made to textbook for graph implementation
 * Textbook: Anany Levitin - Intro to the Design and Analysis of Algorithms
 * 
 * I certify this work is entirely my own other than textbook references.
 */
import java.util.*;

public class Graph {

    private int Vertices; // number of vertices in the graph
    private ArrayList<ArrayList<Integer>> adjList; // list of vertices and list of its neighbors

    // object that represents a graph and its adjacent vertices
    public Graph(int v) {
        Vertices = v;
        adjList = new ArrayList<ArrayList<Integer>>(v);
        for(int i = 0; i < v; i++) {
            adjList.add(new ArrayList<Integer>()); // list of vertices
        }
    }

    public void addEdge(int root, int dest) {
        adjList.get(root).add(dest); // add edges from one vertex to another
    }

    // topological sorting algorithm
    public void topSortUtil(int node, boolean visited[], Deque<Integer> stack) {
        visited[node] = true; // checking the node off as visited
        for(Integer i: adjList.get(node)) {
            if(!visited[i]) {
                topSortUtil(i, visited, stack);
            }
        }
        stack.push(node);
    }

    public void topSort() {
        Deque<Integer> stack = new ArrayDeque<Integer>();  // deque to implement a stack for storing visited vertices     
        boolean visited[] = new boolean[Vertices]; // list that indicates if a vertex is visited
        // mark all vertices as non visited
        for(int i = 0; i < Vertices; i++) {
            visited[i] = false;
        }
        // iterate through all vertices
        for(int i = 0; i < Vertices; i++) {
            if(visited[i] == false) { // if the vertex has not been visited
                topSortUtil(i, visited, stack);
            }
        }
        // while the stack is not empty
        while(!stack.isEmpty()) {
            // pop stack and print (this is the topological sorted order)
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }

    // method for printing the graph in an adjacency list representation
    // only prints vertices that have neighbors
    public void printGraph() {
        for(int i = 0; i < Vertices; i++) {
            if(adjList.get(i).size() > 0) {
                System.out.print("Vertex " + i + " is connected to: ");
                for(int j = 0; j < adjList.get(i).size(); j++) {
                    System.out.print(adjList.get(i).get(j) + " ");
                }
                System.out.println();
            }
        }
    }

    /*
     * Driver code for implementing and testing the topological sort algorithm
     */
    public static void main(String[] args) {
        // Test using the problem 1a on page 142 of the textbook.
        Graph g1 = new Graph(7);
        g1.addEdge(0,1);
        g1.addEdge(0,2);
        g1.addEdge(1,4);
        g1.addEdge(1,6);
        g1.addEdge(2,5);
        g1.addEdge(3,0);
        g1.addEdge(3,1);
        g1.addEdge(3,2);
        g1.addEdge(3,5);
        g1.addEdge(3,6);
        g1.addEdge(6,4);
        g1.addEdge(6,5);

        System.out.println("----------------Textbook page 142. Problem 1a:----------------");
        g1.printGraph();
        System.out.println("The topological sort of this graph using DFS starting at vertex 0:");
        g1.topSort();

        //Test using the graph in figure 4.7 on page 140 of textbook. 
        Graph g2 = new Graph(5);
        g2.addEdge(0,2);
        g2.addEdge(1,2);
        g2.addEdge(2,3);
        g2.addEdge(2,4);
        g2.addEdge(3,4);

        System.out.println("----------------Textbook page 140. Figure 4.7:----------------");
        g2.printGraph();
        System.out.println("The topological sort of this graph using DFS starting at vertex 0:");
        g2.topSort();

        // User can enter own graph to test. 
        Scanner sc = new Scanner(System.in);
        System.out.println("\nWould  you like to enter a new graph to test? Enter [y]/[n]");
        String userAnswer = sc.nextLine();
        while(userAnswer.equals("y")) {
            int v, e, to, from;
            System.out.println("Enter the number of vertices: ");
            v = sc.nextInt();
            System.out.println("Enter the number of edges: ");
            e = sc.nextInt();
        
            Graph newGraph = new Graph(v);
            System.out.println("Enter the edges: <root> <destination> \nNOTE: Numbering of the vertices starts at 0!");
            int i = 0;
            while(i < e) {
                to = sc.nextInt();
                from = sc.nextInt();
                newGraph.addEdge(to, from);
                i++;
            }

            System.out.println("---------------- Adjacency Lists of Your Graph ----------------");
            newGraph.printGraph();
            System.out.println("The topological sort of this graph using DFS starting at vertex 0:");
            newGraph.topSort();

            System.out.println("Would you like to enter a new graph to test? Enter[y]/[n]");
            userAnswer = sc.next();
        }

        if(userAnswer.equals("n")) {
            System.out.println("OK, exiting program");
        }
        sc.close();
    }
}