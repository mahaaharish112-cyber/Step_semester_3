public class Main {

    public static void main(String[] args) {

        RunnerEntry runnerEntry =
            new RunnerEntry("BIB2001", 80, "Open 10K");

        EliteRunnerEntry eliteEntry =
            new EliteRunnerEntry(
                "BIB3001",
                150,
                "Elite Full Marathon",
                500
            );

        RelayTeamEntry relayEntry =
            new RelayTeamEntry(
                "BIB4001",
                300,
                4
            );

        runnerEntry.announce();

        eliteEntry.announce();

        relayEntry.announce();

        System.out.println();

        System.out.println(
            RaceEntry.classifyGeneration(eliteEntry)
        );

        System.out.println(
            RaceEntry.classifyGeneration(relayEntry)
        );

        System.out.println();

        RaceEntry[] entries = {
            runnerEntry,
            eliteEntry,
            relayEntry
        };

        System.out.println(
            RaceEntry.getTotalBalanceDue(entries)
        );
    }
}