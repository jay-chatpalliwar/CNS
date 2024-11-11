import java.util.*;

public class vigenere {

    public static String format(String str) {
        StringBuilder res = new StringBuilder();
        for (char ch : str.toCharArray()) {
            if (ch != ' ') {
                res.append(Character.toLowerCase(ch));
            }
        }
        return res.toString();
    }

    public static String encrypt(String plain, String key) {
        StringBuilder cipher = new StringBuilder();
        for (int i = 0; i < plain.length(); i++) {
            int val = plain.charAt(i) - 'a' + key.charAt(i % key.length()) - 'a';
            cipher.append((char) ('a' + (val % 26)));
        }
        return cipher.toString();
    }

    public static String decrypt(String cipher, String key) {
        StringBuilder plain = new StringBuilder();
        for (int i = 0; i < cipher.length(); i++) {
            int val = cipher.charAt(i) - 'a' - (key.charAt(i % key.length()) - 'a');
            plain.append((char) ('a' + (val + 26) % 26));
        }
        return plain.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Encrypt\n2. Decrypt\nEnter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        if (choice == 1) {
            System.out.println("\nEnter plain text: ");
            String plain = scanner.nextLine();
            plain = format(plain);

            System.out.println("\nEnter key: ");
            String key = scanner.nextLine();

            String cipher = encrypt(plain, key);

            System.out.println("\nEncrypted text is : " + cipher);
        } else if (choice == 2) {
            System.out.println("\nEnter cipher text: ");
            String cipher = scanner.nextLine();
            cipher = format(cipher);

            System.out.println("\nEnter key: ");
            String key = scanner.nextLine();

            String plain = decrypt(cipher, key);

            System.out.println("\nDecrypted text is : " + plain);
        }

        scanner.close();
    }
}
