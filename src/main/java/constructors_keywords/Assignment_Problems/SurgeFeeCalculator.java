package main.java.constructors_keywords.Assignment_Problems;

import java.util.Scanner;

public final class SurgeFeeCalculator {

    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        this.minimumSurgePercent = minimumSurgePercent;
    }

    public final double calculateSurgeFee(double orderValue, int delayMinutes) {

        if (orderValue < 0) {
            throw new IllegalArgumentException("Order value cannot be negative");
        }

        if (delayMinutes < 0) {
            throw new IllegalArgumentException("Delay minutes cannot be negative");
        }

        if (delayMinutes == 0) {
            return 0.0;
        }

        double tieredFee = 0.0;

        int firstTierMinutes = Math.min(delayMinutes, 5);
        tieredFee += orderValue * 0.005 * firstTierMinutes;

        if (delayMinutes > 5) {
            int secondTierMinutes = Math.min(delayMinutes - 5, 10);
            tieredFee += orderValue * 0.01 * secondTierMinutes;
        }

        if (delayMinutes > 15) {
            int thirdTierMinutes = delayMinutes - 15;
            tieredFee += orderValue * 0.02 * thirdTierMinutes;
        }

        double minimumFee = orderValue * (minimumSurgePercent / 100.0);

        return Math.max(tieredFee, minimumFee);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter minimum surge percentage: ");
        double minimumSurgePercent = scanner.nextDouble();

        System.out.print("Enter order value: ");
        double orderValue = scanner.nextDouble();

        System.out.print("Enter delay in minutes: ");
        int delayMinutes = scanner.nextInt();

        try {
            SurgeFeeCalculator calculator =
                    new SurgeFeeCalculator(minimumSurgePercent);

            double surgeFee =
                    calculator.calculateSurgeFee(orderValue, delayMinutes);

            System.out.println("Surge Fee: Rs " + surgeFee);

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }

        scanner.close();
    }
}
