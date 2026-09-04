package main.java.OOP_Fundamentals.assignment_problems;

class Employee {

    private int empId;
    private String empName;
    private double salary;

    public Employee(int empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }
}

class ManagerEmployee extends Employee {

    private double teamBonus;

    public ManagerEmployee(int empId, String empName, double salary, double teamBonus) {
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    public double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

class InternEmployee extends Employee {

    private double stipendCap;

    public InternEmployee(int empId, String empName, double salary, double stipendCap) {
        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    public double effectiveSalary() {
        return Math.min(getSalary(), stipendCap);
    }
}

public class EmployeeDemo {

    public static void main(String[] args) {

        Employee plainEmployee = new Employee(101, "Arun", 40000);
        ManagerEmployee manager = new ManagerEmployee(102, "Rahul", 70000, 8000);
        InternEmployee intern = new InternEmployee(103, "Kavin", 12000, 10000);

        Employee[] employees = {
            plainEmployee,
            manager,
            intern
        };

        for (Employee employee : employees) {

            if (employee instanceof ManagerEmployee) {
                ManagerEmployee managerEmployee = (ManagerEmployee) employee;
                System.out.println("Manager effective pay: Rs "
                        + managerEmployee.effectiveSalary());

            } else if (employee instanceof InternEmployee) {
                InternEmployee internEmployee = (InternEmployee) employee;
                System.out.println("Intern effective pay: Rs "
                        + internEmployee.effectiveSalary());

            } else {
                System.out.println("Plain employee pay: Rs "
                        + employee.getSalary());
            }
        }
    }
}
