package main.java.String_Operations_Performance.assigment_problems;

import java.util.Scanner;

public class LibraryIsbnNormalizerValidator {

    public static String normalizeCode(String raw) {
        String code = raw.trim();

        String publisherCode = code.substring(0, 3).toUpperCase();
        String remainingPart = code.substring(3);

        return publisherCode + remainingPart;
    }

    public static String validateAndFormat(String code) {

        if (code.length() != 13) {
            return "Invalid: wrong length";
        }

        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: body must contain only digits";
            }
        }

        String publisherCode = code.substring(0, 3);
        String year = code.substring(3, 7);
        String catalogNumber = code.substring(7);

        StringBuilder result = new StringBuilder();

        result.append("[")
              .append(publisherCode)
              .append("] YEAR: ")
              .append(year)
              .append(" | CATALOG: ")
              .append(catalogNumber);

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter ISBN-style code: ");
        String rawCode = scanner.nextLine();

        String normalizedCode = normalizeCode(rawCode);
        String result = validateAndFormat(normalizedCode);

        System.out.println(result);

        scanner.close();
    }
}
