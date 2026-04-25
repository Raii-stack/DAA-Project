package bruteForce;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class BruteForceTry {
    public static void main(String[] args) {
        String[] dictionary = new String[10000];
        int wordCount = loadDictionary("data/words_10000.txt", dictionary);

        if (wordCount == 0) return;

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter prefix to search: ");
            String prefix = scanner.next();
            performSearch(dictionary, wordCount, prefix);
        }
    }

    private static int loadDictionary(String filePath, String[] dictionary) {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) { //used BufferedReader for faster reading of file in load time, instead of Scanner.
            String line;
            while ((line = reader.readLine()) != null && count < dictionary.length) {
                dictionary[count++] = line.split("\\s+")[0];    //Split the string here so  that in loading the dictionary only words will be loaded. 
            }
            System.out.println("Loaded " + count + " words.");
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found at " + filePath);
        }
        return count;
    }
    
    private static void performSearch(String[] dict, int count, String prefix) {
        long startTime = System.nanoTime();
        int matches = 0;

        for (int i = 0; i < count; i++) {
            if (dict[i] != null && dict[i].startsWith(prefix)) { //added null to check for any null pointer exceptions.
                System.out.println("Match found: " + dict[i]); //Removed the split, for small time improvement when using performSearch
                matches++;
            }
        }

        long duration = System.nanoTime() - startTime;

        if (matches == 0) {
            System.out.println("No matches found for prefix: " + prefix);
        } else {
            System.out.println("\nTotal matches: " + matches);
            System.out.println("Search took: " + (duration / 1000000.0) + " ms");
        }
    }

    public static int countMatches(String[] dict, int count, String prefix) { 
        int matches = 0;
        for (int i = 0; i < count; i++) {
            if (dict[i] != null && dict[i].startsWith(prefix)) {
                matches++;
            }
        }
        return matches;
    }
}
