import java.util.ArrayList;
import java.util.List;

// Pricing Plan Interface
interface PricingPlan {
    double calculatePrice(double originalPrice);
}

// Day Scholar Plan
class DayScholarPlan implements PricingPlan {

    @Override
    public double calculatePrice(double originalPrice) {
        return originalPrice;
    }
}

// Hosteller Plan
class HostellerPlan implements PricingPlan {

    @Override
    public double calculatePrice(double originalPrice) {
        return originalPrice * 0.90;
    }
}

// Staff Plan
class StaffPlan implements PricingPlan {

    @Override
    public double calculatePrice(double originalPrice) {
        return originalPrice * 0.80;
    }
}

// Transaction Class
class Transaction {
    private double amount;
    private String description;

    public Transaction(double amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
}

// Smart Card Class
class SmartCard {

    private String cardId;
    private PricingPlan pricingPlan;
    private List<Transaction> transactions;

    private boolean blocked;
    private boolean refundUsed;

    public SmartCard(
            String cardId,
            PricingPlan pricingPlan) {

        this.cardId = cardId;
        this.pricingPlan = pricingPlan;
        this.transactions = new ArrayList<>();

        blocked = false;
        refundUsed = false;
    }

    // Calculate balance from transactions
    public double getBalance() {

        double balance = 0;

        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }

        return balance;
    }

    // Top-up
    public void topUp(double amount) {

        if (blocked) {
            System.out.println(
                "Top-up rejected: Card is blocked."
            );
            return;
        }

        if (amount < 100) {
            System.out.println(
                "Top-up rejected: Minimum top-up is ₹100."
            );
            return;
        }

        if (getBalance() + amount > 5000) {
            System.out.println(
                "Top-up rejected: Maximum balance is ₹5000."
            );
            return;
        }

        transactions.add(
            new Transaction(amount, "Top-up")
        );

        System.out.printf(
            "Top-up successful: ₹%.2f%n",
            amount
        );
    }

    // Purchase
    public void purchase(
            String item,
            double originalPrice) {

        if (blocked) {
            System.out.println(
                "Purchase rejected: Card is blocked."
            );
            return;
        }

        double chargedPrice =
            pricingPlan.calculatePrice(originalPrice);

        if (getBalance() < chargedPrice) {
            System.out.printf(
                "Purchase failed: Need ₹%.2f but balance is ₹%.2f%n",
                chargedPrice,
                getBalance()
            );
            return;
        }

        transactions.add(
            new Transaction(
                -chargedPrice,
                item
            )
        );

        System.out.printf(
            "%s purchased for ₹%.2f%n",
            item,
            chargedPrice
        );
    }

    // Refund
    public void refund(double amount) {

        if (blocked) {
            System.out.println(
                "Refund rejected: Card is blocked."
            );
            return;
        }

        if (refundUsed) {
            System.out.println(
                "Refund rejected: Refund can be used only once."
            );
            return;
        }

        if (amount <= 0) {
            System.out.println(
                "Refund rejected: Invalid amount."
            );
            return;
        }

        transactions.add(
            new Transaction(
                amount,
                "Refund"
            )
        );

        refundUsed = true;

        System.out.printf(
            "Refund successful: ₹%.2f%n",
            amount
        );
    }

    // Block card
    public void block() {
        blocked = true;
        System.out.println("Card blocked.");
    }

    // Unblock card
    public void unblock() {
        blocked = false;
        System.out.println("Card unblocked.");
    }

    // Display statement
    public void showStatement() {

        System.out.println("\nTransaction Statement:");

        for (Transaction transaction : transactions) {

            System.out.printf(
                "%s : %+.2f%n",
                transaction.getDescription(),
                transaction.getAmount()
            );
        }

        System.out.printf(
            "Current Balance: ₹%.2f%n",
            getBalance()
        );
    }
}

// Main Class
public class CampusCanteenSmartCard {

    public static void main(String[] args) {

        // C-2045 is a Hosteller card
        SmartCard card =
            new SmartCard(
                "C-2045",
                new HostellerPlan()
            );

        // Top-up ₹500
        card.topUp(500);

        // Veg Thali ₹120 -> 10% discount = ₹108
        card.purchase(
            "Veg Thali",
            120
        );

        // Cold Coffee ₹60 -> 10% discount = ₹54
        card.purchase(
            "Cold Coffee",
            60
        );

        // Attempt ₹400 purchase
        // Required amount = ₹360
        card.purchase(
            "Meal Combo",
            400
        );

        // Refund ₹108
        card.refund(108);

        // Second refund should fail
        card.refund(54);

        // Display statement
        card.showStatement();
    }
}
