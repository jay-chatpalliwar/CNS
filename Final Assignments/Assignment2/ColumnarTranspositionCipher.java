import java.util.*;

public class ColumnarTranspositionCipher {

    static Map<Character, Integer> keyMap = new TreeMap<>();

    static void setPermutationOrder(String key) {
        // Add the permutation order into the map
        for (int i = 0; i < key.length(); i++) {
            keyMap.put(key.charAt(i), i);
        }
    }

    // Encryption Function
    static String encrypt(String plaintext, String key) {
        setPermutationOrder(key);

        int rows, columns;
        StringBuilder ciphertext = new StringBuilder();

        // Number of columns in the matrix
        columns = key.length();

        // Maximum number of rows in the matrix
        rows = (int) Math.ceil((double) plaintext.length() / columns);

        char[][] matrix = new char[rows][columns];

        for (int i = 0, k = 0; i < rows; i++) {
            for (int j = 0; j < columns; ) {
                if (k < plaintext.length()) {
                    char ch = plaintext.charAt(k);
                    if (Character.isLetter(ch) || ch == ' ') {
                        matrix[i][j] = ch;
                        j++;
                    }
                    k++;
                } else {
                    matrix[i][j] = '_';
                    j++;
                }
            }
        }

        for (Map.Entry<Character, Integer> entry : keyMap.entrySet()) {
            int columnIndex = entry.getValue();

            // Get the cipher text
            for (int i = 0; i < rows; i++) {
                if (Character.isLetter(matrix[i][columnIndex]) || matrix[i][columnIndex] == ' ' || matrix[i][columnIndex] == '_') {
                    ciphertext.append(matrix[i][columnIndex]);
                }
            }
        }
        return ciphertext.toString();
    }

    // Decryption Function
    static String decrypt(String ciphertext, String key) {
        setPermutationOrder(key);

        int columns = key.length();
        int rows = (int) Math.ceil((double) ciphertext.length() / columns);
        char[][] cipherMat = new char[rows][columns];

        // Add characters into the matrix column-wise
        int k = 0;
        for (Map.Entry<Character, Integer> entry : keyMap.entrySet()) {
            int columnIndex = entry.getValue();
            for (int i = 0; i < rows; i++) {
                if (k < ciphertext.length()) {
                    cipherMat[i][columnIndex] = ciphertext.charAt(k);
                    k++;
                } else {
                    cipherMat[i][columnIndex] = '_';
                }
            }
        }

        char[][] decCipher = new char[rows][columns];
        for (int l = 0; l < key.length(); l++) {
            int columnIndex = keyMap.get(key.charAt(l));
            for (int i = 0; i < rows; i++) {
                decCipher[i][l] = cipherMat[i][columnIndex];
            }
        }

        // Get the message with the help of the matrix
        StringBuilder msg = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (decCipher[i][j] != '_') {
                    msg.append(decCipher[i][j]);
                }
            }
        }
        return msg.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Encrypt\n2. Decrypt\nEnter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        if (choice == 1) {
            System.out.println("\nEnter plain text: ");
            String plain = scanner.nextLine();

            System.out.println("\nEnter key: ");
            String key = scanner.nextLine();

            String cipher = encrypt(plain, key);

            System.out.println("\nEncrypted text is : " + cipher);
        } else if (choice == 2) {
            System.out.println("\nEnter cipher text: ");
            String cipher = scanner.nextLine();

            System.out.println("\nEnter key: ");
            String key = scanner.nextLine();

            String plain = decrypt(cipher, key);

            System.out.println("\nDecrypted text is : " + plain);
        }

        scanner.close();
    }
}
