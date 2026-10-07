import java.util.LinkedList;
import java.util.Queue;

// Credit Policy Interface
interface CreditPolicy {
    int getCreditLimit();
}

// Regular Student Policy
class RegularCreditPolicy implements CreditPolicy {
    @Override
    public int getCreditLimit() {
        return 24;
    }
}

// Honors Student Policy
class HonorsCreditPolicy implements CreditPolicy {
    @Override
    public int getCreditLimit() {
        return 28;
    }
}

// Exchange Student Policy
class ExchangeCreditPolicy implements CreditPolicy {
    @Override
    public int getCreditLimit() {
        return 20;
    }
}

// Student Class
class Student {
    private String name;
    private CreditPolicy creditPolicy;
    private int currentCredits;

    public Student(String name, CreditPolicy creditPolicy, int currentCredits) {
        this.name = name;
        this.creditPolicy = creditPolicy;
        this.currentCredits = currentCredits;
    }

    public boolean canAddCredits(int credits) {
        return currentCredits + credits <= creditPolicy.getCreditLimit();
    }

    public void addCredits(int credits) {
        currentCredits += credits;
    }

    public String getName() {
        return name;
    }

    public int getCurrentCredits() {
        return currentCredits;
    }

    public int getCreditLimit() {
        return creditPolicy.getCreditLimit();
    }
}

// Elective Class
class Elective {
    private String name;
    private int credits;
    private int capacity;

    private Queue<Student> waitlist;
    private Queue<Student> enrolledStudents;

    public Elective(String name, int credits, int capacity) {
        this.name = name;
        this.credits = credits;
        this.capacity = capacity;

        waitlist = new LinkedList<>();
        enrolledStudents = new LinkedList<>();
    }

    public boolean isFull() {
        return enrolledStudents.size() >= capacity;
    }

    public boolean isEnrolled(Student student) {
        return enrolledStudents.contains(student);
    }

    public boolean isWaiting(Student student) {
        return waitlist.contains(student);
    }

    public void enroll(Student student) {
        if (isEnrolled(student) || isWaiting(student)) {
            System.out.println(
                student.getName() +
                " cannot enroll/wait twice."
            );
            return;
        }

        // Credit check before seat availability
        if (!student.canAddCredits(credits)) {
            System.out.println(
                "Enrollment failed for " +
                student.getName() +
                ": credit limit exceeded."
            );
            return;
        }

        if (!isFull()) {
            enrolledStudents.add(student);
            student.addCredits(credits);

            System.out.println(
                student.getName() +
                " enrolled in " + name +
                ". Credits: " +
                student.getCurrentCredits() + "/" +
                student.getCreditLimit()
            );
        } else {
            waitlist.add(student);

            System.out.println(
                student.getName() +
                " added to waitlist for " + name
            );
        }
    }

    public void drop(Student student) {

        if (!enrolledStudents.contains(student)) {
            System.out.println(
                student.getName() +
                " is not enrolled in " + name
            );
            return;
        }

        enrolledStudents.remove(student);

        System.out.println(
            student.getName() +
            " dropped " + name
        );

        // Automatically promote first eligible student
        promoteNextStudent();
    }

    private void promoteNextStudent() {

        int size = waitlist.size();

        for (int i = 0; i < size; i++) {

            Student student = waitlist.poll();

            // Credit limit checked again during promotion
            if (student.canAddCredits(credits)) {

                enrolledStudents.add(student);
                student.addCredits(credits);

                System.out.println(
                    student.getName() +
                    " promoted from waitlist to " +
                    name +
                    ". Credits: " +
                    student.getCurrentCredits() +
                    "/" +
                    student.getCreditLimit()
                );

                return;
            } else {

                System.out.println(
                    student.getName() +
                    " cannot be promoted because " +
                    "credit limit would be exceeded."
                );
            }
        }
    }
}

// Enrollment Service
class EnrollmentService {

    public void enroll(Student student, Elective elective) {
        elective.enroll(student);
    }

    public void drop(Student student, Elective elective) {
        elective.drop(student);
    }
}

// Main Class
public class ElectiveSeatRush {

    public static void main(String[] args) {

        EnrollmentService service = new EnrollmentService();

        // Cloud Computing: 4 credits, capacity 2
        Elective cloudComputing =
            new Elective("Cloud Computing", 4, 2);

        // Students
        Student asha =
            new Student(
                "Asha",
                new RegularCreditPolicy(),
                20
            );

        Student ravi =
            new Student(
                "Ravi",
                new HonorsCreditPolicy(),
                22
            );

        Student neha =
            new Student(
                "Neha",
                new ExchangeCreditPolicy(),
                12
            );

        Student kiran =
            new Student(
                "Kiran",
                new RegularCreditPolicy(),
                22
            );

        // Asha -> 20 + 4 = 24
        service.enroll(asha, cloudComputing);

        // Ravi -> 22 + 4 = 26
        service.enroll(ravi, cloudComputing);

        // Neha -> elective full, so waitlist
        service.enroll(neha, cloudComputing);

        // Kiran -> 22 + 4 = 26 > 24
        service.enroll(kiran, cloudComputing);

        // Asha drops
        service.drop(asha, cloudComputing);
    }
}