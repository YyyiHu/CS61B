/** Class that prints the Collatz sequence starting from a given number.
 *  @author Yi
 */
public class Collatz {

    /**
     * Calculates the next number in the Collatz sequence based on the given number.
     *
     * If the input number is odd, the next number is calculated as 3 * n + 1.
     * If the input number is even, the next number is calculated as n / 2.
     *
     * @param n The current number in the Collatz sequence.
     * @return The next number in the Collatz sequence.
     */
    public static int nextNumber(int n) {
        if (n % 2 == 1) {
            return 3 * n + 1;
        } else {
            return n / 2;
        }
    }

    public static void main(String[] args) {
        int n = 5;
        while (n != 1) {
            System.out.print(n + " ");
            n = nextNumber(n);
        }
        System.out.println(n);
    }
}