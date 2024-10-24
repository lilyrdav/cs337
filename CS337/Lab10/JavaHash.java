import java.io.*;
import java.security.*;

public class JavaHash {
    public static void main(String[] args) {
        String msg = "It was the best of times, it was the worst of times.";
        String hashValue = hashString(msg, "SHA-256");
        System.out.println(hashValue + "   " + msg);
    }

    static String hashString(String message, String algorithm) {
        byte[] hashedBytes = null;
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] byteMessage = message.getBytes("UTF-8");
            hashedBytes = digest.digest(byteMessage);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return bToH(hashedBytes);
    }

    static String bToH(byte[] value) {
        StringBuilder sb = new StringBuilder(value.length * 2);
        for (byte b : value)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
