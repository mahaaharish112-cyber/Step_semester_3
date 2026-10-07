import java.util.HashMap;
import java.util.Scanner;

public class NetBalancePeriodCounter {

    public static long countPeriods(int[] transactions, long k) {
        HashMap<Long, Long> prefixCount = new HashMap<>();

        prefixCount.put(0L, 1L);

        long prefixSum = 0;
        long count = 0;

        for (int transaction : transactions) {
            prefixSum += transaction;

            if (prefixCount.containsKey(prefixSum - k)) {
                count += prefixCount.get(prefixSum - k);
            }

            prefixCount.put(
                prefixSum,
                prefixCount.getOrDefault(prefixSum, 0L) + 1
            );
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of transactions: ");
        int n = scanner.nextInt();

        int[] transactions = new int[n];

        System.out.println("Enter transactions:");
        for (int i = 0; i < n; i++) {
            transactions[i] = scanner.nextInt();
        }

        System.out.print("Enter target net balance: ");
        long k = scanner.nextLong();

        long result = countPeriods(transactions, k);

        System.out.println("Output: " + result);

        scanner.close();
    }
}