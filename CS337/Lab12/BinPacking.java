import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class BinPacking {
    public static int minimumNumberOfContainers(List<Package> packages, int capacity) {
        int totalWeight = packages.stream().mapToInt(Package::getWeight).sum();
        int numBins = totalWeight / capacity;
        if (numBins * capacity == totalWeight) {
            return numBins;
        } else {
            return numBins + 1;
        }
    }

    public static int nextFit(int[] weights, int capacity) {
        int currentWeight = 0; // weight of the current package
        int totalWeight = 0;   // the amount of weight in the current bin
        int binsUsed = 1;      // the number of bins used
    
        for (int weight : weights) {
            if (totalWeight + weight > capacity) {
                binsUsed++;
                totalWeight = weight;
            } else {
                totalWeight += weight;
            }
        }
    
        return binsUsed;
    }

    public static int bestFit(int[] weights, int capacity) {
        int[] bins = new int[weights.length]; // array of bins, all initially empty
        int numBins = 0;                      // the number of bins with packages
    
        for (int weight : weights) {
            int bestBin = -1;  // the best bin to put the package in
            int binSpace = capacity; // the amount of space remaining in the best bin
            for (int i = 0; i < numBins; i++) {
                int totalWeight = bins[i] + weight;
                if (totalWeight <= capacity && capacity - totalWeight < binSpace) {
                    bestBin = i;
                    binSpace = capacity - totalWeight;
                }
            }
            if (bestBin >= 0) {
                bins[bestBin] += weight;
            } else {
                bins[numBins] = weight;
                numBins++;
            }
        }
    
        return numBins;
    }

    public static int worstFit(int[] weights, int capacity) {
        int[] bins = new int[weights.length]; // array of bins, all initially empty
        int numBins = 0;                      // the number of bins with packages
    
        for (int weight : weights) {
            int worstBin = -1; // the worst bin to put the package in
            int binSpace = 0;  // the amount of space remaining in the worst bin
            for (int i = 0; i < numBins; i++) {
                int totalWeight = bins[i] + weight;
                if (totalWeight <= capacity && capacity - totalWeight > binSpace) {
                    worstBin = i;
                    binSpace = capacity - totalWeight;
                }
            }
            if (worstBin >= 0) {
                bins[worstBin] += weight;
            } else {
                bins[numBins] = weight;
                numBins++;
            }
        }
    
        return numBins;
    }
    
    public static int nextFitAsc(int[] weights, int capacity) {
        Arrays.sort(weights);  // reorder the packages in ascending order
        int numBins = 0;       // the number of bins used
        int currentBin = 0;    // the current bin being filled
        int currentBinWeight = 0; // the weight of packages already placed in the current bin
    
        for (int weight : weights) {
            if (currentBinWeight + weight <= capacity) {
                currentBinWeight += weight;
            } else {
                numBins++;
                currentBinWeight = weight;
                currentBin++;
            }
        }
    
        if (currentBinWeight > 0) {
            numBins++;
        }
    
        return numBins;
    }
    
    public static int bestFitAsc(int[] weights, int capacity) {
        Arrays.sort(weights);
        int[] bins = new int[weights.length]; // array of bins, all initially empty
        int numBins = 0;                      // the number of bins with packages
    
        for (int weight : weights) {
            int bestBin = -1;  // the best bin to put the package in
            int binSpace = capacity; // the amount of space remaining in the best bin
            for (int i = 0; i < numBins; i++) {
                int totalWeight = bins[i] + weight;
                if (totalWeight <= capacity && capacity - totalWeight < binSpace) {
                    bestBin = i;
                    binSpace = capacity - totalWeight;
                }
            }
            if (bestBin >= 0) {
                bins[bestBin] += weight;
            } else {
                bins[numBins] = weight;
                numBins++;
            }
        }
    
        return numBins;
    }
    
    public static int worstFitAsc(int[] W, int C) {
        int[] B = new int[W.length];
        int bc = 0;
        Arrays.sort(W); // sort the packages in ascending order
        
        for (int i = 0; i < W.length; i++) {
            int wc = W[i];
            int bb = -1;  // the best bin to put the package in 
            int bSpace = 0; // the amount of space remaining in the best bin
            
            for (int j = 0; j < bc; j++) {
                int t = B[j] + wc;
                if (t <= C && C - t > bSpace) {
                    bb = j;
                    bSpace = C - t;
                }
            }
            
            if (bb >= 0) {
                B[bb] += wc;
            } else {
                B[bc] = wc;
                bc++;
            }
        }
        
        return bc;
    }

    public static int nextFitDesc(int[] W, int C) {
        int[] B = new int[W.length];
        int bc = 0;
        Arrays.sort(W); // sort the packages in descending order
        reverse(W);     // reverse the array to get descending order
        
        int tw = 0;
        for (int i = 0; i < W.length; i++) {
            int wc = W[i];
            if (tw + wc > C) {
                bc++;
                tw = wc;
            } else {
                tw += wc;
            }
            B[bc] = tw;
        }
        
        return bc + 1;
    }

    public static int bestFitDesc(int[] W, int C) {
        int[] B = new int[W.length];
        int bc = 0;
        Arrays.sort(W); // sort the packages in descending order
        reverse(W);     // reverse the array to get descending order
        
        for (int i = 0; i < W.length; i++) {
            int wc = W[i];
            int bb = -1;
            int bSpace = C;
            for (int j = 0; j < bc; j++) {
                int t = B[j] + wc;
                if (t <= C && C - t < bSpace) {
                    bb = j;
                    bSpace = C - t;
                }
            }
            if (bb >= 0) {
                B[bb] += wc;
            } else {
                B[bc++] = wc;
            }
        }
        
        return bc;
    }
    
    public static void reverse(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }

    public static int worstFitDesc(int[] weights, int capacity) {
        Arrays.sort(weights);
        int numBins = 0;
        int[] bins = new int[weights.length];
        for (int i = weights.length - 1; i >= 0; i--) {
            int currentPackage = weights[i];
            int bestBin = -1;
            int binSpace = 0;
            for (int j = 0; j < numBins; j++) {
                int space = capacity - bins[j];
                if (currentPackage <= space && space > binSpace) {
                    bestBin = j;
                    binSpace = space;
                }
            }
            if (bestBin >= 0) {
                bins[bestBin] += currentPackage;
            } else {
                bins[numBins] = currentPackage;
                numBins++;
            }
        }
        return numBins;
    }    

    public static void main(String[] args) {
        String fileName = "sample.txt";
        int[] weights = null;
        int capacity = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                if (lineNumber == 0) { // first line is bin size
                    capacity = Integer.parseInt(line);
                    weights = new int[1000]; // adjust array size as needed
                } else { // subsequent lines are package weights
                    int weight = Integer.parseInt(line);
                    weights[lineNumber - 1] = weight;
                }
                lineNumber++;
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    
        // Test NextFit
        long startTime = System.nanoTime();
        int numBins = bestFitAsc(weights, capacity);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
    
        // Calculate and print the lower bound
        int lowerBound = (int) Math.ceil(Arrays.stream(weights).sum() / (double) capacity);
        
        System.out.println("Off-line bestFit ascending algorithm on d10000000.txt:");
        //Number of Packages
        System.out.println("Number of packages: " + weights.length);
        System.out.println("Bin capacity: " + capacity);
        System.out.println("Theoretical minimum bins: " + lowerBound);
        System.out.println("Number of bins used: " + numBins);
        System.out.println("Time taken: " + duration + " nanoseconds");

        //Compute closeness to lower bound
        int closeness = Math.abs(numBins - lowerBound);
        if (numBins > lowerBound) {
            System.out.println("Closeness to lower bound: " + closeness + " over theoretical minimum");
        }
        else {
            System.out.println("Closeness to lower bound: " + closeness);
        }
    }
}