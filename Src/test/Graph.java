package test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    int vertexes;
    ArrayList<Node> nodes;

    //hold the number of vertexes

    int[][] matrix;

    /**
     * Constructor to declare the graphs size and the number of vertexes
     * @param v
     */
    Graph(int v) {
        //Attaches the nodes to the obj
        vertexes = v;
        nodes = new ArrayList<>();
        matrix = new int[v][v];
    }

    /**
     * This adds a node to the graph
     * @param node
     */
    public void addNode(Node node) {
        nodes.add(node);
    }

    /**
     * This method adds an edge
     * @param src the address of the initial node
     * @param dst  the node that we want to connect to
     * @param weight value of the weight of the edge
     */
    public void addEdge(int src, int dst, int weight) {
        //src = row dst = column
        matrix[src][dst] = weight;
        //comment this line for directed graph
        matrix[dst][src] = weight;
    }

    /**
     * Checks if there is a edge in between 2 nodes
     * @param src the 1st node address
     * @param dst the 2nd node address
     * @return returns true if there is false if there is none
     */
    public boolean checkEdge(int src, int dst) {
        if (matrix[src][dst] >= 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method prints out the matrix containing
     * all the address that each node contains
     */
    public void printMatrix() {
        System.out.print("  ");
        //Prints out all the chars of the nodes at the header
        System.out.print("  ");
        for (Node node : nodes) {
            System.out.print(node.data + " ");
        }
        System.out.println();

        for (int i = 0; i < matrix.length; i++) {
            //Prints the character of the node at the left side
            System.out.print(i + "." + nodes.get(i).data + " ");
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * This method will perform and print out the
     * transversal of the graph in the dfs order
     * @param src starting node
     */
    public void depthFirstSearch(int src) {
        System.out.println(" ");
        System.out.println("DEPTH FIRST SEARCH ALGORITHM ");
        System.out.println("The sequence of transversal is: ");
        System.out.println("=============================== ");
        //list to keep track if the node has been visited
        // length is 9
        boolean[] visited = new boolean[matrix.length];
        dFSRecursion(src, visited);
        System.out.print("End");
        System.out.println(" ");
        System.out.println("=============================== ");
        System.out.println(" ");
    }

    /**
     * This method is the recursion that traverses the graph
     * in the dsf fashion
     * @param src originally holds the starting node but eventually assumes every other node visited
     * @param visited holds all the nodes that been visited/unvisited
     */
    public void dFSRecursion(int src, boolean[] visited) {
        //src = the starting vertex
        //if the visited array at source has been visited
        if (visited[src] == true) {
            return;
            // So when the node has already been visited the loop down bellow
            // Will simply continue where it left off and then so on and so forth
        } else {
            //if it has not been visited
            visited[src] = true;
            System.out.print(nodes.get(src).data + " -> ");
        }
        //matrix[src][i] src = node(row) currently at and i = column
        for (int i = 0; i < matrix[src].length; i++) {
            if (matrix[src][i] >= 1) {
                dFSRecursion(i, visited);
            }
        }
    }

    /**
     * Method that traverses the graph using the
     * breadthfirstsearch algorithm and prints it out
     * @param src the address of the starting node in order of it's addition
     */
    public void breadthFirstSearch(int src) {
        System.out.println(" ");
        System.out.println("BREADTH FIRST SEARCH ALGORITHM ");
        System.out.println("The sequence of transversal is: ");
        System.out.println("=============================== ");
        //queue is fcfs
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[matrix.length];
        //add node to the queue
        queue.offer(src);
        //mark src as visited
        visited[src] = true;
        while (queue.size() != 0) {
            //get src node from head
            src = queue.poll();
            System.out.print(nodes.get(src).data + " -> ");

            //check the row
            for (int i = 0; i < matrix[src].length; i++) {
                if (matrix[src][i] >= 1 && !visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }
        System.out.print("End");
        System.out.println(" ");
        System.out.println("=============================== ");
        System.out.println(" ");
    }

    /**
     * This method returns the weight of an unvisited vertex
     * @param visited contains which vertexes we have visited
     * @param distance  contains the distances of the nodes relative to the src
     * @return returns a unvisited node
     */
    int getWeight(boolean[] visited, int[] distance) {
        int weight = -1;
        for (int i = 0; i < vertexes; i++) {
            //if the node has not been added to the visited
            //and the current distance is lesser than infinity
            if (visited[i] == false && Integer.MAX_VALUE > distance[i]) {
                weight = i;
            }
        }
        return weight;
    }

    /**
     * Finds the distances of all nodes from the src then prints out their distances
     * @param src the node that we will want to find the distance of every other node to
     */

    public void dijkstraAlgorithm(int src) {
        boolean[] visited = new boolean[vertexes];
        int[] distances = new int[vertexes];
        //we declare all the known distances as high as infinity
        for (int i = 0; i < vertexes; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        distances[src] = 0;
        //start from the source node
        for (int i = 0; i < vertexes; i++) {
            //get the weight of an adjacent vertex
            int vertexSrc = getWeight(visited, distances);
            visited[vertexSrc] = true;
            //            DISTANCES
            //            for (int x = 0; x < distances.length; x++){
            //                System.out.print(distances[x] + " ");
            //            }
            //            System.out.println(" ");
            //            NODES ALREADY TRAVERSED
            //            visited[vertexSrc] = true;
            //            for (int x = 0; x < distances.length; x++){
            //                System.out.print(visited[x] + " ");
            //            }
            //            System.out.println(" ");
            for (int column = 0; column < vertexes; column++) {
                //check of the edge between vertexSrc and column
                //This finds a non zero address at the current node
                if (matrix[vertexSrc][column] > 0) {
                    if (visited[column] == false) {
                        //check if distance needs an update or not
                        //means check total weight from source to column is less than
                        //the current distance value, if yes then update the distance
                        int newDistance = matrix[vertexSrc][column] + distances[vertexSrc];
                        if (newDistance < distances[column]){
                            distances[column] = newDistance;
                        }
                    }
                }
            }
        }
        printDistances(src, distances);
    }

    /**
     * prints out all of the distances of the nodes relative to the src
     * @param src the address of the starting node in order of it's addition
     * @param distance the distances of the nodes relative to the source
     */
    public void printDistances(int src, int[] distance) {
        System.out.println(" ");
        System.out.println("Dijkstra Algorithm");
        System.out.println("Chosen node is: " + nodes.get(src).data);
        System.out.println("=======================================");
        for (int i = 0; i < vertexes; i++) {
            System.out.println("Vertex " + nodes.get(src).data + " --> " + nodes.get(i).data + " distance: " + distance[i]);
        }
        System.out.println("=======================================");
        System.out.println(" ");
    }

}




