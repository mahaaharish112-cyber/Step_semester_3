import java.util.Scanner;

public class ExamScoreBandCounter {

    public static int countInBand(int[] scores, int low, int high) {
        int first = lowerBound(scores, low);
        int last = upperBound(scores, high);

        return last - first;
    }

    public static int lowerBound(int[] scores, int target) {
        int left = 0;
        int right = scores.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (scores[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static int upperBound(int[] scores, int target) {
        int left = 0;
        int right = scores.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (scores[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of scores: ");
        int n = scanner.nextInt();

        int[] scores = new int[n];

        System.out.println("Enter scores in sorted order:");
        for (int i = 0; i < n; i++) {
            scores[i] = scanner.nextInt();
        }

        System.out.print("Enter lower score: ");
        int low = scanner.nextInt();

        System.out.print("Enter upper score: ");
        int high = scanner.nextInt();

        int result = countInBand(scores, low, high);

        System.out.println("Output: " + result);

        scanner.close();
    }
}