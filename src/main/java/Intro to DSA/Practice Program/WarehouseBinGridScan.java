import java.util.Scanner;

public class WarehouseBinGridScan {

    public static Object[] warehouseSummary(int[][] grid) {
        long total = 0;
        int maxValue = -1;
        int maxRow = 0;
        int maxColumn = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                total += grid[i][j];

                if (grid[i][j] > maxValue) {
                    maxValue = grid[i][j];
                    maxRow = i;
                    maxColumn = j;
                }
            }
        }

        return new Object[]{total, maxRow, maxColumn};
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of rows: ");
        int rows = scanner.nextInt();

        System.out.print("Enter number of columns: ");
        int columns = scanner.nextInt();

        int[][] grid = new int[rows][columns];

        System.out.println("Enter grid elements:");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }

        Object[] result = warehouseSummary(grid);

        System.out.println("Total: " + result[0]);
        System.out.println("Max Coordinate: (" + result[1] + ", " + result[2] + ")");

        scanner.close();
    }
}
