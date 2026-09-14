class EventTicket {
    protected String attendeeId;
    protected double basePrice;
    protected double balanceDue;

    public EventTicket(String attendeeId, double basePrice) {
        if (attendeeId == null || attendeeId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid attendee ID");
        }

        if (basePrice <= 0) {
            throw new IllegalArgumentException("Invalid base price");
        }

        this.attendeeId = attendeeId;
        this.basePrice = basePrice;
        this.balanceDue = basePrice;
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

    public double getBalanceDue() {
        return balanceDue;
    }

    public void printTicket() {
        System.out.println("Standard Event Ticket | Balance Due: " + balanceDue);
    }
}

class WorkshopTicket extends EventTicket {
    protected String track;

    public WorkshopTicket(String attendeeId, double basePrice, String track) {
        super(attendeeId, basePrice);
        this.track = track;
    }

    @Override
    public void printTicket() {
        System.out.println("Workshop Ticket | Track: " + track + " | Balance Due: " + balanceDue);
    }
}

class PremiumWorkshopTicket extends WorkshopTicket {
    private double kitFee;

    public PremiumWorkshopTicket(String attendeeId, double basePrice, String track, double kitFee) {
        super(attendeeId, basePrice, track);

        if (kitFee < 0) {
            throw new IllegalArgumentException("Invalid kit fee");
        }

        this.kitFee = kitFee;
        this.balanceDue += kitFee;
    }

    @Override
    public void printTicket() {
        System.out.println("Premium Workshop Ticket | Track: " + track +
                " | Kit Fee: " + kitFee +
                " | Balance Due: " + balanceDue);
    }
}

class HackathonTicket extends EventTicket {
    private String teamName;

    public HackathonTicket(String attendeeId, double basePrice, String teamName) {
        super(attendeeId, basePrice);
        this.teamName = teamName;
    }

    @Override
    public void printTicket() {
        System.out.println("Hackathon Ticket | Team: " + teamName + " | Balance Due: " + balanceDue);
    }
}

public class PremiumWorkshoTicket {

    static String classifyGeneration(EventTicket ticket) {
        if (ticket instanceof PremiumWorkshopTicket) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (ticket instanceof HackathonTicket) {
            return "Hierarchical sibling (independent branch)";
        }

        if (ticket instanceof WorkshopTicket) {
            return "Direct subclass";
        }

        return "Base class";
    }

    static double getTotalBalanceDue(EventTicket[] tickets) {
        double total = 0;

        for (EventTicket ticket : tickets) {
            total += ticket.getBalanceDue();
        }

        return total;
    }

    public static void main(String[] args) {
        PremiumWorkshopTicket premium =
                new PremiumWorkshopTicket("STU1", 1700, "Cloud Native", 300);

        HackathonTicket hackathon =
                new HackathonTicket("STU2", 800, "Byte Force");

        System.out.println(classifyGeneration(premium));
        System.out.println(classifyGeneration(hackathon));

        EventTicket[] tickets = {
                new EventTicket("STU3", 500),
                new WorkshopTicket("STU4", 1200, "AI/ML"),
                premium,
                hackathon
        };

        System.out.println(getTotalBalanceDue(tickets));
    }
}