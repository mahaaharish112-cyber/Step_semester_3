package OOP_Fundamentals.class_problems;

import java.util.Scanner;

class FeeAccount {

    private String regNo;
    private double totalFee;
    private double amountPaid;

    public FeeAccount(String regNo, double totalFee, double amountPaid) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = amountPaid;
    }

    public void pay(double amount) {
        if (amount > 0) {
            amountPaid += amount;
        }
    }

    public double getDue() {
        return totalFee - amountPaid;
    }
}

class HostelFeeAccount extends FeeAccount {

    public HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
        super(regNo, totalFee, amountPaid);
    }

    public void payInTwoInstallments(double amount) {
        if (amount > 0) {
            pay(amount / 2);
            pay(amount / 2);
        }
    }
}

class ScholarshipFeeAccount extends FeeAccount {

    private double scholarshipPercent;

    public ScholarshipFeeAccount(String regNo, double totalFee, double amountPaid,
                                  double scholarshipPercent) {
        super(regNo, totalFee, amountPaid);

        if (scholarshipPercent >= 0 && scholarshipPercent <= 100) {
            this.scholarshipPercent = scholarshipPercent;
        } else {
            this.scholarshipPercent = 0;
        }
    }

    public double effectiveDue() {
        return getDue() - (getDue() * scholarshipPercent / 100);
    }
}

public class FeeAccountDemo {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Plain account register number: ");
        String plainRegNo = scanner.nextLine();

        System.out.print("Enter Plain total fee: ");
        double plainTotalFee = scanner.nextDouble();

        System.out.print("Enter Plain amount paid: ");
        double plainPaid = scanner.nextDouble();

        FeeAccount plain = new FeeAccount(plainRegNo, plainTotalFee, plainPaid);

        System.out.print("Enter Hostel account register number: ");
        scanner.nextLine();
        String hostelRegNo = scanner.nextLine();

        System.out.print("Enter Hostel total fee: ");
        double hostelTotalFee = scanner.nextDouble();

        System.out.print("Enter Hostel amount to pay: ");
        double hostelPayment = scanner.nextDouble();

        HostelFeeAccount hostel =
                new HostelFeeAccount(hostelRegNo, hostelTotalFee, 0);

        hostel.payInTwoInstallments(hostelPayment);

        System.out.print("Enter Scholarship account register number: ");
        scanner.nextLine();
        String scholarshipRegNo = scanner.nextLine();

        System.out.print("Enter Scholarship total fee: ");
        double scholarshipTotalFee = scanner.nextDouble();

        System.out.print("Enter Scholarship amount paid: ");
        double scholarshipPaid = scanner.nextDouble();

        System.out.print("Enter Scholarship percentage: ");
        double scholarshipPercent = scanner.nextDouble();

        ScholarshipFeeAccount scholarship =
                new ScholarshipFeeAccount(
                        scholarshipRegNo,
                        scholarshipTotalFee,
                        scholarshipPaid,
                        scholarshipPercent
                );

        FeeAccount[] accounts = {plain, hostel, scholarship};

        for (FeeAccount account : accounts) {

            if (account instanceof ScholarshipFeeAccount) {
                ScholarshipFeeAccount scholarshipAccount =
                        (ScholarshipFeeAccount) account;

                System.out.println(
                        "Scholarship account effective due: Rs "
                                + scholarshipAccount.effectiveDue()
                );

            } else if (account instanceof HostelFeeAccount) {
                System.out.println(
                        "Hostel account due: Rs "
                                + account.getDue()
                );

            } else {
                System.out.println(
                        "Plain account due: Rs "
                                + account.getDue()
                );
            }
        }

        scanner.close();
    }
}
