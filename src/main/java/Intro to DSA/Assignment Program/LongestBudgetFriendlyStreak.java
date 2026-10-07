import java.util.Scanner;

public class LongestBudgetFriendlyStreak {

    public static int[] longestStreak(int[] costs, long budget) {
        int left = 0;
        long sum = 0;
        int maxLength = 0;
        int startIndex = -1;

        for (int right = 0; right < costs.length; right++) {
            sum += costs[right];

            while (sum > budget && left <= right) {
                sum -= costs[left];
                left++;
            }

            int currentLength = right - left + 1;

            if (currentLength > maxLength) {
                maxLength = currentLength;
                startIndex = left;
            }
        }

        return new int[]{maxLength, startIndex};
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of days: ");
        int n = scanner.nextInt();

        int[] costs = new int[n];

        System.out.println("Enter daily costs:");
        for (int i = 0; i < n; i++) {
            costs[i] = scanner.nextInt();
        }

        System.out.print("Enter budget: ");
        long budget = scanner.nextLong();

        int[] result = longestStreak(costs, budget);

        System.out.println("Output: (" + result[0] + ", " + result[1] + ")");

        scanner.close();
    }
}
