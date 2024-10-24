import java.util.*;

class Dijkstra {
    public double[] shortest;
    public int[] pred;
    public PriorityQueue<Node> Q;

    // Node class for use in priority queue
    class Node implements Comparable<Node> {
        int vertex;
        double priority;

        Node(int vertex, double priority) {
            this.vertex = vertex;
            this.priority = priority;
        }

        public int compareTo(Node other) {
            return Double.compare(priority, other.priority);
        }
    }

    // Constructor
    Dijkstra(WeightedGraph graph, int source) {
        int n = graph.V;
        shortest = new double[n];
        pred = new int[n];
        Arrays.fill(shortest, Integer.MAX_VALUE);
        Arrays.fill(pred, -1);
        shortest[source] = 0;

        Q = new PriorityQueue<Node>();
        for (int v = 0; v < n; v++) {
            Q.offer(new Node(v, shortest[v]));
        }

        while (!Q.isEmpty()) {
            int u = Q.poll().vertex;

            for (WeightedGraph.Edge edge : graph.adj[u]) {
                int v = edge.dest;
                double w = edge.weight;

                if (shortest[u] + w < shortest[v]) {
                    shortest[v] = shortest[u] + w;
                    pred[v] = u;

                    // Update priority of vertex v in the priority queue
                    for (Node node : Q) {
                        if (node.vertex == v) {
                            Q.remove(node);
                            node.priority = shortest[v];
                            Q.offer(node);
                            break;
                        }
                    }
                }
            }
        }
    }

    // Getter methods for shortest and pred arrays
    double[] getShortest() {
        return shortest;
    }

    int[] getPred() {
        return pred;
    }
}