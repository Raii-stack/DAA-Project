package prefixTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TrieDriver2 {
    public static void main(String[] args) {
        String filePath = "data/words_10000.txt";
        if (!new File(filePath).exists()) {
            filePath = "../data/words_10000.txt";
        }

        String[] dictionary = new String[10000];
        int count = loadDictionary(filePath, dictionary);
        if (count == 0) {
            System.out.println("Dictionary not found. Exiting.");
            return;
        }

        Trie trie = new Trie();
        for (int i = 0; i < count; i++) {
            trie.insert(dictionary[i]);
        }

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter prefix: ");
            String prefix = scanner.next();
            long start = System.nanoTime();
            List<String> results = trie.autocomplete(prefix);
            double ms = (System.nanoTime() - start) / 1_000_000.0;
            if (results.isEmpty()) {
                System.out.println("No matches for: " + prefix);
            } else {
                for (String r : results) {
                    System.out.println(r.split("\\s+")[0]);
                }
                System.out.println("Matches: " + results.size());
                System.out.println("Time: " + ms + " ms");
            }
        }
    }

    private static int loadDictionary(String filePath, String[] dictionary) {
        int i = 0;
        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            while (fileScanner.hasNextLine() && i < dictionary.length) {
                dictionary[i++] = fileScanner.nextLine();
            }
            System.out.println("Loaded " + i + " words from " + filePath);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
        }
        return i;
    }
}
