import java.util.*;

@SuppressWarnings("unchecked")
public class GraphADT {
    private int numVertices;
    private int numEdges;
    private ArrayList<Integer>[] adjList;
    private String[] states;
    
    public GraphADT(int n) {
        numVertices = n;
        numEdges = 0;
        adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<Integer>();
        }
    }
    
    public void addEdge(int v, int w) {
        adjList[v].add(w);
        adjList[w].add(v);
        numEdges++;
    }
    
    public int getNumVertices() {
        return numVertices;
    }
    
    public int getNumEdges() {
        return numEdges;
    }

    public ArrayList<Integer> getAdjList(int v) {
        return adjList[v];
    }
    
    public void setStates(String[] states) {
        this.states = states;
    }
    
    public String[] getStates() {
        return states;
    }
    
    public int getIndex(String state) {
        for (int i = 0; i < states.length; i++) {
            if (states[i].equals(state)) {
                return i;
            }
        }
        return -1;
    }
}
    
