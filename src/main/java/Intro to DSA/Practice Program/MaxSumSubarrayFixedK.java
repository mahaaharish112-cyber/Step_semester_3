import java.util.Scanner;

public class MaxSumSubarrayFixedK {

    public static int maxSumSubarray(int[] sales, int k) {
        int windowSum = 0;

        for (int i = 0; i < k; i++) {
            windowSum += sales[i];
        }

        int maxSum = windowSum;

        for (int i = k; i < sales.length; i++) {
            windowSum += sales[i];
            windowSum -= sales[i - k];

            if (windowSum > maxSum) {
                maxSum = windowSum;
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of sales records: ");
        int n = scanner.nextInt();

        int[] sales = new int[n];

        System.out.println("Enter sales values:");

        for (int i = 0; i < n; i++) {
            sales[i] = scanner.nextInt();
        }

        System.out.print("Enter k: ");
        int k = scanner.nextInt();

        if (k < 1 || k > n) {
            System.out.println("Invalid value of k");
        } else {
            int result = maxSumSubarray(sales, k);
            System.out.println("Output: " + result);
        }

        scanner.close();
    }
}
