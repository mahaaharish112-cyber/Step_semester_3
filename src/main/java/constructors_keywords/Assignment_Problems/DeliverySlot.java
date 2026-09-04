package main.java.constructors_keywords.Assignment_Problems;

import java.util.Scanner;

public class DeliverySlot {

    private String orderId;
    private String timeSlot;

    public DeliverySlot(String orderId, String timeSlot) {
        this.orderId = orderId;
        this.timeSlot = timeSlot;
    }

    public DeliverySlot(String orderId) {
        this(orderId, "ASAP");
    }

    public boolean isPeakHour() {
        return timeSlot.equals("12:00-13:00")
                || timeSlot.equals("13:00-14:00")
                || timeSlot.equals("19:00-20:00")
                || timeSlot.equals("20:00-21:00");
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Order ID: ");
        String orderId = scanner.nextLine();

        System.out.print("Do you want to enter a time slot? (yes/no): ");
        String choice = scanner.nextLine();

        DeliverySlot slot;

        if (choice.equalsIgnoreCase("yes")) {
            System.out.print("Enter Time Slot: ");
            String timeSlot = scanner.nextLine();

            slot = new DeliverySlot(orderId, timeSlot);
        } else {
            slot = new DeliverySlot(orderId);
        }

        System.out.println("Order ID: " + slot.orderId);
        System.out.println("Time Slot: " + slot.timeSlot);
        System.out.println("Peak Hour: " + slot.isPeakHour());

        scanner.close();
    }
}
