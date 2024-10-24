import java.io.*;
import java.util.*;

public class Read {
    public static void main(String[] args) throws IOException {
        // Read input file
        Scanner sc = new Scanner(new File("tinyEWD.txt"));
        int V = sc.nextInt();
        int E = sc.nextInt();
        WeightedGraph graph = new WeightedGraph(V);

        // Add edges to graph
        for (int i = 0; i < E; i++) {
            int v = sc.nextInt();
            int w = sc.nextInt();
            double weight = sc.nextDouble();
            graph.addEdge(v, w, weight);
        }
        
        try (// Read source and destination vertices
        Scanner input = new Scanner(System.in)) {
            while (true) {
                System.out.print("Enter the source vertex (or enter -1 to quit): ");
                while (!input.hasNextInt()) {
                    System.out.println("Please enter an integer.");
                    input.next();
                }
                int source = input.nextInt();
                if (source == -1) {
                    break;
                }
                else if (source < 0 || source >= V) {
                    System.out.println("Source vertex must be between 0 and " + (V - 1) + ".");
                }
                else {
                    System.out.print("Enter the destination vertex: ");
                    while (!input.hasNextInt()) {
                        System.out.println("Please enter an integer.");
                        input.next();
                    }
                    int destination = input.nextInt();
                    if (destination == -1) {
                        break;
                    }
                    else if (destination < 0 || source >= V) {
                        System.out.println("Destination vertex must be between 0 and " + (V - 1) + ".");
                    }
                    Dijkstra dijkstra = new Dijkstra(graph, source);
                    double[] shortest = dijkstra.shortest;
                    int[] pred = dijkstra.pred;
                    if (shortest[destination] == Integer.MAX_VALUE) {
                        System.out.println("There is no path from " + source + " to " + destination + ".");
                    } else {
                        System.out.println("There is a path from " + source + " to " + destination + ".");
                        System.out.println("The shortest path has a cost " + shortest[destination] + ".");
                        System.out.println("Here it is:");
                        int current = destination;
                        while (current != source) {
                            System.out.println(pred[current] + " -> " + current);
                            current = pred[current];
                        }
                    }
                    System.out.println();
                }
            }
        }

        // Close scanner
        sc.close();
    }

}

