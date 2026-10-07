import java.util.Scanner;

public class MallFootfallRangeReport {

    public static long[] footfallReport(int[] visitors, int[][] queries) {
        long[] prefixSum = new long[visitors.length + 1];
        long[] result = new long[queries.length];

        for (int i = 0; i < visitors.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + visitors[i];
        }

        for (int i = 0; i < queries.length; i++) {
            int start = queries[i][0];
            int end = queries[i][1];

            result[i] = prefixSum[end + 1] - prefixSum[start];
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of visitors: ");
        int n = scanner.nextInt();

        int[] visitors = new int[n];

        System.out.println("Enter visitors for each hour:");
        for (int i = 0; i < n; i++) {
            visitors[i] = scanner.nextInt();
        }

        System.out.print("Enter number of queries: ");
        int q = scanner.nextInt();

        int[][] queries = new int[q][2];

        System.out.println("Enter start and end for each query:");
        for (int i = 0; i < q; i++) {
            queries[i][0] = scanner.nextInt();
            queries[i][1] = scanner.nextInt();
        }

        long[] result = footfallReport(visitors, queries);

        System.out.print("Output: [");

        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);

            if (i < result.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.println("]");

        scanner.close();
    }
}
