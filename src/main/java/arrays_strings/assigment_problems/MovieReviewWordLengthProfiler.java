package arrays_strings.assigment_problems;

import java.util.Scanner;

public class MovieReviewWordLengthProfiler {

    public static void classifyWordLengths(String review) {
        String[] words = review.split("\\s+");

        int shortWords = 0;
        int mediumWords = 0;
        int longWords = 0;

        for (int i = 0; i < words.length; i++) {
            int length = words[i].length();

            if (length >= 1 && length <= 4) {
                shortWords++;
            } else if (length >= 5 && length <= 8) {
                mediumWords++;
            } else if (length >= 9) {
                longWords++;
            }
        }

        System.out.println("Short: " + shortWords
                + " | Medium: " + mediumWords
                + " | Long: " + longWords);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter movie review: ");
        String review = scanner.nextLine();

        if (review.trim().isEmpty()) {
            System.out.println("Error: Review cannot be empty.");
        } else {
            classifyWordLengths(review);
        }

        scanner.close();
    }
}
