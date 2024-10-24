public class Horner {
    public int horner(String s) {
        int sm = 0;
        int mul = 89;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int vch = (int) ch;
            sm = sm * mul + vch;
        }
        return sm;
    }

    public static void main(String[] args) {
        Horner Horner = new Horner();
        String[] words = {"Bryn Mawr", "k-Cass", "Haverford", "Swarthmore"};
        for (String word : words) {
            System.out.println(word + ": " + Math.abs(Horner.horner(word)));
        }
    }
}
