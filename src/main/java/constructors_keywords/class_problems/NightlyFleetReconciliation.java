package constructors_keywords.class_problems;

public class NightlyFleetReconciliation {

    static class BusTicketAccount {
        private static double minimumPenaltyPercent;
        private String bookingId;
        private double ticketFare;

        static {
            minimumPenaltyPercent = 1.0;
        }

        public BusTicketAccount(String bookingId, double ticketFare) {
            if (bookingId == null || bookingId.trim().isEmpty()) {
                throw new IllegalArgumentException("Invalid booking ID");
            }

            if (ticketFare < 0) {
                throw new IllegalArgumentException("Ticket fare cannot be negative");
            }

            this.bookingId = bookingId.trim();
            this.ticketFare = ticketFare;
        }

        public BusTicketAccount(String bookingId) {
            this(bookingId, 0.0);
        }

        public final double calculatePenalty(int minutesLate) {
            if (minutesLate < 0) {
                throw new IllegalArgumentException("Minutes late cannot be negative");
            }

            if (minutesLate == 0) {
                return 0.0;
            }

            int firstTier = Math.min(minutesLate, 5);
            int secondTier = Math.min(Math.max(minutesLate - 5, 0), 10);
            int thirdTier = Math.max(minutesLate - 15, 0);

            double penalty =
                    ticketFare * firstTier * 0.005
                    + ticketFare * secondTier * 0.01
                    + ticketFare * thirdTier * 0.02;

            double minimumPenalty =
                    ticketFare * minimumPenaltyPercent / 100.0;

            return Math.max(penalty, minimumPenalty);
        }

        public void processAccount(double amount, int minutesLate) {
            if (amount < 0) {
                throw new IllegalArgumentException("Amount cannot be negative");
            }

            double penalty = calculatePenalty(minutesLate);
            System.out.println(
                    bookingId + " regular settled: Amount = "
                    + amount + ", Penalty = " + penalty
            );
        }
    }

    static class SleeperTicketAccount extends BusTicketAccount {

        public SleeperTicketAccount(String bookingId, double ticketFare) {
            super(bookingId, ticketFare);
        }

        public SleeperTicketAccount(String bookingId) {
            super(bookingId);
        }

        @Override
        public void processAccount(double amount, int minutesLate) {
            if (amount < 0) {
                throw new IllegalArgumentException("Amount cannot be negative");
            }

            double sleeperSettlement = amount * 1.05;
            double penalty = calculatePenalty(minutesLate);

            System.out.println(
                    "Sleeper settled: Amount = "
                    + sleeperSettlement + ", Penalty = " + penalty
            );
        }
    }

    static class FleetProcessor {

        private int processed;
        private int nullSkipped;
        private int sleeperCount;
        private int regularCount;
        private double grandTotalPenalties;

        public void processAccount(
                BusTicketAccount account,
                double amount,
                int minutesLate) {

            if (account == null) {
                nullSkipped++;
                return;
            }

            try {
                account.processAccount(amount, minutesLate);

                double penalty = account.calculatePenalty(minutesLate);
                grandTotalPenalties += penalty;
                processed++;

                if (account instanceof SleeperTicketAccount) {
                    sleeperCount++;
                } else {
                    regularCount++;
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Invalid account skipped");
            }
        }

        public static void processBatch(
                BusTicketAccount[] accounts,
                double[] amounts,
                int[] minutesLateArray) {

            if (accounts == null || amounts == null || minutesLateArray == null) {
                throw new IllegalArgumentException("Batch arrays cannot be null");
            }

            if (accounts.length != amounts.length
                    || accounts.length != minutesLateArray.length) {
                throw new IllegalArgumentException(
                        "Batch rejected: array lengths do not match"
                );
            }

            FleetProcessor processor = new FleetProcessor();

            for (int i = 0; i < accounts.length; i++) {
                processor.processAccount(
                        accounts[i],
                        amounts[i],
                        minutesLateArray[i]
                );
            }

            System.out.println();
            System.out.println("Batch Summary");
            System.out.println("Processed: " + processor.processed);
            System.out.println("Null skipped: " + processor.nullSkipped);
            System.out.println("Sleeper: " + processor.sleeperCount);
            System.out.println("Regular: " + processor.regularCount);
            System.out.println(
                    "Grand total penalties = "
                    + processor.grandTotalPenalties
            );
        }
    }

    public static void main(String[] args) {

        BusTicketAccount[] accounts = {
            new SleeperTicketAccount("BK001", 2000),
            null,
            new BusTicketAccount("BK002", 1200)
        };

        double[] amounts = {
            1200,
            900,
            700
        };

        int[] minutesLateArray = {
            10,
            5,
            0
        };

        FleetProcessor.processBatch(
                accounts,
                amounts,
                minutesLateArray
        );
    }
}
