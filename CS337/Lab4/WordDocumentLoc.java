public class WordDocumentLoc {
    private String documentID;
    private int wordLoc;

    public WordDocumentLoc(String documentID, int wordLoc) {
        this.documentID = documentID;
        this.wordLoc = wordLoc;
    }

    public String getDocumentID() {
        return documentID;
    }

    public int getWordLoc() {
        return wordLoc;
    }

    public String toString() {
        return documentID + " " + wordLoc;
    }
}
