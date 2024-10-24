import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Collections;
import java.io.File;
import java.io.IOException;

public class InvertedIndex {
    public static Map<String, List<WordDocumentLoc>> buildInvIdx(List<String> filenames) {
        Map<String, List<WordDocumentLoc>> invIdx = new HashMap<String, List<WordDocumentLoc>>();
        GGReader myReader = new GGReader();
        for (String filename : filenames) {
            List<String> words = myReader.readFile(filename);
            int wordloc = 0;
            for (String w : words) {
                w = w.toLowerCase();
                if (!invIdx.containsKey(w)) {
                    invIdx.put(w, new ArrayList<WordDocumentLoc>());
                }
                invIdx.get(w).add(new WordDocumentLoc(filename, wordloc));
                wordloc++;
            }
        }
        return invIdx;
    }

    public static List<String> getDocumentsForWords(Map<String, List<WordDocumentLoc>> II, List<String> W) {
        Map<String, Integer> DI = new HashMap<String, Integer>();
        for (String w : W) {
            List<WordDocumentLoc> FW = II.get(w);
            List<String> UF = new ArrayList<String>();
            for (WordDocumentLoc wd : FW) {
                if (!UF.contains(wd.getDocumentID())) {
                    UF.add(wd.getDocumentID());
                }
            }
            for (String uf : UF) {
                if (!DI.containsKey(uf)) {
                    DI.put(uf, 1);
                }
                else {
                    DI.put(uf, DI.get(uf) + 1);
                }
            }
        }
        List<String> R = new ArrayList<String>();
        for (String docID : DI.keySet()) {
            if (DI.get(docID) == W.size()) {
                R.add(docID);
            }
        }
        return R;
    }

    public static int getSmallestDifference(List<Integer> W1, List<Integer> W2) {
        Collections.sort(W1);
        Collections.sort(W2);
        int idx1 = 0;
        int idx2 = 0;
        int len1 = W1.size();
        int len2 = W2.size();
        int d = Math.abs(W1.get(idx1) - W2.get(idx2));
        while (idx1 < len1 && idx2 < len2) {
            if (Math.abs(W1.get(idx1) - W2.get(idx2)) < d) {
                d = Math.abs(W1.get(idx1) - W2.get(idx2));
            }
            if (W1.get(idx1) < W2.get(idx2)) {
                idx1++;
            }
            else {
                idx2++;
            }
        }
        return d;
    }

    public static void main (String[] args) throws IOException {
        List<String> filenames = new ArrayList<String>();
        File directoryPath = new File("Lab04Data");
        String contents[] = directoryPath.list();
        for (String file : contents) {
            filenames.add(file);
        }
        Map<String, List<WordDocumentLoc>> words = InvertedIndex.buildInvIdx(filenames);
        String word1 = "lily";
        String word2 = "love";
        List<String> W = new ArrayList<String>();
        W.add(word1);
        W.add(word2);
        List<String> docs = InvertedIndex.getDocumentsForWords(words, W);
        System.out.println("[" + word1 + " " + word2 + "]");
        Map<Integer, String> unsortedOutput = new HashMap<Integer, String>();
        for (String doc : docs) {
            List<Integer> locsW1 = new ArrayList<Integer>();
            List<Integer> locsW2 = new ArrayList<Integer>();
            for (WordDocumentLoc wd : words.get(word1)) {
                if (wd.getDocumentID().equals(doc)) {
                    locsW1.add(wd.getWordLoc());
                }
            }
            for (WordDocumentLoc wd : words.get(word2)) {
                if (wd.getDocumentID().equals(doc)) {
                    locsW2.add(wd.getWordLoc());
                }
            }
            int distance = InvertedIndex.getSmallestDifference(locsW1, locsW2);
            unsortedOutput.put(distance, doc);
        }
        Map<Integer, String> sortedOutput = new TreeMap<Integer, String>(unsortedOutput);
        int count = 1;
        for (Map.Entry<Integer, String> entry : sortedOutput.entrySet()) {
            System.out.println(count + "    " + entry.getKey() + " " + entry.getValue());
            count++;
        }
    }
}