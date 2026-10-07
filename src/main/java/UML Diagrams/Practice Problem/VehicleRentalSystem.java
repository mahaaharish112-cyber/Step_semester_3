abstract class Vehicle {
    protected String name;
    protected boolean available;

    public Vehicle(String name) {
        this.name = name;
        this.available = true;
    }

    public boolean isAvailable() {
        return available;
    }

    public void rent() {
        available = false;
    }

    public void returnVehicle() {
        available = true;
    }

    public String getName() {
        return name;
    }

    public abstract double calculateCharge(int days);
}

// Standard Car
class StandardCar extends Vehicle {

    public StandardCar(String name) {
        super(name);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 50;
    }
}

// Luxury Car
class LuxuryCar extends Vehicle {

    public LuxuryCar(String name) {
        super(name);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 100;
    }
}

// SUV
class SUV extends Vehicle {

    public SUV(String name) {
        super(name);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 80;
    }
}

// Rental class
class Rental {
    private Vehicle vehicle;
    private int days;
    private double charge;

    public Rental(Vehicle vehicle, int days) {
        this.vehicle = vehicle;
        this.days = days;
        this.charge = vehicle.calculateCharge(days);
    }

    public void displayRental() {
        System.out.println(
            vehicle.getName() +
            " rented for " + days + " days."
        );

        System.out.printf(
            "Total charge: $%.2f%n",
            charge
        );
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}

// Rental Service
class RentalService {

    public Rental rentVehicle(Vehicle vehicle, int days) {

        if (!vehicle.isAvailable()) {
            System.out.println(
                vehicle.getName() +
                " is currently unavailable."
            );
            return null;
        }

        vehicle.rent();

        Rental rental = new Rental(vehicle, days);
        rental.displayRental();

        return rental;
    }

    public void returnVehicle(Rental rental) {

        if (rental == null) {
            return;
        }

        Vehicle vehicle = rental.getVehicle();

        vehicle.returnVehicle();

        System.out.println(
            vehicle.getName() +
            " returned. Now available."
        );
    }
}

// Main class
public class VehicleRentalSystem {

    public static void main(String[] args) {

        RentalService service = new RentalService();

        Vehicle luxuryCar = new LuxuryCar("Luxury Car A");
        Vehicle standardCar = new StandardCar("Standard Car B");

        // Customer rents Luxury Car A for 3 days
        Rental rental1 = service.rentVehicle(luxuryCar, 3);

        // Customer rents Standard Car B for 5 days
        Rental rental2 = service.rentVehicle(standardCar, 5);

        // Return Luxury Car A
        service.returnVehicle(rental1);

        // Try renting Luxury Car A again
        service.rentVehicle(luxuryCar, 2);
    }
}
