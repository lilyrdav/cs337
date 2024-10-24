public class OddBitwise {
    public static void main(String[] args) {
        OddBitwise odd = new OddBitwise();
        for (int i = -5; i <= 5; i++) {
            System.out.println(i + " " + odd.isOdd(i));
        }
    }

    public boolean isOdd(int n) {
        return ((n & 1) != 0);
    }
}
