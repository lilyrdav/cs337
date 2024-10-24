import java.util.*;

class WeightedGraph {
    public int V; // number of vertices
    public LinkedList<Edge>[] adj; // adjacency list

    // Edge class
    class Edge {
        int dest; // destination vertex
        double weight; // edge weight

        Edge(int dest, double weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    // Constructor
    WeightedGraph(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    // Function to add an edge to the graph
    void addEdge(int src, int dest, double weight) {
        Edge edge = new Edge(dest, weight);
        adj[src].add(edge);
    }

    // Function to print the adjacency list representation of the graph
    void printGraph() {
        for (int i = 0; i < V; i++) {
            System.out.print(i + " -> ");
            for (Edge edge : adj[i]) {
                System.out.print("(" + edge.dest + ", " + edge.weight + ") ");
            }
            System.out.println();
        }
    }

    public double getWeight(int i, int current) {
        for (Edge edge : adj[current]) {
            if (edge.dest == i) {
                return edge.weight;
            }
        }
        return 0;
    }
}
