/**
 * In this lab represent graphs and implement some simple graph algorithms to solve a quintessential Bryn Mawr Puzzle. First the puzzle:
You need to travel, by land, from the state of Washington to Washington, DC. You are only allowed to travel through states whose names begin with any of the letters in the word W-O- M-A-N. That is, you can travel through states like, Arkansas, Minnesota, Wyoming, etc. but not through California, Texas, Utah. Is it possible? If so, what would be the path?
Graphs:. You can (and should) solve the puzzle above by modeling it as a graph search problem. (See Chapters 5 & 6 from Cormen)
Given a graph of all the states in the United States and their neighbors, you can model the problem as a graph search problem where Washington is the start state and Washington, DC the goal state. A database of states and neighbors is available in a text file:
Link: http://cs.brynmawr.edu/cs337/USStates.csv
You may read this file from the URL or download it to you computer and work with it there. If you are working on the deparment’s UNIX machines, the file is also available at /home/ gtowell/Public/337/USStates.csv
You can get this file to your computer using scp: scpYOU@MACHINE:/home/gtowell/Public/337/USStates.csv .
Here are some of the entries in the file:
Colorado, Arizona, Kansas, Nebraska, New Mexico, Oklahoma, Utah, Wyoming
Connecticut, Massachusetts, New York, Rhode Island
District of Columbia, Maryland, Virginia
Delaware, Maryland, New Jersey, Pennsylvania
Florida, Alabama, Georgia
Georgia, Alabama, Florida, North Carolina, South Carolina, Tennessee
Hawaii,
Idaho, Montana, Nevada, Oregon, Utah, Washington, Wyoming
Illinois, Indiana, Iowa, Michigan, Kentucky, Missouri, Wisconsin
Each line starts with a state; which is followed by all its neighbors. So from the above data we can see that Florida has two neighbors: Alabama and Georgia.
Task#1: Create a graph ADT (Abstract Data Type) to input and store the above graph.
 */

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
    