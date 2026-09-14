class EventTicket {
    protected double balanceDue;

    public EventTicket(double basePrice) {
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Invalid base price");
        }

        balanceDue = basePrice;
    }

    public double getBalanceDue() {
        return balanceDue;
    }

    public void printTicket() {
        System.out.println("Standard | Balance: " + balanceDue);
    }
}

class WorkshopTicket extends EventTicket {
    private String track;

    public WorkshopTicket(double basePrice, String track) {
        super(basePrice);
        this.track = track;
    }

    public String getTrack() {
        return track;
    }

    @Override
    public void printTicket() {
        System.out.println("Workshop | Track: " + track + " | Balance: " + balanceDue);
    }
}

public class TicketPrinter {

    static String batchPrint(EventTicket[] tickets) {
        StringBuilder result = new StringBuilder();

        for (EventTicket ticket : tickets) {
            ticket.printTicket();

            if (ticket instanceof WorkshopTicket) {
                WorkshopTicket workshop = (WorkshopTicket) ticket;

                result.append("Workshop | Track: ")
                        .append(workshop.getTrack())
                        .append(" | Balance: ")
                        .append(workshop.getBalanceDue())
                        .append(" [Track via downcast: ")
                        .append(workshop.getTrack())
                        .append("] | ");
            } else {
                result.append("Standard | Balance: ")
                        .append(ticket.getBalanceDue())
                        .append(" | ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        EventTicket[] tickets = {
                new EventTicket(500),
                new WorkshopTicket(1200, "AI/ML")
        };

        System.out.println(batchPrint(tickets));
    }
}