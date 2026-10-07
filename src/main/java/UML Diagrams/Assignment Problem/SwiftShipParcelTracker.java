import java.util.ArrayList;
import java.util.List;

// Shipping Type Interface
interface ShippingType {
    double calculateCost(double weight);
}

// Standard Shipping
class StandardShipping implements ShippingType {
    @Override
    public double calculateCost(double weight) {
        return 40 + (10 * weight);
    }
}

// Express Shipping
class ExpressShipping implements ShippingType {
    @Override
    public double calculateCost(double weight) {
        return 80 + (15 * weight);
    }
}

// Fragile Shipping
class FragileShipping implements ShippingType {
    private ShippingType baseShipping;

    public FragileShipping(ShippingType baseShipping) {
        this.baseShipping = baseShipping;
    }

    @Override
    public double calculateCost(double weight) {
        return baseShipping.calculateCost(weight) + 50;
    }
}

// Notification Channel Interface
interface NotificationChannel {
    void notifyCustomer(String message);
}

// SMS Notification
class SmsChannel implements NotificationChannel {
    @Override
    public void notifyCustomer(String message) {
        System.out.println("SMS: " + message);
    }
}

// Email Notification
class EmailChannel implements NotificationChannel {
    @Override
    public void notifyCustomer(String message) {
        System.out.println("Email: " + message);
    }
}

// Customer Class
class Customer {
    private String name;
    private List<NotificationChannel> channels;

    public Customer(String name) {
        this.name = name;
        channels = new ArrayList<>();
    }

    public void subscribe(NotificationChannel channel) {
        channels.add(channel);
    }

    public void notifyCustomer(String message) {
        for (NotificationChannel channel : channels) {
            channel.notifyCustomer(message);
        }
    }

    public String getName() {
        return name;
    }
}

// Parcel Status
enum ParcelStatus {
    BOOKED,
    PICKED_UP,
    IN_TRANSIT,
    OUT_FOR_DELIVERY,
    DELIVERED
}

// Parcel Class
class Parcel {
    private String parcelId;
    private double weight;
    private ShippingType shippingType;
    private Customer customer;
    private ParcelStatus status;

    public Parcel(String parcelId, double weight,
                  ShippingType shippingType, Customer customer) {
        this.parcelId = parcelId;
        this.weight = weight;
        this.shippingType = shippingType;
        this.customer = customer;
        this.status = ParcelStatus.BOOKED;
    }

    public double calculateCost() {
        return shippingType.calculateCost(weight);
    }

    public boolean updateStatus(ParcelStatus newStatus) {

        boolean valid = false;

        switch (status) {
            case BOOKED:
                valid = newStatus == ParcelStatus.PICKED_UP;
                break;

            case PICKED_UP:
                valid = newStatus == ParcelStatus.IN_TRANSIT;
                break;

            case IN_TRANSIT:
                valid = newStatus == ParcelStatus.OUT_FOR_DELIVERY;
                break;

            case OUT_FOR_DELIVERY:
                valid = newStatus == ParcelStatus.DELIVERED;
                break;

            case DELIVERED:
                valid = false;
                break;
        }

        if (valid) {
            status = newStatus;

            customer.notifyCustomer(
                "Parcel " + parcelId + " status changed to " + status
            );

            return true;
        }

        System.out.println(
            "Invalid status change for " + parcelId +
            ": " + status + " -> " + newStatus
        );

        return false;
    }

    public boolean cancel() {
        if (status == ParcelStatus.BOOKED) {
            System.out.println("Parcel " + parcelId + " cancelled.");
            return true;
        }

        System.out.println(
            "Cancellation failed: Parcel " + parcelId +
            " is already " + status
        );

        return false;
    }
}

// Parcel Service
class ParcelService {

    public void showCost(Parcel parcel) {
        System.out.printf(
            "Parcel cost: ₹%.2f%n",
            parcel.calculateCost()
        );
    }

    public void updateParcelStatus(
            Parcel parcel,
            ParcelStatus status) {

        parcel.updateStatus(status);
    }
}

// Main Class
public class SwiftShipParcelTracker {

    public static void main(String[] args) {

        Customer customer = new Customer("Harish");

        // Subscribe to SMS and Email
        customer.subscribe(new SmsChannel());
        customer.subscribe(new EmailChannel());

        // Express parcel weighing 2 kg
        ShippingType express = new ExpressShipping();

        Parcel parcel = new Parcel(
            "P101",
            2,
            express,
            customer
        );

        ParcelService service = new ParcelService();

        // Calculate cost
        service.showCost(parcel);

        // BOOKED -> PICKED_UP
        service.updateParcelStatus(
            parcel,
            ParcelStatus.PICKED_UP
        );

        // Attempt cancellation after pickup
        parcel.cancel();

        // PICKED_UP -> IN_TRANSIT
        service.updateParcelStatus(
            parcel,
            ParcelStatus.IN_TRANSIT
        );

        // Attempt to directly move to DELIVERED
        service.updateParcelStatus(
            parcel,
            ParcelStatus.DELIVERED
        );

        // IN_TRANSIT -> OUT_FOR_DELIVERY
        service.updateParcelStatus(
            parcel,
            ParcelStatus.OUT_FOR_DELIVERY
        );

        // OUT_FOR_DELIVERY -> DELIVERED
        service.updateParcelStatus(
            parcel,
            ParcelStatus.DELIVERED
        );
    }
}
