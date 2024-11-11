import java.util.Scanner;

public class CaesarCipher {

    public static String encrypt(String plain, int k) {
        StringBuilder cipher = new StringBuilder();
        for (int i = 0; i < plain.length(); i++) {
            if (plain.charAt(i) != ' ') {
                cipher.append((char) ((plain.charAt(i) - 'a' + k) % 26 + 'a'));
            } else {
                cipher.append(' ');
            }
        }
        return cipher.toString();
    }

    public static String decrypt(String cipher, int k) {
        StringBuilder plain = new StringBuilder();
        for (int i = 0; i < cipher.length(); i++) {
            if (cipher.charAt(i) != ' ') {
                plain.append((char) ((cipher.charAt(i) - 'a' - k + 26) % 26 + 'a'));
            } else {
                plain.append(' ');
            }
        }
        return plain.toString();
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("Enter choice: \n1. Decrypt \n2. Encrypt");
        choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String plain, cipher;
        int k;

        if (choice == 1) {
            System.out.println("Enter Encrypted text: ");
            cipher = scanner.nextLine();
            System.out.println("Enter K: ");
            k = scanner.nextInt();
            String decryptedText = decrypt(cipher, k);
            System.out.println("Decrypted text is: " + decryptedText);
        } else if (choice == 2) {
            System.out.println("Enter Plain text: ");
            plain = scanner.nextLine();
            System.out.println("Enter K: ");
            k = scanner.nextInt();
            String encryptedText = encrypt(plain, k);
            System.out.println("\nEncrypted text is: " + encryptedText);
        }

        scanner.close();
    }
}
