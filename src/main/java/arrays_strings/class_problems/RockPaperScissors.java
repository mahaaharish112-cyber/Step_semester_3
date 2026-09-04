
import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    public static String playRound(String playerMove, String computerMove) {

        if (playerMove.equals(computerMove)) {
            return "Draw";
        }

        if ((playerMove.equals("Rock") && computerMove.equals("Scissors")) ||
            (playerMove.equals("Paper") && computerMove.equals("Rock")) ||
            (playerMove.equals("Scissors") && computerMove.equals("Paper"))) {
            return "Player Wins";
        }

        return "Computer Wins";
    }

    public static String getComputerMove(Random random) {

        int choice = random.nextInt(3);

        if (choice == 0) {
            return "Rock";
        } else if (choice == 1) {
            return "Paper";
        } else {
            return "Scissors";
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int rounds = 5;

        String[] playerMoves = new String[rounds];
        String[] computerMoves = new String[rounds];
        String[] results = new String[rounds];

        int wins = 0;
        int losses = 0;
        int draws = 0;

        System.out.println("======================================");
        System.out.println("       ROCK-PAPER-SCISSORS GAME");
        System.out.println("======================================");

        for (int i = 0; i < rounds; i++) {

            System.out.println("\nRound " + (i + 1));
            System.out.println("Choose your move:");
            System.out.println("1. Rock");
            System.out.println("2. Paper");
            System.out.println("3. Scissors");

            int choice;

            while (true) {
                System.out.print("Enter your choice (1-3): ");

                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();

                    if (choice >= 1 && choice <= 3) {
                        break;
                    }
                } else {
                    scanner.next();
                }

                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }

            String playerMove;

            if (choice == 1) {
                playerMove = "Rock";
            } else if (choice == 2) {
                playerMove = "Paper";
            } else {
                playerMove = "Scissors";
            }

            String computerMove = getComputerMove(random);

            String result = playRound(playerMove, computerMove);

            playerMoves[i] = playerMove;
            computerMoves[i] = computerMove;
            results[i] = result;

            if (result.equals("Player Wins")) {
                wins++;
            } else if (result.equals("Computer Wins")) {
                losses++;
            } else {
                draws++;
            }

            System.out.println("\nPlayer Move   : " + playerMove);
            System.out.println("Computer Move : " + computerMove);
            System.out.println("Result        : " + result);
        }

        double winPercentage = (wins * 100.0) / rounds;

        System.out.println("\n\n======================================");
        System.out.println("           FINAL SUMMARY");
        System.out.println("======================================");

        System.out.printf("%-8s %-15s %-17s %-15s%n",
                "Round", "Player Move", "Computer Move", "Result");

        System.out.println("------------------------------------------------------------");

        for (int i = 0; i < rounds; i++) {
            System.out.printf("%-8d %-15s %-17s %-15s%n",
                    (i + 1),
                    playerMoves[i],
                    computerMoves[i],
                    results[i]);
        }

        System.out.println("------------------------------------------------------------");

        System.out.println("Wins   : " + wins);
        System.out.println("Losses : " + losses);
        System.out.println("Draws  : " + draws);
        System.out.printf("Win %%  : %.1f%%%n", winPercentage);

        System.out.println("======================================");

        scanner.close();
    }
}

