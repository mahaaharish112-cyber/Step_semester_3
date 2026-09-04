package arrays_strings.assigment_problems;

import java.util.Scanner;

public class TypingSpeedAccuracyChecker {

    public static void checkTypingAccuracy(String original, String typed) {
        int matched = 0;
        int firstMismatch = -1;

        for (int i = 0; i < original.length(); i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatch == -1) {
                firstMismatch = i;
            }
        }

        double accuracy = ((double) matched / original.length()) * 100;

        System.out.printf("Matched: %d/%d | Accuracy: %.2f%%", 
                          matched, original.length(), accuracy);

        if (firstMismatch == -1) {
            System.out.println(" | No Mismatches");
        } else {
            System.out.println(" | First Mismatch at position " 
                    + (firstMismatch + 1) 
                    + " ('" + original.charAt(firstMismatch) 
                    + "' vs '" + typed.charAt(firstMismatch) + "')");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter original passage: ");
        String original = scanner.nextLine();

        System.out.print("Enter typed text: ");
        String typed = scanner.nextLine();

        if (original.length() != typed.length()) {
            System.out.println("Error: Both strings must have equal length.");
        } else if (original.length() == 0) {
            System.out.println("Error: Strings cannot be empty.");
        } else {
            checkTypingAccuracy(original, typed);
        }

        scanner.close();
    }
}
