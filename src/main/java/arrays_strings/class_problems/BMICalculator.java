
import java.util.Scanner;

public class BMICalculator {

    public static String getBmiStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    public static void printWellnessReport(double[] heights, double[] weights) {
        System.out.println("\n==============================================================");
        System.out.println("                  WELLNESS REPORT");
        System.out.println("==============================================================");

        System.out.printf("%-10s %-15s %-15s %-10s %-15s%n",
                "Person", "Height (m)", "Weight (kg)", "BMI", "Status");

        System.out.println("--------------------------------------------------------------");

        for (int i = 0; i < heights.length; i++) {
            double bmi = weights[i] / (heights[i] * heights[i]);
            String status = getBmiStatus(bmi);

            System.out.printf("%-10d %-15.2f %-15.2f %-10.2f %-15s%n",
                    (i + 1),
                    heights[i],
                    weights[i],
                    bmi,
                    status);
        }

        System.out.println("==============================================================");
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of people: ");
        int n = scanner.nextInt();

        double[] heights = new double[n];
        double[] weights = new double[n];

        for (int i = 0; i < n; i++) {

            System.out.println("\nPerson " + (i + 1));

            System.out.print("Enter height in meters: ");
            heights[i] = scanner.nextDouble();

            while (heights[i] <= 0) {
                System.out.print("Height must be greater than 0. Enter again: ");
                heights[i] = scanner.nextDouble();
            }

            System.out.print("Enter weight in kg: ");
            weights[i] = scanner.nextDouble();

            while (weights[i] <= 0) {
                System.out.print("Weight must be greater than 0. Enter again: ");
                weights[i] = scanner.nextDouble();
            }
        }

        printWellnessReport(heights, weights);

        scanner.close();
    }
}