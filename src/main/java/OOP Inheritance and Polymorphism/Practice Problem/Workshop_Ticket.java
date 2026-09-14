class EventTicket {
    protected double balanceDue;
    private double[] lateFeeHistory;
    private int lateFeeCount;

    public EventTicket(String attendeeId, double basePrice) {
        if (attendeeId == null || attendeeId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid attendee ID");
        }

        if (basePrice <= 0) {
            throw new IllegalArgumentException("Invalid base price");
        }

        balanceDue = basePrice;
        lateFeeHistory = new double[10];
        lateFeeCount = 0;
    }

    public EventTicket(double basePrice) {
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Invalid base price");
        }

        balanceDue = basePrice;
        lateFeeHistory = new double[10];
        lateFeeCount = 0;
    }

    public void pay(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid payment");
        }

        balanceDue -= amount;

        if (balanceDue < 0) {
            balanceDue = 0;
        }
    }

    protected void applyLateFee(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid late fee");
        }

        if (lateFeeCount >= 10) {
            throw new IllegalStateException("Late fee limit reached");
        }

        balanceDue += amount;
        lateFeeHistory[lateFeeCount] = amount;
        lateFeeCount++;
    }

    public double getBalanceDue() {
        return balanceDue;
    }

    public double[] getLateFeeHistory() {
        double[] history = new double[lateFeeCount];

        for (int i = 0; i < lateFeeCount; i++) {
            history[i] = lateFeeHistory[i];
        }

        return history;
    }
}

class WorkshopTicket extends EventTicket {

    public WorkshopTicket(String attendeeId, double basePrice, String track) {
        super(attendeeId, basePrice);
    }

    public WorkshopTicket(double basePrice) {
        super(basePrice);
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}

public class Workshop_Ticket {
    public static void main(String[] args) {
        WorkshopTicket w = new WorkshopTicket(1200);

        w.pay(1200);
        w.applyLateFee(100);

        System.out.println(w.getBalanceDue());

        double[] history = w.getLateFeeHistory();

        for (double fee : history) {
            System.out.println(fee);
        }

        history[0] = 9999;

        System.out.println(w.getLateFeeHistory()[0]);
    }
}