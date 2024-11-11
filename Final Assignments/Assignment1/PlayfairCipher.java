import java.util.*;

class PlayfairCipher {
    public static Pair<char[][], Map<Character, Pair<Integer, Integer>>> getKeyMatrixAndPositions(String key) {
        char[][] keyMatrix = new char[5][5];
        int i = 0, j = 0;
        Set<Character> set = new HashSet<>();
        Map<Character, Pair<Integer, Integer>> position = new HashMap<>();

        for (char c : key.toCharArray()) {
            if (c == 'j') {
                c = 'i';
            }

            if (set.contains(c)) {
                continue;
            }

            set.add(c);
            keyMatrix[i][j] = c;
            position.put(c, new Pair<>(i, j));

            j++;
            if (j == 5) {
                j = 0;
                i++;
            }
        }

        for (char c = 'a'; c <= 'z'; c++) {
            if (c == 'j') {
                continue;
            }

            if (set.contains(c)) {
                continue;
            }

            set.add(c);
            keyMatrix[i][j] = c;
            position.put(c, new Pair<>(i, j));

            j++;
            if (j == 5) {
                j = 0;
                i++;
            }
        }

        return new Pair<>(keyMatrix, position);
    }

    public static List<String> getDiagrams(String text) {
        int n = text.length();
        int i = 0;
        List<String> diagrams = new ArrayList<>();

        while (i + 1 < n) {
            if (text.charAt(i) != text.charAt(i + 1)) {
                diagrams.add("" + Character.toLowerCase(text.charAt(i)) + Character.toLowerCase(text.charAt(i + 1)));
                i += 2;
            } else {
                diagrams.add("" + Character.toLowerCase(text.charAt(i)) + 'x');
                i++;
            }
        }

        if (i == n - 1) {
            diagrams.add("" + Character.toLowerCase(text.charAt(i)) + 'x');
        }

        return diagrams;
    }

    public static String encrypt(String plaintext, String key) {
        Pair<char[][], Map<Character, Pair<Integer, Integer>>> p = getKeyMatrixAndPositions(key);
        char[][] keyMatrix = p.getFirst();
        Map<Character, Pair<Integer, Integer>> position = p.getSecond();
        List<String> diagrams = getDiagrams(plaintext);
        StringBuilder ciphertext = new StringBuilder();

        for (String diagram : diagrams) {
            Pair<Integer, Integer> p1 = position.get(diagram.charAt(0));
            Pair<Integer, Integer> p2 = position.get(diagram.charAt(1));
            int i0 = p1.getFirst(), j0 = p1.getSecond();
            int i1 = p2.getFirst(), j1 = p2.getSecond();

            if (i0 == i1) {
                diagram = "" + keyMatrix[i0][(j0 + 1) % 5] + keyMatrix[i0][(j1 + 1) % 5];
            } else if (j0 == j1) {
                diagram = "" + keyMatrix[(i0 + 1) % 5][j0] + keyMatrix[(i1 + 1) % 5][j0];
            } else {
                diagram = "" + keyMatrix[i0][j1] + keyMatrix[i1][j0];
            }

            ciphertext.append(diagram);
        }

        return ciphertext.toString().toUpperCase();
    }

    public static String decrypt(String ciphertext, String key) {
        Pair<char[][], Map<Character, Pair<Integer, Integer>>> p = getKeyMatrixAndPositions(key);
        char[][] keyMatrix = p.getFirst();
        Map<Character, Pair<Integer, Integer>> position = p.getSecond();

        List<String> diagrams = getDiagrams(ciphertext.toLowerCase());
        StringBuilder plaintext = new StringBuilder();

        for (String diagram : diagrams) {
            Pair<Integer, Integer> p1 = position.get(diagram.charAt(0));
            Pair<Integer, Integer> p2 = position.get(diagram.charAt(1));
            int i0 = p1.getFirst(), j0 = p1.getSecond();
            int i1 = p2.getFirst(), j1 = p2.getSecond();

            if (i0 == i1) {
                diagram = "" + keyMatrix[i0][(j0 - 1 + 5) % 5] + keyMatrix[i0][(j1 - 1 + 5) % 5];
            } else if (j0 == j1) {
                diagram = "" + keyMatrix[(i0 - 1 + 5) % 5][j0] + keyMatrix[(i1 - 1 + 5) % 5][j0];
            } else {
                diagram = "" + keyMatrix[i0][j1] + keyMatrix[i1][j0];
            }

            plaintext.append(diagram);
        }

        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("PlayFair Cipher:");
        System.out.println("Enter your choice:");
        System.out.println("1. Encrypt");
        System.out.println("2. Decrypt");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1: {
                System.out.println("Enter plaintext: ");
                String plaintext = scanner.nextLine().replaceAll("\\s+", "");

                System.out.println("Enter key : ");
                String key = scanner.nextLine();

                String ciphertext = encrypt(plaintext, key);

                System.out.println("Plaintext:  " + plaintext);
                System.out.println("Ciphertext: " + ciphertext);
                break;
            }

            case 2: {
                System.out.println("Enter ciphertext: ");
                String ciphertext = scanner.nextLine();

                System.out.println("Enter key : ");
                String key = scanner.nextLine();

                String plaintext = decrypt(ciphertext, key);

                System.out.println("Ciphertext: " + ciphertext);
                System.out.println("Plaintext:  " + plaintext);
                break;
            }
        }

        scanner.close();
    }
}

// Utility Pair class to handle pairs
class Pair<U, V> {
    private final U first;
    private final V second;

    public Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }

    public U getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }
}
