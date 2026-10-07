import java.util.ArrayList;
import java.util.List;

// Food Item
class FoodItem {
    private String name;
    private double price;

    public FoodItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

// Line Item
class LineItem {
    private FoodItem item;
    private int quantity;

    public LineItem(FoodItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public double getTotal() {
        return item.getPrice() * quantity;
    }

    public String getDetails() {
        return item.getName() + " (Qty " + quantity + ")";
    }
}

// Restaurant
class Restaurant {
    private String name;
    private List<FoodItem> menu = new ArrayList<>();

    public Restaurant(String name) {
        this.name = name;
    }

    public void addFoodItem(FoodItem item) {
        menu.add(item);
    }

    public String getName() {
        return name;
    }
}

// Customer
class Customer {
    private String name;
    private List<Order> orders = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public String getName() {
        return name;
    }
}

// Payment Interface
interface IPaymentMethod {
    String getName();

    boolean pay(double amount);
}

// Credit Card Payment
class CreditCardPayment implements IPaymentMethod {

    @Override
    public String getName() {
        return "Credit Card";
    }

    @Override
    public boolean pay(double amount) {
        return true;
    }
}

// Digital Wallet Payment
class DigitalWalletPayment implements IPaymentMethod {

    @Override
    public String getName() {
        return "Digital Wallet";
    }

    @Override
    public boolean pay(double amount) {
        return false;
    }
}

// Cash on Delivery Payment
class CashOnDeliveryPayment implements IPaymentMethod {

    @Override
    public String getName() {
        return "Cash on Delivery";
    }

    @Override
    public boolean pay(double amount) {
        return true;
    }
}

// Notification Service
class NotificationService {

    public static void notify(Customer customer, String message) {
        System.out.println("Notification: " + message);
    }
}

// Order
class Order {
    private static int nextOrderId = 123;

    private int orderId;
    private Customer customer;
    private Restaurant restaurant;
    private List<LineItem> items = new ArrayList<>();

    private OrderStatus status;

    enum OrderStatus {
        CREATED,
        PAID,
        PENDING_PAYMENT
    }

    public Order(Customer customer, Restaurant restaurant) {
        this.orderId = nextOrderId++;
        this.customer = customer;
        this.restaurant = restaurant;
        this.status = OrderStatus.CREATED;
    }

    public void addItem(FoodItem item, int quantity) {
        if (quantity <= 0) {
            System.out.println("Quantity must be greater than zero.");
            return;
        }

        items.add(new LineItem(item, quantity));

        System.out.println(
            "Added " + item.getName() + " (Qty " + quantity + ")"
        );
    }

    private double calculateTotal() {
        double total = 0;

        for (LineItem item : items) {
            total += item.getTotal();
        }

        return total;
    }

    public void placeOrder(IPaymentMethod paymentMethod) {

        if (items.isEmpty()) {
            System.out.println(
                "Cannot place order: Order must contain at least one item."
            );
            return;
        }

        System.out.println("Order placed successfully.");

        double total = calculateTotal();

        boolean paymentSuccessful = paymentMethod.pay(total);

        if (paymentSuccessful) {

            status = OrderStatus.PAID;

            System.out.println(
                "Payment via " + paymentMethod.getName() + " successful."
            );

            System.out.println("Order status: Paid.");

            NotificationService.notify(
                customer,
                "Order #" + orderId + " placed and paid."
            );

        } else {

            status = OrderStatus.PENDING_PAYMENT;

            System.out.println(
                "Payment via " + paymentMethod.getName() + " failed."
            );

            System.out.println("Order status: Pending Payment.");

            NotificationService.notify(
                customer,
                "Order #" + orderId + " placed, awaiting payment."
            );
        }
    }

    public int getOrderId() {
        return orderId;
    }
}

// Main Class
public class FoodOrderSystem {

    public static void main(String[] args) {

        Customer customer = new Customer("Harish");

        Restaurant restaurant = new Restaurant("Food Corner");

        FoodItem pizza = new FoodItem("Pizza", 250);
        FoodItem soda = new FoodItem("Soda", 50);
        FoodItem burger = new FoodItem("Burger", 150);

        restaurant.addFoodItem(pizza);
        restaurant.addFoodItem(soda);
        restaurant.addFoodItem(burger);

        // First Order
        Order order1 = new Order(customer, restaurant);

        order1.addItem(pizza, 2);
        order1.addItem(soda, 1);

        customer.addOrder(order1);

        // Empty order attempt
        Order emptyOrder = new Order(customer, restaurant);
        emptyOrder.placeOrder(new CreditCardPayment());

        // Place first order
        order1.placeOrder(new CreditCardPayment());

        // Second Order
        Order order2 = new Order(customer, restaurant);

        order2.addItem(burger, 1);

        customer.addOrder(order2);

        // Payment failure
        order2.placeOrder(new DigitalWalletPayment());
    }
}
