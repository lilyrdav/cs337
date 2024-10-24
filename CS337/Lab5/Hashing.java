public class Hashing {
    public static void main(String[] args) {
        String[] words = {"Bryn Mawr", "k-Cass", "Haverford", "Swarthmore"};
        for (String word : words) {
            System.out.println(word + ": " + Math.abs(word.hashCode()));
        }
    }
}
