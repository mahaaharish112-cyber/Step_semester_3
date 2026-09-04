package main.java.String_Operations_Performance.assigment_problems;
import java.util.Scanner;

public class AtmPinLengthValidator {
    public static void checkPinLength(String pin) {
        if (pin.length() != 4) {
            System.out.println("Invalid PIN — must be exactly 4 digits.");
        } else {
            System.out.println("PIN length OK.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your 4-digit PIN: ");
        String pin = scanner.nextLine();

        checkPinLength(pin);

        scanner.close();
    }
}