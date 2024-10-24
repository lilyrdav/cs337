import java.io.*;
import java.util.*;

public class LZW {
    public static void compress(File inputFile, File outputFile) throws IOException {
        Map<String, Integer> dictionary = new HashMap<String, Integer>();
        List<Integer> output = new ArrayList<Integer>();
        int dp = 256;

        // initialize the dictionary with ASCII characters
        for (int j = 0; j <= 255; j++) {
            String key = String.valueOf((char) j);
            dictionary.put(key, j);
        }

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String line;
        String s = String.valueOf((char) reader.read());
        while ((line = reader.readLine()) != null) {
            for (int i = 0; i < line.length(); i++) {
                String c = String.valueOf(line.charAt(i));
                String sc = s + c;

                if (dictionary.containsKey(sc)) {
                    s = sc;
                } else {
                    output.add(dictionary.get(s));
                    dictionary.put(sc, dp);
                    dp++;
                    s = c;
                }
            }
        }
        reader.close();

        output.add(dictionary.get(s));
        DataOutputStream writer = new DataOutputStream(new FileOutputStream(outputFile));
        for (Integer code : output) {
            writer.writeInt(code);
        }
        writer.close();

        System.out.println("Length of text (bytes): " + inputFile.length());
        System.out.println("Number of bytes needed to store compressed text: " + output.size() * 4);
        System.out.println("Number of entries in the dictionary: " + dictionary.size());
    }

    public static void main(String[] args) throws IOException {
        // compress a sample input file
        File inputSample = new File("sample1.txt");
        File outputSample = new File("sample1_compressed.bin");
        compress(inputSample, outputSample);

        // compress another sample input file
        File inputAnotherSample = new File("sample2.txt");
        File outputAnotherSample = new File("sample2_compressed.bin");
        compress(inputAnotherSample, outputAnotherSample);

        // compress the full text of Moby Dick
        File inputMobyDick = new File("mobb.txt");
        File outputMobyDick = new File("mobb_compressed.bin");
        compress(inputMobyDick, outputMobyDick);
    }
}