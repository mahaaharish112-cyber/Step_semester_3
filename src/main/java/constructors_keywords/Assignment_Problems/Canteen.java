package main.java.constructors_keywords.Assignment_Problems;

import java.util.Scanner;

public class Canteen {

    private String canteenCode;
    private String canteenName;
    private int trustScore;

    public Canteen(String canteenCode, String canteenName, int trustScore) {
        this.canteenCode = canteenCode;
        this.canteenName = canteenName;
        this.trustScore = trustScore;
    }

    public Canteen(String canteenCode, String canteenName) {
        this(canteenCode, canteenName, 3);
    }

    public int compareTo(Canteen other) {

        if (this.trustScore != other.trustScore) {
            return other.trustScore - this.trustScore;
        }

        int codeComparison = this.canteenCode.compareToIgnoreCase(other.canteenCode);

        if (codeComparison != 0) {
            return codeComparison;
        }

        return this.canteenName.length() - other.canteenName.length();
    }

    public static Canteen[] rankCanteens(Canteen[] canteens) {

        for (int i = 0; i < canteens.length - 1; i++) {

            for (int j = 0; j < canteens.length - 1 - i; j++) {

                if (canteens[j].compareTo(canteens[j + 1]) > 0) {
                    Canteen temp = canteens[j];
                    canteens[j] = canteens[j + 1];
                    canteens[j + 1] = temp;
                }
            }
        }

        return canteens;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of canteens: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        Canteen[] canteens = new Canteen[n];

        for (int i = 0; i < n; i++) {

            System.out.println("\nCanteen " + (i + 1));

            System.out.print("Enter Canteen Code: ");
            String code = scanner.nextLine();

            System.out.print("Enter Canteen Name: ");
            String name = scanner.nextLine();

            System.out.print("Do you want to enter Trust Score? (yes/no): ");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("yes")) {
                System.out.print("Enter Trust Score: ");
                int score = scanner.nextInt();
                scanner.nextLine();

                canteens[i] = new Canteen(code, name, score);
            } else {
                canteens[i] = new Canteen(code, name);
            }
        }

        rankCanteens(canteens);

        System.out.println("\nRanked Canteens:");

        for (int i = 0; i < canteens.length; i++) {
            System.out.println(
                (i + 1) + ". " +
                canteens[i].canteenCode + " - " +
                canteens[i].canteenName + " - Trust Score: " +
                canteens[i].trustScore
            );
        }

        scanner.close();
    }
}
