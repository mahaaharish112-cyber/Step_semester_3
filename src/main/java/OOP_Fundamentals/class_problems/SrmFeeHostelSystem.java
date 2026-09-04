package OOP_Fundamentals.class_problems;

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
        } else {
            System.out.println("Payment rejected for " + regNo);
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
        } else {
            System.out.println("Payment rejected for " + getDue());
        }
    }
}

class HostelRoom {

    private String roomNo;
    private int beds;
    private int occupied;

    public HostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    public boolean allot(String name) {
        if (occupied < beds) {
            occupied++;
            return true;
        }

        return false;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public static HostelRoom findAvailableRoom(HostelRoom[] rooms) {
        for (HostelRoom room : rooms) {
            if (room != null && room.occupied < room.beds) {
                return room;
            }
        }

        return null;
    }

    public static HostelRoom safeAllot(HostelRoom[] rooms, String studentName) {
        HostelRoom room = findAvailableRoom(rooms);

        if (room != null && room.allot(studentName)) {
            return room;
        }

        return null;
    }
}

class SrmStudent {

    String name;
    String regNo;
    HostelFeeAccount feeAccount;
    HostelRoom room;

    static int totalStudents = 0;

    public SrmStudent(String name, String regNo,
                      HostelFeeAccount feeAccount) {
        this.name = name;
        this.regNo = regNo;
        this.feeAccount = feeAccount;
        this.room = null;
        totalStudents++;
    }

    public String fullStatus() {
        String roomNumber;

        if (room == null) {
            roomNumber = "unallotted";
        } else {
            roomNumber = room.getRoomNo();
        }

        return name + " | Due: Rs " + feeAccount.getDue()
                + " | Room: " + roomNumber;
    }
}

public class SrmFeeHostelSystem {

    public static void main(String[] args) {

        HostelRoom[] rooms = {
            new HostelRoom("C-214", 1, 0),
            new HostelRoom("C-507", 1, 0)
        };

        SrmStudent ravi = new SrmStudent(
                "Ravi",
                "RA101",
                new HostelFeeAccount("RA101", 200000, 60000)
        );

        SrmStudent anitha = new SrmStudent(
                "Anitha",
                "RA102",
                new HostelFeeAccount("RA102", 200000, 20000)
        );

        SrmStudent karthik = new SrmStudent(
                "Karthik",
                "RA103",
                new HostelFeeAccount("RA103", 200000, 0)
        );

        ravi.room = HostelRoom.safeAllot(rooms, ravi.name);
        anitha.room = HostelRoom.safeAllot(rooms, anitha.name);

        ravi.feeAccount.pay(10000);
        anitha.feeAccount.pay(-5000);
        karthik.feeAccount.pay(0);

        System.out.println(ravi.fullStatus());
        System.out.println(anitha.fullStatus());
        System.out.println(karthik.fullStatus());

        System.out.println("Total students: " + SrmStudent.totalStudents);
    }
}
