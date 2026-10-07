import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

abstract class Room {
    protected String roomNumber;
    protected String category;

    public Room(String roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public abstract double calculatePrice(long days);
}

class StandardRoom extends Room {

    public StandardRoom(String roomNumber) {
        super(roomNumber, "Standard");
    }

    @Override
    public double calculatePrice(long days) {
        return days * 150;
    }
}

class DeluxeRoom extends Room {

    public DeluxeRoom(String roomNumber) {
        super(roomNumber, "Deluxe");
    }

    @Override
    public double calculatePrice(long days) {
        return days * 200;
    }
}

class Suite extends Room {

    public Suite(String roomNumber) {
        super(roomNumber, "Suite");
    }

    @Override
    public double calculatePrice(long days) {
        return days * 300;
    }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Reservation {
    private Room room;
    private Customer customer;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;

    public Reservation(Room room, Customer customer,
                       LocalDate startDate, LocalDate endDate) {

        this.room = room;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.active = true;
    }

    public boolean overlaps(LocalDate start, LocalDate end) {

        return active &&
               start.isBefore(endDate) &&
               end.isAfter(startDate);
    }

    public void cancel() {
        active = false;
    }

    public Room getRoom() {
        return room;
    }

    public double getPrice() {
        long days =
            java.time.temporal.ChronoUnit.DAYS.between(
                startDate, endDate
            );

        return room.calculatePrice(days);
    }

    public void display() {
        System.out.println(
            room.category + " Room " +
            room.roomNumber +
            " booked from " + startDate +
            " to " + endDate + "."
        );

        System.out.printf(
            "Total price: $%.2f%n",
            getPrice()
        );
    }
}

class BookingManager {

    private List<Room> rooms = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Reservation bookRoom(
            Room room,
            Customer customer,
            LocalDate startDate,
            LocalDate endDate) {

        for (Reservation reservation : reservations) {

            if (reservation.getRoom() == room &&
                reservation.overlaps(startDate, endDate)) {

                System.out.println(
                    "Booking failed: " +
                    room.category + " Room " +
                    room.roomNumber +
                    " is not available for " +
                    startDate + " to " +
                    endDate + "."
                );

                return null;
            }
        }

        Reservation reservation =
            new Reservation(
                room,
                customer,
                startDate,
                endDate
            );

        reservations.add(reservation);

        reservation.display();

        return reservation;
    }

    public void cancelReservation(Reservation reservation) {

        if (reservation != null) {
            reservation.cancel();

            System.out.println(
                "Reservation for " +
                reservation.getRoom().category +
                " Room " +
                reservation.getRoom().roomNumber +
                " cancelled successfully."
            );
        }
    }
}

public class HotelBookingSystem {

    public static void main(String[] args) {

        BookingManager manager = new BookingManager();

        Room deluxeRoom = new DeluxeRoom("101");
        Room standardRoom = new StandardRoom("205");

        manager.addRoom(deluxeRoom);
        manager.addRoom(standardRoom);

        Customer customer1 =
            new Customer("John");

        Customer customer2 =
            new Customer("Harish");

        // Booking 1
        Reservation reservation1 =
            manager.bookRoom(
                deluxeRoom,
                customer1,
                LocalDate.of(2024, 12, 1),
                LocalDate.of(2024, 12, 5)
            );

        // Booking 2
        Reservation reservation2 =
            manager.bookRoom(
                standardRoom,
                customer2,
                LocalDate.of(2024, 12, 3),
                LocalDate.of(2024, 12, 7)
            );

        // Overlapping booking
        Reservation reservation3 =
            manager.bookRoom(
                deluxeRoom,
                customer2,
                LocalDate.of(2024, 12, 3),
                LocalDate.of(2024, 12, 7)
            );

        // Cancel first reservation
        manager.cancelReservation(reservation1);
    }
}
