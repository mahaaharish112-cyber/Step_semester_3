package OOP_Fundamentals.class_problems;

public class SrmStudentStaticDemo {

    static class BrokenSrmStudent {

        static String name;
        static String regNo;
        static int attendance;

        public BrokenSrmStudent(String name, String regNo, int attendance) {
            BrokenSrmStudent.name = name;
            BrokenSrmStudent.regNo = regNo;
            BrokenSrmStudent.attendance = attendance;
        }

        public String getName() {
            return name;
        }
    }

    private String name;
    private String regNo;
    private int attendance;

    private static String university = "SRM Institute of Science and Technology";
    private static int admissionCount = 0;

    public SrmStudentStaticDemo(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;
        admissionCount++;
        this.regNo = "RA2311003010" + String.format("%02d", admissionCount);
    }

    public void printIdCard() {
        System.out.println(name + " | " + regNo);
    }

    public static void printTotalAdmissions() {
        System.out.println("Students admitted so far: " + admissionCount);
    }

    public static void main(String[] args) {

        System.out.println("Broken version:");

        BrokenSrmStudent student1 =
                new BrokenSrmStudent("Ravi", "RA231100301011", 82);

        BrokenSrmStudent student2 =
                new BrokenSrmStudent("Meera", "RA231100301012", 74);

        System.out.println(student1.getName());
        System.out.println(student2.getName());

        System.out.println();

        System.out.println("Fixed version:");

        SrmStudentStaticDemo student3 =
                new SrmStudentStaticDemo("Ravi", 82);

        SrmStudentStaticDemo student4 =
                new SrmStudentStaticDemo("Meera", 74);

        student3.printIdCard();
        student4.printIdCard();

        SrmStudentStaticDemo.printTotalAdmissions();
    }
}
