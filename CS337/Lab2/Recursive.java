import java.util.Random;

public class Recursive {
    public static void main(String[] args) {
        int[] A = new int[10000];
        int n = A.length;
        int index = 0;
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            A[i] = rand.nextInt(10000);
        }
        int xbest = A[0];
        long stbest = System.nanoTime();
        int answerbest = recursiveLinearSearch(A, n, index, xbest);
        long enbest = System.nanoTime();
        System.out.println("Best: " + answerbest);
        System.out.println((enbest - stbest) / 1000000000.0 + " seconds");

        Random rand1 = new Random();
        int xaverage = rand1.nextInt(100000000);
        long staverage = System.nanoTime();
        int answeraverage = recursiveLinearSearch(A, n, index, xaverage);
        long enaverage = System.nanoTime();
        System.out.println("Average: " + answeraverage);
        System.out.println((enaverage - staverage) / 1000000000.0 + " seconds");

        int xworst = -10;
        long stworst = System.nanoTime();
        int answerworst = recursiveLinearSearch(A, n, index, xworst);
        long enworst = System.nanoTime();
        System.out.println("Worst: " + answerworst);
        System.out.println((enworst - stworst) / 1000000000.0 + " seconds");
    }

    public static int recursiveLinearSearch(int[] A, int n, int i, int x) {
        if (i >= n) {
            return -1;
        } else if (i < n && A[i] == x) {
            return i;
        } else {
            return recursiveLinearSearch(A, n, i + 1, x);
        }
    }
}
