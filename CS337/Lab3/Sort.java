import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        int[] array = new int[5000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }

        for (int j = 0; j < array.length - 2; j++) {
            int k = (int)(Math.random() * array.length - 1);
            int temp = array[j];
            array[j] = array[k];
            array[k] = temp;
        }

        int[] array3 = new int[5000000];
        for (int i = 0; i < array3.length; i++) {
            array3[i] = i;
        }

        for (int j = 0; j < array3.length - 2; j++) {
            int k = (int)(Math.random() * array3.length - 1);
            int temp = array3[j];
            array3[j] = array3[k];
            array3[k] = temp;
        }

        int[] array4 = new int[5000000];
        for (int i = 0; i < array4.length; i++) {
            array4[i] = i;
        }

        for (int j = 0; j < array4.length - 2; j++) {
            int k = (int)(Math.random() * array4.length - 1);
            int temp = array4[j];
            array4[j] = array4[k];
            array4[k] = temp;
        }
        
        System.out.println();
        System.out.println();
        System.out.println("Quicksorted: ");
        long stquick = System.nanoTime();
        quicksort(array, 0, array.length - 1);
        long enquick = System.nanoTime();
        System.out.println((enquick - stquick) / 1000000000.0 + " seconds");

        System.out.println();
        System.out.println();
        System.out.println("Hybrid Quicksorted:");
        long sthybrid = System.nanoTime();
        hybridQuicksort(array3, 0, array3.length - 1, 25);
        long enhybrid = System.nanoTime();
        System.out.println((enhybrid - sthybrid) / 1000000000.0 + " seconds");

        System.out.println();
        System.out.println();
        System.out.println("Java Sorted: ");
        long stjava = System.nanoTime();
        Arrays.sort(array4);
        long enjava = System.nanoTime();
        System.out.println((enjava - stjava) / 1000000000.0 + " seconds");
    }
    
    public static void quicksort(int[] A, int left, int right) {
        if (left >= right) return;
        int pivot = (int)(Math.random() * (right - left + 1) + left);
        int temp = A[left];
        A[left] = A[pivot];
        A[pivot] = temp;
        int m = left;
        for (int i = left + 1; i <= right; i++) {
            if (A[i] < A[left]) {
                temp = A[++m];
                A[m] = A[i];
                A[i] = temp;
            }
        }
        temp = A[left];
        A[left] = A[m];
        A[m] = temp;
        quicksort(A, left, m - 1);
        quicksort(A, m + 1, right);
    }

    public static void quicksortModified(int[] A, int left, int right, int cutoff) {
        if (right - left < cutoff) return;
        int pivot = (int)(Math.random() * (right - left + 1) + left);
        int temp = A[left];
        A[left] = A[pivot];
        A[pivot] = temp;
        int m = left;
        for (int i = left + 1; i <= right; i++) {
            if (A[i] < A[left]) {
                temp = A[++m];
                A[m] = A[i];
                A[i] = temp;
            }
        }
        temp = A[left];
        A[left] = A[m];
        A[m] = temp;
        quicksortModified(A, left, m - 1, cutoff);
        quicksortModified(A, m + 1, right, cutoff);
    }

    public static void insertionSort(int[] A) {
        for (int i = 2; i < A.length; i++) {
            int key = A[i];
            int j = i - 1;
            while (j > 0 && A[j] > key) {
                A[j + 1] = A[j];
                j--;
            }
            A[j + 1] = key;
        }
    }

    public static void hybridQuicksort(int[] A, int left, int right, int cutoff) {
        quicksortModified(A, left, right, cutoff);
        insertionSort(A);
    }
}