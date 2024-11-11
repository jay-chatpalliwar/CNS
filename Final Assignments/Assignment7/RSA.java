import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Random;

public class RSA
{
    private BigInteger P;
    private BigInteger Q;
    private BigInteger N;
    private BigInteger PHI;
    private BigInteger e;
    private BigInteger d;
    private int maxLength = 1024;
    private Random R;

    public RSA()
    {
        R = new Random();
        P = BigInteger.probablePrime(maxLength, R);
        Q = BigInteger.probablePrime(maxLength, R);
        N = P.multiply(Q);
        PHI = P.subtract(BigInteger.ONE).multiply(Q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(maxLength / 2, R);
        while (PHI.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(PHI) < 0)
        {
            e = e.add(BigInteger.ONE);  // Update e to a valid value
        }
        d = e.modInverse(PHI);  // Calculate d, the private key exponent
    }

    public RSA(BigInteger e, BigInteger d, BigInteger N)
    {
        this.e = e;
        this.d = d;
        this.N = N;
    }

    public static void main(String[] args) throws IOException
    {
        RSA rsa = new RSA();
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String inputString;
        System.out.println("Enter message you wish to send:");
        inputString = input.readLine();
        System.out.println("Encrypting the message: " + inputString);
        System.out.println("The message in bytes is: " + bToS(inputString.getBytes()));

        // Encryption
        byte[] cipher = rsa.encryptMessage(inputString.getBytes());

        // Decryption
        byte[] plain = rsa.decryptMessage(cipher);
        System.out.println("Decrypting Bytes: " + bToS(plain));
        System.out.println("Plain message is: " + new String(plain));
    }

    private static String bToS(byte[] cipher)
    {
        StringBuilder temp = new StringBuilder();
        for (byte b : cipher)
        {
            temp.append(Byte.toString(b)).append(" ");
        }
        return temp.toString().trim();
    }

    // Encrypting the message
    public byte[] encryptMessage(byte[] message)
    {
        return (new BigInteger(message)).modPow(e, N).toByteArray();
    }

    // Decrypting the message
    public byte[] decryptMessage(byte[] message)
    {
        return (new BigInteger(message)).modPow(d, N).toByteArray();
    }
}
