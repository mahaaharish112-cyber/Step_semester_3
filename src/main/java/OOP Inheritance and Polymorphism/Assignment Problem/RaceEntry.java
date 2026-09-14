public class RaceEntry {

    private String bibNumber;
    private double entryFee;
    private double amountPaid;

    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid bib number");
        }

        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        this.amountPaid = 0;
    }

    public void pay(double amount) {
        amountPaid += amount;
    }

    public double getBalanceDue() {
        return entryFee - amountPaid;
    }

    public String getBibNumber() {
        return bibNumber;
    }

    public double getEntryFee() {
        return entryFee;
    }

    public void announce() {
        System.out.println(
            "Race Entry | Bib: " + bibNumber +
            " | Balance: " + getBalanceDue()
        );
    }

    public static String registerBatch(String[] bibNumbers, double entryFee) {
        int registered = 0;
        int rejected = 0;

        for (String bibNumber : bibNumbers) {
            try {
                new RaceEntry(bibNumber, entryFee);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered + " | Rejected: " + rejected;
    }

    public static String classifyGeneration(RaceEntry entry) {
        if (entry instanceof EliteRunnerEntry) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (entry instanceof RelayTeamEntry) {
            return "Hierarchical sibling (independent branch)";
        }

        return "Base RaceEntry";
    }

    public static double getTotalBalanceDue(RaceEntry[] entries) {
        double total = 0;

        for (RaceEntry entry : entries) {
            total += entry.getBalanceDue();
        }

        return total;
    }
}