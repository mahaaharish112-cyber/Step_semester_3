package main.java.String_Operations_Performance.assigment_problems;

import java.util.*;

public class StopWordFilteredWordFrequencyReport {

    public static void printFilteredWordFrequency(String feedback) {
        String cleanedText = feedback.toLowerCase()
                .replace(".", "")
                .replace(",", "");

        String[] words = cleanedText.split("\\s+");

        String[] stopWords = {"the", "was", "and", "a", "is", "of", "in"};

        HashMap<String, Integer> frequency = new HashMap<>();

        for (String word : words) {
            boolean isStopWord = false;

            for (String stopWord : stopWords) {
                if (word.equals(stopWord)) {
                    isStopWord = true;
                    break;
                }
            }

            if (!isStopWord) {
                frequency.put(word, frequency.getOrDefault(word, 0) + 1);
            }
        }

        List<Map.Entry<String, Integer>> wordList =
                new ArrayList<>(frequency.entrySet());

        wordList.sort((entry1, entry2) ->
                entry2.getValue().compareTo(entry1.getValue()));

        for (Map.Entry<String, Integer> entry : wordList) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter feedback paragraph: ");
        String feedback = scanner.nextLine();

        printFilteredWordFrequency(feedback);

        scanner.close();
    }
}
