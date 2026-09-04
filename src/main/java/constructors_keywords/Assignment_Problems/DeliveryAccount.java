package main.java.constructors_keywords.Assignment_Problems;

import java.util.Scanner;

public class DeliveryAccount {

    private static double regularRate;

    private String studentId;
    private double orderValue;

    static {
        regularRate = 1.0;
    }

    public DeliveryAccount(String studentId, double orderValue) {
        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    public DeliveryAccount(String studentId) {
        this(studentId, 0.0);
    }

    public final double calculateSurgeFee(int delayMinutes) {

        if (delayMinutes < 0) {
            throw new IllegalArgumentException("Delay minutes cannot be negative");
        }

        if (orderValue < 0) {
            throw new IllegalArgumentException("Order value cannot be negative");
        }

        if (delayMinutes == 0) {
            return 0.0;
        }

        double fee;

        if (delayMinutes <= 5) {
            fee = orderValue * 0.005 * delayMinutes;
        } else if (delayMinutes <= 15) {
            fee = orderValue * 0.005 * 5;
            fee += orderValue * 0.01 * (delayMinutes - 5);
        } else {
            fee = orderValue * 0.005 * 5;
            fee += orderValue * 0.01 * 10;
            fee += orderValue * 0.02 * (delayMinutes - 15);
        }

        double minimumFee = orderValue * regularRate / 100.0;

        return Math.max(fee, minimumFee);
    }

    public void processAccount(
            DeliveryAccount account,
            double amount,
            int delayMinutes) {

        if (account == null) {
            return;
        }

        if (amount < 0) {
            throw new IllegalArgumentException("Order amount cannot be negative");
        }

        account.orderValue = amount;

        double surgeFee = account.calculateSurgeFee(delayMinutes);

        if (account instanceof PremiumAccount) {
            surgeFee = surgeFee * 0.5;
        }

        System.out.println(
                account.studentId +
                " | Amount: Rs " + amount +
                " | Delay: " + delayMinutes +
                " minutes | Surge Fee: Rs " + surgeFee
        );
    }

    public static void processBatch(
            DeliveryAccount[] accounts,
            double[] amounts,
            int[] delayMinutesArray) {

        if (accounts == null ||
            amounts == null ||
            delayMinutesArray == null) {

            throw new IllegalArgumentException("Arrays cannot be null");
        }

        if (accounts.length != amounts.length ||
            accounts.length != delayMinutesArray.length) {

            throw new IllegalArgumentException(
                    "All arrays must have the same length"
            );
        }

        int processed = 0;
        int nullSkipped = 0;
        int premium = 0;
        int regular = 0;
        double grandTotal = 0.0;

        DeliveryAccount processor =
                new DeliveryAccount("PROCESSOR");

        for (int i = 0; i < accounts.length; i++) {

            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            processor.processAccount(
                    accounts[i],
                    amounts[i],
                    delayMinutesArray[i]
            );

            double surgeFee =
                    accounts[i].calculateSurgeFee(
                            delayMinutesArray[i]
                    );

            if (accounts[i] instanceof PremiumAccount) {
                surgeFee = surgeFee * 0.5;
                premium++;
            } else if (accounts[i] instanceof DeliveryAccount) {
                regular++;
            }

            grandTotal += surgeFee;
            processed++;
        }

        System.out.println();

        System.out.println(
                processed + " processed | " +
                nullSkipped + " null skipped | " +
                premium + " premium | " +
                regular + " regular"
        );

        System.out.println(
                "Grand total surge fees = Rs " + grandTotal
        );
    }

    public static class PremiumAccount extends DeliveryAccount {

        public PremiumAccount(String studentId, double orderValue) {
            super(studentId, orderValue);
        }

        public PremiumAccount(String studentId) {
            super(studentId);
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of accounts: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        DeliveryAccount[] accounts =
                new DeliveryAccount[n];

        double[] amounts =
                new double[n];

        int[] delayMinutesArray =
                new int[n];

        for (int i = 0; i < n; i++) {

            System.out.println("\nAccount " + (i + 1));

            System.out.print(
                    "Enter account type (premium/regular/null): "
            );

            String type = scanner.nextLine();

            if (type.equalsIgnoreCase("null")) {
                accounts[i] = null;
                amounts[i] = 0;
                delayMinutesArray[i] = 0;
                continue;
            }

            System.out.print("Enter Student ID: ");
            String studentId = scanner.nextLine();

            System.out.print("Enter Order Value: ");
            double orderValue = scanner.nextDouble();

            scanner.nextLine();

            if (type.equalsIgnoreCase("premium")) {
                accounts[i] =
                        new PremiumAccount(
                                studentId,
                                orderValue
                        );
            } else {
                accounts[i] =
                        new DeliveryAccount(
                                studentId,
                                orderValue
                        );
            }

            System.out.print("Enter Amount: ");
            amounts[i] = scanner.nextDouble();

            System.out.print("Enter Delay Minutes: ");
            delayMinutesArray[i] = scanner.nextInt();

            scanner.nextLine();
        }

        try {
            processBatch(
                    accounts,
                    amounts,
                    delayMinutesArray
            );
        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Invalid input: " + e.getMessage()
            );
        }

        scanner.close();
    }
}
