import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

public class Concordance {

    public Hashtable<String, Integer> readConcordance(List<String> filenames) throws IOException {
        Hashtable<String, Integer> table = new Hashtable<String, Integer>();
        GGReader myReader = new GGReader();
        for (String filename : filenames) {
            List<String> words = myReader.readFile(filename);
            for (String word : words) {
                word = word.toLowerCase();
                if (table.containsKey(word)) {
                    table.put(word, table.get(word) + 1);
                } else {
                    table.put(word, 1);
                }
            }
        }
        return table;
    }
}