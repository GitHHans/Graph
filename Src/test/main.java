package test;

import java.util.Objects;
import java.util.Scanner;


public class main {
    public static void main (String[]args){
        //use copy path from repository root
        String filepath = "Src/data/gData.csv";
        Scanner kbd = new Scanner(System.in);
        int src = 0;
        int choice;
        read rw = new read();
        Graph graph = null;
        String[] letters = new String[999];

        do {
            menu();
            choice = enterChoice(1,5);
            switch (choice) {
                case 1 -> {
                    try {
                        letters = rw.readRow(0,filepath,",");
                        String [] column2  = rw.readRow(1,filepath,",");
                        String [] column3  = rw.readRow(2,filepath,",");
                        String [] column4  = rw.readRow(3,filepath,",");
                        graph = new Graph(letters.length);
                        for (int i =0; i < letters.length;i++){
                            graph.addNode(new Node(letters[i]));
                        }
                        for (int i =0; i < column2.length;i++){
                            graph.addEdge(Integer.parseInt(column2[i]), Integer.parseInt(column3[i]),Integer.parseInt(column4[i]));
                        }
                        System.out.println("");
                        System.out.println("The .csv file gData.csv has been loaded");
                        System.out.println("=====================");
                        graph.printMatrix();
                        System.out.println("=====================");
                        System.out.println("");
                    } catch (Exception e) {
                        System.out.println("The file cannot be read or found please try again !");
                    }
                }
                case 2 -> {
                    if (graph == null){
                        System.out.println("The graph has not been loaded yet");
                    }else {
                        do {
                            System.out.println("");
                            System.out.println("The nodes available are are:");
                            for (int i =0; i < letters.length; i++){
                                System.out.print(letters[i] + " ");
                            }
                            System.out.println(" ");
                            System.out.println("Input the starting node: ");
                            String data = kbd.nextLine();
                            int pos = returnPosition(data,letters);
                            src = pos;
                        }while (src <= -1);
                        graph.depthFirstSearch(src);
                    }
                    enterKeyToContinue();
                }
                case 3 -> {
                    if (graph == null){
                        System.out.println("The graph has not been loaded yet");
                    }else {
                        do {
                            System.out.println("");
                            System.out.println("The nodes available are are:");
                            for (int i =0; i < letters.length; i++){
                                System.out.print(letters[i] + " ");
                            }
                            System.out.println(" ");
                            System.out.println("Input the starting node: ");
                            String data = kbd.nextLine();
                            int pos = returnPosition(data,letters);
                            src = pos;
                        }while (src <= -1);
                        graph.breadthFirstSearch(src);
                    }
                    enterKeyToContinue();
                }
                case 4 -> {
                    if (graph == null){
                        System.out.println("The graph has not been loaded yet");
                    }else {
                        do {
                            System.out.println("");
                            System.out.println("The nodes available are are:");
                            for (int i =0; i < letters.length; i++){
                                System.out.print(letters[i] + " ");
                            }
                            System.out.println(" ");
                            System.out.println("Input the starting node: ");
                            String data = kbd.nextLine();
                            int pos = returnPosition(data,letters);
                            src = pos;
                        }while (src <= -1);
                        graph.dijkstraAlgorithm(src);
                    }
                    enterKeyToContinue();
                }
                case 5 -> {
                    System.exit(0);
                }

            }
        }while (choice <=5 );

    }

    /**
     * This method shows all the options of the program
     */
    public static void menu() {
        System.out.println("1.Load file containing the graph's data and input the source node");
        System.out.println("2.Apply Depth First Traversal to the graph");
        System.out.println("3.Apply Breadth First Traversal to the graph");
        System.out.println("4.Showcase shortest path in the graph");
        System.out.println("5.Exit");

    }

    /**
     * Accepts and returns a valid choice
     * @param min minimum choice
     * @param max maximum choice
     * @return choice
     */
    public static int enterChoice(int min, int max) {
        Scanner kbd = new Scanner(System.in);
        int temp = 0;
        do {
            try {
                System.out.print("> ");
                temp = Integer.parseInt(kbd.nextLine());
                if (temp < min || temp > max) {
                    System.out.println("Invalid choice. Please ensure that you enter a number from " +
                            min + " to " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, input another number.");
            }
        } while (temp < min || temp > max);
        return (temp);
    }

    /**
     * This method is only used to accept anyform of input from the user
     */
    public static void enterKeyToContinue() {
        System.out.print("Press Enter key to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method searches through all the data(String) of every node in the graph
     * and returns the position the node you are looking for
     * (Position is based on which order the node you are looking for was added)
     * @param src String of the node you are looking for
     * @param data the data(String) of every node in the graph
     * @return returns the position of the vertex
     */
    public static  int returnPosition(String src,String[] data){
        int pos = -1;
        for (int i =0; i < data.length; i++){
            if (Objects.equals(src,data[i])){
                pos = i;
            }
        }
        if (pos <= -1){
            System.out.println("The starting vertex is not valid!");
        }
        return pos;
    }
}
