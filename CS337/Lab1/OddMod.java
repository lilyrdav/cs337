public class OddMod {
    public static void main(String[] args) {
        OddMod odd = new OddMod();
        for (int i = -5; i <= 5; i++) {
            System.out.println(i + " " + odd.isOdd(i));
        }
    }

    public boolean isOdd(int n) {
        if (Math.abs(n) % 2 == 1) {
            return true;
        } else {
            return false;
        }
    }
}