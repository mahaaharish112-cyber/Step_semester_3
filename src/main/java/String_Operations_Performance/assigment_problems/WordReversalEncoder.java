package main.java.String_Operations_Performance.assigment_problems;

import java.util.Scanner;

public class WordReversalEncoder {

   
    public static String reverseEachWord(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            StringBuilder reversedWord = new StringBuilder(word);
            reversedWord.reverse();

            result.append(reversedWord).append(" ");
        }

        return result.toString().trim();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Enter a sentence: ");
        String sentence = scanner.nextLine();

       
        String result = reverseEachWord(sentence);
        System.out.println("Reversed sentence: " + result);

        scanner.close();
    }
}
