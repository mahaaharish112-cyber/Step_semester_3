import java.time.LocalDate;

abstract class Employee {
    protected String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract boolean canTakeLeave(int days);
}

class FullTimeEmployee extends Employee {

    public FullTimeEmployee(String name) {
        super(name);
    }

    @Override
    public boolean canTakeLeave(int days) {
        return days <= 20;
    }
}

class PartTimeEmployee extends Employee {

    public PartTimeEmployee(String name) {
        super(name);
    }

    @Override
    public boolean canTakeLeave(int days) {
        return days <= 10;
    }
}

class ContractEmployee extends Employee {

    public ContractEmployee(String name) {
        super(name);
    }

    @Override
    public boolean canTakeLeave(int days) {
        return days <= 5;
    }
}

enum LeaveStatus {
    PENDING,
    APPROVED,
    REJECTED
}

class LeaveRequest {
    private Employee employee;
    private LocalDate startDate;
    private LocalDate endDate;
    private LeaveStatus status;

    public LeaveRequest(
            Employee employee,
            LocalDate startDate,
            LocalDate endDate) {

        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = LeaveStatus.PENDING;
    }

    public void approve() {

        if (status != LeaveStatus.PENDING) {
            System.out.println(
                "Cannot approve. Request is already " + status
            );
            return;
        }

        status = LeaveStatus.APPROVED;

        System.out.println(
            "Leave request for " +
            employee.getName() +
            " approved. Status: Approved."
        );
    }

    public void reject() {

        if (status != LeaveStatus.PENDING) {
            System.out.println(
                "Cannot reject. Request is already " + status
            );
            return;
        }

        status = LeaveStatus.REJECTED;

        System.out.println(
            "Leave request for " +
            employee.getName() +
            " rejected. Status: Rejected."
        );
    }

    public void setPending() {

        if (status != LeaveStatus.PENDING) {
            System.out.println(
                "Cannot change status: " +
                status +
                " request cannot revert to Pending."
            );
            return;
        }

        status = LeaveStatus.PENDING;
    }

    public LeaveStatus getStatus() {
        return status;
    }
}

class LeaveManager {

    public LeaveRequest submitLeave(
            Employee employee,
            LocalDate startDate,
            LocalDate endDate) {

        int days =
            (int) java.time.temporal.ChronoUnit.DAYS.between(
                startDate,
                endDate
            ) + 1;

        if (!employee.canTakeLeave(days)) {
            System.out.println(
                "Leave request rejected: " +
                employee.getName() +
                " exceeds the leave limit."
            );
            return null;
        }

        LeaveRequest request =
            new LeaveRequest(
                employee,
                startDate,
                endDate
            );

        System.out.println(
            "Leave request submitted by " +
            employee.getName() +
            " for " +
            startDate +
            " to " +
            endDate +
            ". Status: Pending."
        );

        return request;
    }
}

public class EmployeeLeaveManagement {

    public static void main(String[] args) {

        LeaveManager manager = new LeaveManager();

        // Full-time employee
        Employee john =
            new FullTimeEmployee("John Doe");

        LeaveRequest johnRequest =
            manager.submitLeave(
                john,
                LocalDate.of(2024, 10, 10),
                LocalDate.of(2024, 10, 12)
            );

        // Manager approves John's request
        johnRequest.approve();

        // Part-time employee
        Employee jane =
            new PartTimeEmployee("Jane Smith");

        LeaveRequest janeRequest =
            manager.submitLeave(
                jane,
                LocalDate.of(2024, 11, 1),
                LocalDate.of(2024, 11, 5)
            );

        // Try to change John's approved request
        johnRequest.setPending();
    }
}
