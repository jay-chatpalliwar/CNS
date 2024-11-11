import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class DESExample {

    // Method to encrypt a message using DES
    public static String encrypt(String plainText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Method to decrypt a message using DES
    public static String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }

    // Generate a DES secret key
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(56); // DES uses a key size of 56 bits
        return keyGenerator.generateKey();
    }

    public static void main(String[] args) {
        try {
            String message = "Hello, World!";
            
            // Generate a DES key
            SecretKey secretKey = generateKey();

            // Encrypt the message
            String encryptedMessage = encrypt(message, secretKey);
            System.out.println("Encrypted Message: " + encryptedMessage);

            // Decrypt the message
            String decryptedMessage = decrypt(encryptedMessage, secretKey);
            System.out.println("Decrypted Message: " + decryptedMessage);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
