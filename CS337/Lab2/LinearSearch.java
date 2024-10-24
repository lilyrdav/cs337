import java.util.Random;

public class LinearSearch {
    public static void main(String[] args) {
        int[] A = new int[50000000];
        int n = A.length;
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            A[i] = rand.nextInt(50000000);
        }
        int xbest = A[0];
        long stbest = System.nanoTime();
        int answerbest = linearSearch(A, n, xbest);
        long enbest = System.nanoTime();
        System.out.println("Best: " + answerbest);
        System.out.println((enbest - stbest) / 1000000000.0 + " seconds");

        Random rand1 = new Random();
        int xaverage = rand1.nextInt(100000000);
        long staverage = System.nanoTime();
        int answeraverage = linearSearch(A, n, xaverage);
        long enaverage = System.nanoTime();
        System.out.println("Average: " + answeraverage);
        System.out.println((enaverage - staverage) / 1000000000.0 + " seconds");

        int xworst = -10;
        long stworst = System.nanoTime();
        int answerworst = linearSearch(A, n, xworst);
        long enworst = System.nanoTime();
        System.out.println("Worst: " + answerworst);
        System.out.println((enworst - stworst) / 1000000000.0 + " seconds");
    }

    public static int linearSearch(int[] A, int n, int x) {
        int answer = -1;
        for (int i = 0; i < n - 1; i++) {
            if (A[i] == x) {
                answer = i;
            }
        }
        return answer;
    }
}
