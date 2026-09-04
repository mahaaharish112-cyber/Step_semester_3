package OOP_Fundamentals.class_problems;

import java.util.Scanner;

public class SrmStudent {

    private String name;
    private String regNo;
    private int attendance;

    public SrmStudent(String name, String regNo, int attendance) {
        this.name = name;
        this.regNo = regNo;
        this.attendance = attendance;
    }

    public void addAttendanceUpdate(int newAttendance) {
        this.attendance = newAttendance;
    }

    public boolean isEligible() {
        return attendance >= 75;
    }

    public static double classAverage(SrmStudent[] students) {
        int total = 0;

        for (SrmStudent student : students) {
            total += student.attendance;
        }

        return (double) total / students.length;
    }

    public String getName() {
        return name;
    }

    public int getAttendance() {
        return attendance;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        SrmStudent[] students = new SrmStudent[5];

        for (int i = 0; i < students.length; i++) {
            System.out.println("Enter details for Student " + (i + 1));

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Register Number: ");
            String regNo = scanner.nextLine();

            System.out.print("Attendance: ");
            int attendance = scanner.nextInt();
            scanner.nextLine();

            students[i] = new SrmStudent(name, regNo, attendance);
            System.out.println();
        }

        System.out.println("Attendance Report");

        for (SrmStudent student : students) {
            String status = student.isEligible() ? "Eligible" : "Detained";

            System.out.println(
                    student.getName() + " - "
                    + student.getAttendance() + "% - "
                    + status
            );
        }

        double average = SrmStudent.classAverage(students);

        System.out.printf("Class average: %.1f%%%n", average);

        scanner.close();
    }
}
