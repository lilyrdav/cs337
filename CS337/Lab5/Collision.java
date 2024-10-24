import java.util.Hashtable;

public class Collision {
    public void collisionPerformanceHash(Hashtable<String, Integer> table) {
        System.out.println("N,collisions,load factor");
        int N = 70000;
        int collisions = 0;
        int[] counts = new int[N];
        for (String word : table.keySet()) {
            int index = Math.abs(word.hashCode() % N);
            if (index < 0) {
                index = -index;
            }
            counts[index]++;
        }
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 1) {
                collisions += counts[i] - 1;
            }
        }
        System.out.println(N + "," + collisions + "," + (double) table.size() / N);
        while (collisions > 0) {
            N += 70000;
            collisions = 0;
            counts = new int[N];
            for (String word : table.keySet()) {
                int index = Math.abs(word.hashCode() % N);
                if (index < 0) {
                    index = -index;
                }
                counts[index]++;
            }
            for (int i = 0; i < counts.length; i++) {
                if (counts[i] > 1) {
                    collisions += counts[i] - 1;
                }
            }
            System.out.println(N + "," + collisions + "," + (double) table.size() / N);
        }
    }

    public void collisionPerformanceHorner(Hashtable<String, Integer> table) {
        System.out.println("N,collisions,load factor");
        Horner Horner = new Horner();
        int N = 70000;
        int collisions = 0;
        int[] counts = new int[N];
        for (String word : table.keySet()) {
            int index = Math.abs(Horner.horner(word) % N);
            if (index < 0) {
                index = -index;
            }
            counts[index]++;
        }
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 1) {
                collisions += counts[i] - 1;
            }
        }
        System.out.println(N + "," + collisions + "," + (double) table.size() / N);
        while (collisions > 0) {
            N += 70000;
            collisions = 0;
            counts = new int[N];
            for (String word : table.keySet()) {
                int index = Math.abs(Horner.horner(word) % N);
                if (index < 0) {
                    index = -index;
                }
                counts[index]++;
            }
            for (int i = 0; i < counts.length; i++) {
                if (counts[i] > 1) {
                    collisions += counts[i] - 1;
                }
            }
            System.out.println(N + "," + collisions + "," + (double) table.size() / N);
        }
    }
}
