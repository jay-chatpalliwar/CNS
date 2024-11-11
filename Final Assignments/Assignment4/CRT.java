import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CRT {

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

    // Function to find modular inverse
    public static int modInverse(int A, int M) {
        int g = findGcdExtended(A, M, 1, 0, 0, 1);
        if (g != 1) {
            System.out.println("Inverse doesn't exist");
            return 0;
        } else {
            // m is added to handle negative x
            int res = (ansS % M + M) % M;
            System.out.println("Inverse is " + res);
            return res;
        }
    }

    // Function to solve the system of congruences
    public static int findX(List<Integer> num, List<Integer> rem, int k) {
        // Compute product of all numbers
        int prod = 1;
        for (int i = 0; i < k; i++) {
            prod *= num.get(i);
        }

        // Initialize result
        int result = 0;

        // Apply above formula
        for (int i = 0; i < k; i++) {
            int pp = prod / num.get(i);
            result += rem.get(i) * modInverse(pp, num.get(i)) * pp;
        }

        return result % prod;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of equations
        int k = scanner.nextInt();

        // Read the numbers and remainders
        List<Integer> num = new ArrayList<>();
        List<Integer> rem = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            num.add(scanner.nextInt());
        }
        for (int i = 0; i < k; i++) {
            rem.add(scanner.nextInt());
        }

        // Find the solution to the system of congruences
        int x = findX(num, rem, k);
        System.out.println("x is " + x);

        // Close the scanner
        scanner.close();
    }
}
