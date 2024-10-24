import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Hashtable;

public class Main {
    public static void main(String[] args) throws Exception {
        List<String> filenames = new ArrayList<String>();
        File directoryPath = new File("Lab04Data");
        String contents[] = directoryPath.list();
        for (String file : contents) {
            filenames.add(file);
        }
        Concordance reader = new Concordance();
        Hashtable<String, Integer> table = reader.readConcordance(filenames);
        Collision coll = new Collision();
        coll.collisionPerformanceHash(table);
        //coll.collisionPerformanceHorner(table);
        
        System.out.println("The concordance has " + table.size() + " unique words");
        System.out.println("Query the Concordance!");
        System.out.print("Word to ask about (hit return to quit): ");
        BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
        String word = br2.readLine();
        while (word != null && word.length() > 0) {
            if (table.containsKey(word)) {
                System.out.println("\t" + word + " appears " + table.get(word) + " times");
            } else {
                System.out.println("\t" + word + " is not in the concordance");
            }
            System.out.print("Word to ask about (hit return to quit): ");
            word = br2.readLine();
        }
        br2.close();
    }
}
