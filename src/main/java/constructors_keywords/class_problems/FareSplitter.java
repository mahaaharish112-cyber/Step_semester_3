package constructors_keywords.class_problems;

import java.util.Scanner;

public class FareSplitter {

    private String tripId;
    private double totalFare;
    private int passengerCount;

    public FareSplitter(String tripId, double totalFare, int passengerCount) {
        if (totalFare < 0) {
            throw new IllegalArgumentException("Fare cannot be negative");
        }

        if (passengerCount <= 0) {
            throw new IllegalArgumentException("Passenger count must be positive");
        }

        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 1);
    }

    public FareSplitter(String tripId) {
        this(tripId, 0.0, 1);
    }

    public double[] fareBreakdown() {

        if (totalFare == 0) {
            return new double[passengerCount];
        }

        double[] shares = new double[passengerCount];

        long totalPaise = Math.round(totalFare * 100);
        long basePaise = totalPaise / passengerCount;
        long remainder = totalPaise % passengerCount;

        for (int i = 0; i < passengerCount; i++) {
            long sharePaise = basePaise;

            if (i == passengerCount - 1) {
                sharePaise += remainder;
            }

            shares[i] = sharePaise / 100.0;
        }

        return shares;
    }

    public boolean isConfirmationOverdue(int confirmed, int expected) {

        if (expected <= 0) {
            return false;
        }

        return confirmed < expected;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter trip ID: ");
        String tripId = scanner.nextLine();

        System.out.print("Enter total fare: ");
        double totalFare = scanner.nextDouble();

        System.out.print("Enter passenger count: ");
        int passengerCount = scanner.nextInt();

        try {
            FareSplitter splitter =
                    new FareSplitter(tripId, totalFare, passengerCount);

            double[] breakdown = splitter.fareBreakdown();

            System.out.println("Fare Breakdown:");

            for (int i = 0; i < breakdown.length; i++) {
                System.out.printf("Passenger %d: %.2f%n",
                        i + 1, breakdown[i]);
            }

            System.out.print("Enter confirmed passengers: ");
            int confirmed = scanner.nextInt();

            System.out.print("Enter expected passengers: ");
            int expected = scanner.nextInt();

            System.out.println(
                    "Confirmation overdue: "
                    + splitter.isConfirmationOverdue(confirmed, expected)
            );

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid split: " + e.getMessage());
        }

        scanner.close();
    }
}
