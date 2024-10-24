import java.io.*;
import java.security.*;

public class FileHasher {
    public static void main(String[] args) {
        String filename = "shattered2.pdf"; // replace with your file name
        String algorithm = "SHA-256"; // replace with the desired algorithm
        
        String hashValue = hashAFile(filename, algorithm);
        System.out.println(hashValue + "  " + filename + " (" + algorithm + ")");
    }
    
    static String hashAFile(String filename, String algorithm) {
        byte[] hashedBytes = null; // the result
        try {
            // Open the file
            FileInputStream inStream = new FileInputStream(filename);
            
            // Instantiate a digest with the algorithm
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            
            // Define input file chunk buffer (of 1024 bytes)
            byte[] buffer = new byte[1024];
            int bytesRead = -1; // counts how many bytes were read
            while ((bytesRead = inStream.read(buffer)) != -1) {
                // there are bytes in input file to process
                // supply the chunk to digest
                digest.update(buffer, 0, bytesRead);
            }
            
            // finalize computation
            hashedBytes = digest.digest();
        } catch (NoSuchAlgorithmException | IOException e) {
            // Catches both: algorithm and file I/O exceptions
            e.printStackTrace();
        }
        return bToH(hashedBytes); // Convert bytes to hex string
    }
    
    static String bToH(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
