import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) throws IOException {
        // Read input file
        Scanner sc = new Scanner(new File("hugeEWD.txt"));
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
    
        Dijkstra dijkstra = new Dijkstra(graph, 3310);
        double[] shortest = dijkstra.shortest;
        double max = 0;
        int maxNode = 0;
        int count = 0;
        for (int i = 0; i < shortest.length; i++) {
            if (shortest[i] != Integer.MAX_VALUE) {
                count++;
                if (shortest[i] > max) {
                    max = shortest[i];
                    maxNode = i;
                }
            }
        }
        System.out.println("FROM 7: CAN GET TO " + count + " nodes");
        System.out.println("MOST EXPENSIVE to get to is node " + maxNode + " with a cost of " + max);
    }
}
