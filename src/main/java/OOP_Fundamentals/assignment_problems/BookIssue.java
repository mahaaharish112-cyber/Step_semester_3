package main.java.OOP_Fundamentals.assignment_problems;

import java.util.Scanner;

public class BookIssue {

    String title;
    String borrowerName;
    int daysOverdue;

    public BookIssue(String title, String borrowerName, int daysOverdue) {
        this.title = title;
        this.borrowerName = borrowerName;
        this.daysOverdue = daysOverdue;
    }

    public double fineAmount() {
        if (daysOverdue > 0) {
            return daysOverdue * 5;
        }
        return 0;
    }

    public boolean isSeverelyOverdue() {
        return daysOverdue > 14;
    }

    public static double totalFineCollected(BookIssue[] issues) {
        double totalFine = 0;

        for (BookIssue issue : issues) {
            totalFine += issue.fineAmount();
        }

        return totalFine;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        BookIssue[] issues = new BookIssue[5];

        for (int i = 0; i < issues.length; i++) {
            System.out.print("Enter book title: ");
            String title = scanner.nextLine();

            System.out.print("Enter borrower name: ");
            String borrowerName = scanner.nextLine();

            System.out.print("Enter days overdue: ");
            int daysOverdue = scanner.nextInt();
            scanner.nextLine();

            issues[i] = new BookIssue(title, borrowerName, daysOverdue);
        }

        System.out.println();

        for (BookIssue issue : issues) {
            if (issue.isSeverelyOverdue()) {
                System.out.println(issue.title + " - "
                        + issue.daysOverdue + " days - Severely overdue");
            } else {
                System.out.println(issue.title + " - "
                        + issue.daysOverdue + " days - OK");
            }
        }

        double totalFine = BookIssue.totalFineCollected(issues);

        System.out.println("Total fine collected: Rs " + totalFine);

        scanner.close();
    }
}
