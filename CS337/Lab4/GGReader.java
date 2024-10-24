import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
    
public class GGReader {
    public ArrayList<String> readFile(String fileName) {
        ArrayList<String> aaa = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                aaa.add(line.trim());
            }
            return aaa;
        } catch (Exception ee) {
            System.err.println(ee);
            return aaa;
        }
    }
    public static void main(String[] args) {
        GGReader myReader = new GGReader();
        ArrayList<String> words = myReader.readFile(args[0]);
        for (String w : words) {
            System.out.println(w);
        }
    }
}   