import java.util.*;
import java.io.*;

public class States {
      public static void main(String[] args) throws FileNotFoundException {
            Scanner input = new Scanner(new File("states.csv"));
            input.nextLine();
            String[] states = new String[51];
            int[] numNeighbors = new int[51];
            int max = 0;
            int maxIndex = 0;
            int count = 0;
            while (input.hasNextLine()) {
                  String line = input.nextLine();
                  String[] tokens = line.split(",");
                  states[count] = tokens[0];
                  numNeighbors[count] = tokens.length - 1;
                  if (numNeighbors[count] > max) {
                  max = numNeighbors[count];
                  maxIndex = count;
                  }
                  count++;
            }
            System.out.println(states[maxIndex] + " has the most neighbors: " + max);
            GraphADT graph = new GraphADT(count);
            graph.setStates(states);
            input = new Scanner(new File("states.csv"));
            input.nextLine();
            while (input.hasNextLine()) {
                  String line = input.nextLine();
                  String[] tokens = line.split(",");
                  int v = graph.getIndex(tokens[0]);
                  for (int i = 1; i < tokens.length; i++) {
                  int w = graph.getIndex(tokens[i]);
                  graph.addEdge(v, w);
                  }
            }
            Scanner console = new Scanner(System.in);
            while (true) {
                  System.out.print("Enter a state (type q to quit): ");
                  String state = console.nextLine();
                  if (state.equals("q")) {
                        break;
                  }
                  int v = graph.getIndex(state);
                  ArrayList<Integer> neighbors = graph.getAdjList(v);
                  for (int i = 0; i < neighbors.size(); i++) {
                        for (int j = i + 1; j < neighbors.size(); j++) {
                              if (neighbors.get(i) == neighbors.get(j)) {
                                    neighbors.remove(j);
                              }
                        }
                  }
                  System.out.print(state + " has the following neighbors: ");
                  for (int i = 0; i < neighbors.size(); i++) {
                        System.out.print(graph.getStates()[neighbors.get(i)]);
                        if (graph.getStates()[neighbors.get(i)] == null) {
                              System.out.print("Invalid state");
                        }
                        else if (neighbors.size() == 0) {
                              System.out.print(state + " has no neighbors");
                        }
                        else if (i < neighbors.size() - 1) {
                              System.out.print(", ");
                        }
                  }
                  System.out.println();
                  System.out.println();
            }
      }
}