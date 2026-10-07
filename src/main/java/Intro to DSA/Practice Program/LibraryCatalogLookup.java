import java.util.Scanner;

public class LibraryCatalogLookup {

    public static String findBook(String[][] catalog, String targetIsbn) {
        int left = 0;
        int right = catalog.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = catalog[mid][0].compareTo(targetIsbn);

            if (comparison == 0) {
                return catalog[mid][1];
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return "Not Found";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of books: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        String[][] catalog = new String[n][2];

        System.out.println("Enter ISBN and title:");

        for (int i = 0; i < n; i++) {
            System.out.print("ISBN: ");
            catalog[i][0] = scanner.nextLine();

            System.out.print("Title: ");
            catalog[i][1] = scanner.nextLine();
        }

        System.out.print("Enter target ISBN: ");
        String targetIsbn = scanner.nextLine();

        String result = findBook(catalog, targetIsbn);

        System.out.println("Output: " + result);

        scanner.close();
    }
}
