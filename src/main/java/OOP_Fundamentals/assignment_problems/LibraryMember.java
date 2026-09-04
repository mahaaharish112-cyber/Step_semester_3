package main.java.OOP_Fundamentals.assignment_problems;

public class LibraryMember {

    private String name;
    private String memberId;
    private int booksIssued;

    private static String libraryName = "Central Library";
    private static int memberCount = 0;

    public LibraryMember(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;

        memberCount++;
        this.memberId = String.format("LM-%04d", 1000 + memberCount);
    }

    public void printMemberCard() {
        System.out.println(name + " | " + memberId);
    }

    public static void printTotalMembers() {
        System.out.println("Total members: " + memberCount);
    }

    public static void main(String[] args) {

        System.out.println("Broken version:");

        String name = "Aditi";
        String memberId = "LM-1001";
        int booksIssued = 2;

        name = "Rohan";
        memberId = "LM-1002";
        booksIssued = 3;

        System.out.println(name);
        System.out.println(name);

        System.out.println();

        System.out.println("Fixed version:");

        LibraryMember member1 = new LibraryMember("Aditi", 2);
        LibraryMember member2 = new LibraryMember("Rohan", 3);

        member1.printMemberCard();
        member2.printMemberCard();

        LibraryMember.printTotalMembers();
    }
}