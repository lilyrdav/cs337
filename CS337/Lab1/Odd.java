public class Odd {
    public static void main(String[] args) {
        Odd odd = new Odd();
        for (int i = -5; i <= 5; i++) {
            System.out.println(i + " " + odd.isOdd(i));
        }
    }

    public boolean isOdd(int n) {
        if (n % 2 == 1) {
            return true;
        } else {
            return false;
        }
    }
}