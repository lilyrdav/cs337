import java.util.LinkedList;
import java.util.Scanner;

public class WOMAN {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] adjList = new int[n][n];
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            adjList[a][b] = 1;
            adjList[b][a] = 1;
        }
        int q = sc.nextInt();
        for (int i = 0; i < q; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            boolean[] visitedStates = new boolean[n];
            LinkedList<Integer> path = new LinkedList<Integer>();
            findPath(a, b, adjList, visitedStates, path);
            if (path.size() == 0) {
                System.out.println("no path");
            } else {
                for (int j = 0; j < path.size(); j++) {
                    System.out.print(path.get(j) + " ");
                }
                System.out.println();
            }
        }
    }
    public static void findPath(int currentState, int destinationState, int[][] adjList, boolean[] visitedStates, LinkedList<Integer> path) {
        if (currentState == destinationState) {
            path.add(currentState);
            return;
        }
        visitedStates[currentState] = true;
        for (int i = 0; i < adjList.length; i++) {
            if (adjList[currentState][i] == 1 && !visitedStates[i]) {
                findPath(i, destinationState, adjList, visitedStates, path);
                if (path.size() > 0) {
                    path.addFirst(currentState);
                    return;
                }
            }
        }
    }
}
