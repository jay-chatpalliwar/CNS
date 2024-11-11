import java.util.Scanner;

public class euclidien {

    // Function to find the Greatest Common Divisor (GCD) using Euclid's algorithm
    public static int findGCD(int num1, int num2) {
        if (num2 == 0) {
            return num1;
        }
        System.out.printf("%.10f\t%d\t%d\t%d%n", (double) num1 / num2, num1, num2, num1 % num2);
        return findGCD(num2, num1 % num2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read input from the user
        int num1 = scanner.nextInt();
        int num2 = scanner.nextInt();
        
        // Calculate the GCD
        int gcd = findGCD(num1, num2);
        
        // Print the result
        System.out.println("GCD is " + gcd);
        
        // Close the scanner
        scanner.close();
    }
}
