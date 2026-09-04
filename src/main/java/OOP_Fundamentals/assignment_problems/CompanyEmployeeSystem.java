package main.java.OOP_Fundamentals.assignment_problems;

class Employee {

    private String name;
    private double pay;

    public Employee(String name, double pay) {
        this.name = name;
        this.pay = pay;
    }

    public double getPay() {
        return pay;
    }

    public String getName() {
        return name;
    }
}

class ManagerEmployee extends Employee {

    private double bonus;

    public ManagerEmployee(String name, double pay, double bonus) {
        super(name, pay);
        this.bonus = bonus;
    }

    @Override
    public double getPay() {
        return super.getPay() + bonus;
    }
}

class ParkingSlot {

    private String slotNumber;
    private boolean allotted;

    public ParkingSlot(String slotNumber) {
        this.slotNumber = slotNumber;
        this.allotted = false;
    }

    public boolean allot() {
        if (!allotted) {
            allotted = true;
            return true;
        }
        return false;
    }

    public String getSlotNumber() {
        return slotNumber;
    }
}

class CompanyEmployeeRecord {

    private String name;
    private String empId;
    private Employee employee;
    private ParkingSlot slot;

    public static int totalRecords = 0;

    public CompanyEmployeeRecord(String name, String empId,
                                 Employee employee, ParkingSlot slot) {
        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = slot;
        totalRecords++;
    }

    public String fullProfile() {
        if (slot == null) {
            return name + " | Pay: Rs " + employee.getPay()
                    + " | Slot: no parking assigned";
        }

        return name + " | Pay: Rs " + employee.getPay()
                + " | Slot: " + slot.getSlotNumber();
    }
}

public class CompanyEmployeeSystem {

    public static void main(String[] args) {

        Employee divyaEmployee =
                new ManagerEmployee("Divya", 70000, 8000);

        Employee karanEmployee =
                new Employee("Karan", 40000);

        Employee meeraEmployee =
                new Employee("Meera", 10000);

        ParkingSlot slotA1 = new ParkingSlot("A1");
        ParkingSlot slotA2 = new ParkingSlot("A2");

        slotA1.allot();
        slotA2.allot();

        CompanyEmployeeRecord record1 =
                new CompanyEmployeeRecord(
                        "Divya",
                        "EMP001",
                        divyaEmployee,
                        slotA1
                );

        CompanyEmployeeRecord record2 =
                new CompanyEmployeeRecord(
                        "Karan",
                        "EMP002",
                        karanEmployee,
                        slotA2
                );

        CompanyEmployeeRecord record3 =
                new CompanyEmployeeRecord(
                        "Meera",
                        "EMP003",
                        meeraEmployee,
                        null
                );

        System.out.println(record1.fullProfile());
        System.out.println(record2.fullProfile());
        System.out.println(record3.fullProfile());

        System.out.println("Total records: "
                + CompanyEmployeeRecord.totalRecords);
    }
}
