package constructors_keywords.class_problems;

public final class BoardingPenaltyCalculator {

    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(double minimumPenaltyPercent) {
        if (minimumPenaltyPercent < 0) {
            throw new IllegalArgumentException(
                    "Minimum penalty percent cannot be negative"
            );
        }

        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    public final double calculatePenalty(double ticketFare, int minutesLate) {

        if (ticketFare < 0 || minutesLate < 0) {
            throw new IllegalArgumentException(
                    "Ticket fare and minutes late cannot be negative"
            );
        }

        if (minutesLate == 0) {
            return 0.0;
        }

        int firstTierMinutes = Math.min(minutesLate, 5);
        int secondTierMinutes = Math.min(Math.max(minutesLate - 5, 0), 10);
        int thirdTierMinutes = Math.max(minutesLate - 15, 0);

        double penalty =
                ticketFare * firstTierMinutes * 0.005
                + ticketFare * secondTierMinutes * 0.01
                + ticketFare * thirdTierMinutes * 0.02;

        double minimumPenalty =
                ticketFare * minimumPenaltyPercent / 100.0;

        return Math.max(penalty, minimumPenalty);
    }

    public static void main(String[] args) {

        BoardingPenaltyCalculator calculator =
                new BoardingPenaltyCalculator(1.0);

        System.out.println(
                "Penalty for 0 minutes: Rs "
                + calculator.calculatePenalty(1000, 0)
        );

        System.out.println(
                "Penalty for 1 minute: Rs "
                + calculator.calculatePenalty(1000, 1)
        );

        System.out.println(
                "Penalty for 16 minutes: Rs "
                + calculator.calculatePenalty(1000, 16)
        );
    }
}
