
import java.util.Scanner;

public class PalindromeChecker {

    public static boolean isPalindromeIterative(String text) {
        int left = 0;
        int right = text.length() - 1;

        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    public static boolean isPalindromeRecursive(String text) {
        if (text.length() <= 1) {
            return true;
        }

        if (text.charAt(0) != text.charAt(text.length() - 1)) {
            return false;
        }

        return isPalindromeRecursive(text.substring(1, text.length() - 1));
    }

    public static boolean isPalindromeArrayReversal(String text) {
        char[] original = text.toCharArray();
        char[] reversed = new char[original.length];

        for (int i = 0; i < original.length; i++) {
            reversed[i] = original[original.length - 1 - i];
        }

        return new String(original).equals(new String(reversed));
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a word or phrase: ");
        String text = scanner.nextLine();

        boolean iterativeResult = isPalindromeIterative(text);
        boolean recursiveResult = isPalindromeRecursive(text);
        boolean arrayResult = isPalindromeArrayReversal(text);

        System.out.println("\nInput: " + text);

        System.out.println("Iterative: " +
                (iterativeResult ? "Palindrome" : "Not Palindrome"));

        System.out.println("Recursive: " +
                (recursiveResult ? "Palindrome" : "Not Palindrome"));

        System.out.println("Array Reversal: " +
                (arrayResult ? "Palindrome" : "Not Palindrome"));

        if (iterativeResult == recursiveResult &&
            recursiveResult == arrayResult) {
            System.out.println("\nAll three approaches agree.");
        } else {
            System.out.println("\nThe approaches do not agree.");
        }

        scanner.close();
    }
}