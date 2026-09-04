package constructors_keywords.class_problems;

import java.util.HashSet;
import java.util.Scanner;

class BusTicket {

    private String passengerName;
    private String destination;
    private boolean checkedIn;

    public BusTicket(String passengerName, String destination) {

        if (!isValidName(passengerName)) {
            throw new IllegalArgumentException("Invalid passenger name");
        }

        if (!isValidDestination(destination)) {
            throw new IllegalArgumentException("Invalid destination");
        }

        this.passengerName = passengerName.trim();
        this.destination = destination.trim();
        this.checkedIn = false;
    }

    private static boolean isValidName(String name) {

        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        for (int i = 0; i < name.trim().length(); i++) {
            char ch = name.trim().charAt(i);

            if (!Character.isLetter(ch) && ch != ' ') {
                return false;
            }
        }

        return true;
    }

    private static boolean isValidDestination(String destination) {

        if (destination == null || destination.trim().isEmpty()) {
            return false;
        }

        for (int i = 0; i < destination.trim().length(); i++) {
            char ch = destination.trim().charAt(i);

            if (!Character.isLetter(ch) && ch != ' ') {
                return false;
            }
        }

        return true;
    }

    public void markCheckedIn() {

        if (!checkedIn) {
            checkedIn = true;
        }
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getDestination() {
        return destination;
    }

    public static void processBatch(String[][] rawBookings) {

        HashSet<String> acceptedBookings = new HashSet<>();

        int valid = 0;
        int rejected = 0;
        int duplicates = 0;

        for (String[] booking : rawBookings) {

            if (booking == null || booking.length < 2) {
                rejected++;
                continue;
            }

            try {
                BusTicket ticket =
                        new BusTicket(booking[0], booking[1]);

                String key = ticket.getPassengerName().toLowerCase()
                        + "|"
                        + ticket.getDestination().toLowerCase();

                if (acceptedBookings.contains(key)) {
                    duplicates++;
                } else {
                    acceptedBookings.add(key);
                    valid++;
                }

            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println(
                "Valid: " + valid
                + " | Rejected: " + rejected
                + " | Duplicates skipped: " + duplicates
        );
    }
}

public class BusTicketBookingValidator {

    public static void main(String[] args) {

        String[][] rawBookings = {
            {"Divya", "Chennai"},
            {"", "Bangalore"},
            {"Ravi123", "Pune"},
            {"Divya", "Chennai"},
            {" ", " "}
        };

        BusTicket.processBatch(rawBookings);
    }
}
