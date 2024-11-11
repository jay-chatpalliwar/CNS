import java.util.*;

public class RailFenceCipher {

    public static String encryptMessage(String plaintext, int rails) {

        // Create the matrix
        char[][] railMatrix = new char[rails][plaintext.length()];
  
        // Filling the rail matrix
        for (int i = 0; i < rails; i++)
        Arrays.fill(railMatrix[i], '\n');
  
        boolean dirDown = false;
        int row = 0, col = 0;
  
        for (int i = 0; i < plaintext.length(); i++) {
  
           // Check the direction of flow
           if (row == 0 || row == rails - 1)
           dirDown = !dirDown;
  
           // Fill the corresponding alphabet
           railMatrix[row][col++] = plaintext.charAt(i);
  
           // Find the next row using direction flag
           if (dirDown)
              row++;
           else
              row--;
        }
        // Now we can create the cipher 
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < rails; i++)
        for (int j = 0; j < plaintext.length(); j++)
        if (railMatrix[i][j] != '\n')
        result.append(railMatrix[i][j]);
  
        return result.toString();
     }
  
      //Decrypt the ciphertext
     public static String decryptMessage(String cipher, int rails) {
        // Create the matrix 
        char[][] railMatrix = new char[rails][cipher.length()];
  
        // Filling the rail matrix 
        for (int i = 0; i < rails; i++)
        Arrays.fill(railMatrix[i], '\n');
  
        // Find the direction
        boolean dirDown = true;
  
        int row = 0, col = 0;
  
        // Mark the places with '*'
        for (int i = 0; i < cipher.length(); i++) {
           // Check the direction of flow
           if (row == 0)
              dirDown = true;
           if (row == rails - 1)
              dirDown = false;
  
           // Place the marker
           railMatrix[row][col++] = '*';
  
           // Find the next row 
           if (dirDown)
              row++;
           else
              row--;
        }
  
        // Now we can produce the fill the rail matrix
        int index = 0;
        for (int i = 0; i < rails; i++)
        for (int j = 0; j < cipher.length(); j++)
        if (railMatrix[i][j] == '*' && index < cipher.length())
           railMatrix[i][j] = cipher.charAt(index++);
  
        StringBuilder result = new StringBuilder();
  
        row = 0;
        col = 0;
        for (int i = 0; i < cipher.length(); i++) {
           // Check the direction of flow
           if (row == 0)
           dirDown = true;
           if (row == rails - 1)
           dirDown = false;
  
           // Place the marker
           if (railMatrix[row][col] != '*')
           result.append(railMatrix[row][col++]);
  
           // Find the next row using direction flag
           if (dirDown)
              row++;
           else
              row--;
        }
        return result.toString();
     }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Encrypt\n2. Decrypt\nEnter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine(); // Consume the newline character

        if (choice == 1) {
            System.out.println("\nEnter plain text: ");
            String plain = sc.nextLine();

            System.out.println("\nEnter key (integer value): ");
            int key = sc.nextInt();

            String cipher = encryptMessage(plain, key);
            System.out.println("\nEncrypted text is : " + cipher);

        } else if (choice == 2) {
            System.out.println("\nEnter cipher text: ");
            String cipher = sc.nextLine();

            System.out.println("\nEnter key (integer value): ");
            int key = sc.nextInt();

            String plain = decryptMessage(cipher, key);
            System.out.println("\nDecrypted text is : " + plain);
        }

        sc.close();
    }
}
