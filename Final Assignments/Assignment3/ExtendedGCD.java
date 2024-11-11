import java.util.Scanner;

public class ExtendedGCD {

    // Variables to store coefficients s and t
    private static int ansS, ansT;

    // Extended Euclidean Algorithm to find GCD and coefficients
    public static int findGcdExtended(int r1, int r2, int s1, int s2, int t1, int t2) {
        // Base case
        if (r2 == 0) {
            ansS = s1;
            ansT = t1;
            return r1;
        }

        int q = r1 / r2;
        int r = r1 % r2;

        int s = s1 - q * s2;
        int t = t1 - q * t2;

        System.out.printf("%d %d %d %d %d %d %d %d %d %d%n", q, r1, r2, r, s1, s2, s, t1, t2, t);

        return findGcdExtended(r2, r, s2, s, t2, t);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read input from the user
        System.out.println("Enter 2 numbers to find GCD");
        int num1 = scanner.nextInt();
        int num2 = scanner.nextInt();

        // Calculate the GCD and coefficients
        int gcd = findGcdExtended(num1, num2, 1, 0, 0, 1);

        // Print the results
        System.out.println("\n\nGCD = " + gcd);
        System.out.println("S = " + ansS);
        System.out.println("T = " + ansT);

        // Close the scanner
        scanner.close();
    }
}
