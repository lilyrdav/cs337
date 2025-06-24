import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class KMeansClustering {
    public static float[][] cluster(float[][] data, int K, float closeEnough) {
        int M = data.length;
        int N = data[0].length;
        float[][] CM = new float[K][N];
        float[][] OLD_CM = new float[K][N];
    
        // Initialize OLD_CM to all 0
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < N; j++) {
                OLD_CM[i][j] = 0;
            }
        }
    
        /**
        // Initialize CM to first two items in data
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < N; j++) {
                CM[i][j] = data[i][j];
            }
        }
        */
        
        // Initialize CM to K randomly chosen elements from data
        Random rand = new Random();
        for (int i = 0; i < K; i++) {
            int index = rand.nextInt(M);
            for (int j = 0; j < N; j++) {
                CM[i][j] = data[index][j];
            }
        }
        
        int numIterations = 0; 
        while (true) {
            numIterations++;
            if (clusDistance(CM, OLD_CM) < closeEnough) {
                break;
            }
    
            float[][][] groups = new float[K][][];
    
            for (int i = 0; i < K; i++) {
                groups[i] = new float[0][N];
            }
    
            for (int i = 0; i < M; i++) {
                // Find the citem in CM to which item is closest
                int closest = -1;
                float minDistance = Float.MAX_VALUE;
    
                for (int j = 0; j < K; j++) {
                    float distance = InfiniteDistance(data[i], CM[j]);
                    if (distance < minDistance) {
                        closest = j;
                        minDistance = distance;
                    }
                }
    
                // Add item to group corresponding to closest citem
                float[][] newGroup = new float[groups[closest].length + 1][N];
                for (int j = 0; j < groups[closest].length; j++) {
                    newGroup[j] = groups[closest][j];
                }
                newGroup[newGroup.length - 1] = data[i];
                groups[closest] = newGroup;
            }
    
            // Copy CM into OLD_CM
            for (int i = 0; i < K; i++) {
                for (int j = 0; j < N; j++) {
                    OLD_CM[i][j] = CM[i][j];
                }
            }
    
            for (int i = 0; i < K; i++) {
                // CM[j] = centroid of group[j] (the average of the group)
                for (int j = 0; j < N; j++) {
                    float sum = 0;
                    for (int k = 0; k < groups[i].length; k++) {
                        sum += groups[i][k][j];
                    }
                    CM[i][j] = sum / groups[i].length;
                }
            }
        }

        System.out.println("Number of iterations: " + numIterations);
    
        return CM;
    }
    
    public static float clusDistance(float[][] cOne, float[][] cTwo) {
        float d = 0;

        for (int i = 0; i < cOne.length; i++) {
            d += InfiniteDistance(cOne[i], cTwo[i]);
        }

        return d;
    }

    public static float ManhattanDistance(float[] item1, float[] item2) {
        float distance = 0;
        for (int i = 0; i < item1.length; i++) {
            distance += Math.abs(item1[i] - item2[i]);
        }
        return distance;
    }

    public static float EuclideanDistance(float[] item1, float[] item2) {
        float sum = 0;
        for (int i = 0; i < item1.length; i++) {
            float diff = item1[i] - item2[i];
            sum += diff * diff;
        }
        return (float) Math.sqrt(sum);
    }

    public static float InfiniteDistance(float[] item1, float[] item2) {
        float maxDiff = 0;
        for (int i = 0; i < item1.length; i++) {
            float diff = Math.abs(item1[i] - item2[i]);
            if (diff > maxDiff) {
                maxDiff = diff;
            }
        }
        return maxDiff;
    }

    public static float[][] readCSV(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line = "";
        int row = 0, col = 0;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            row++;
            col = values.length;
        }
        br.close();

        float[][] data = new float[row][col];
        br = new BufferedReader(new FileReader(filename));
        row = 0;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            for (int i = 0; i < col; i++) {
                data[row][i] = Float.parseFloat(values[i]);
            }
            row++;
        }
        br.close();

        return data;
    }

    public static void main(String[] args) {
        try {
            // Read the data from the CSV file
            float[][] data = KMeansClustering.readCSV("cluster2.csv");
    
            // Set the number of clusters and the threshold for convergence to 0.01
            int K = 6;
            float closeEnough = 0.01f;
    
            // Cluster the data
            float[][] centroids = KMeansClustering.cluster(data, K, closeEnough);
    
            // Print the centroids
            System.out.println("cluster2.csv test using Norm infinity on random starting points for K = 6:");
            for (int i = 0; i < centroids.length; i++) {
                System.out.print("  Cluster " + i + ": [");
                for (int j = 0; j < centroids[i].length; j++) {
                    System.out.print(centroids[i][j]);
                    if (j < centroids[i].length - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println("]");
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }    
}
