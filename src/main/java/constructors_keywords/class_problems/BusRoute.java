package constructors_keywords.class_problems;

import java.util.Scanner;

public class BusRoute {

    private String routeCode;
    private String routeName;
    private int priority;

    public BusRoute(String routeCode, String routeName, int priority) {
        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    public BusRoute(String routeCode, String routeName) {
        this(routeCode, routeName, 5);
    }

    public int compareTo(BusRoute other) {

        if (this.priority != other.priority) {
            return Integer.compare(this.priority, other.priority);
        }

        int codeResult = this.routeCode.compareToIgnoreCase(other.routeCode);

        if (codeResult != 0) {
            return codeResult;
        }

        return this.routeName.compareToIgnoreCase(other.routeName);
    }

    public static BusRoute[] rankRoutes(BusRoute[] routes) {

        BusRoute[] ranked = routes.clone();

        for (int i = 1; i < ranked.length; i++) {

            BusRoute current = ranked[i];
            int j = i - 1;

            while (j >= 0 && ranked[j].compareTo(current) > 0) {
                ranked[j + 1] = ranked[j];
                j--;
            }

            ranked[j + 1] = current;
        }

        return ranked;
    }

    public String getRouteCode() {
        return routeCode;
    }

    public String getRouteName() {
        return routeName;
    }

    public int getPriority() {
        return priority;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of routes: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        BusRoute[] routes = new BusRoute[n];

        for (int i = 0; i < n; i++) {

            System.out.println("Enter details for Route " + (i + 1));

            System.out.print("Route Code: ");
            String routeCode = scanner.nextLine();

            System.out.print("Route Name: ");
            String routeName = scanner.nextLine();

            System.out.print("Enter priority, or -1 for default: ");
            int priority = scanner.nextInt();
            scanner.nextLine();

            if (priority == -1) {
                routes[i] = new BusRoute(routeCode, routeName);
            } else {
                routes[i] = new BusRoute(routeCode, routeName, priority);
            }
        }

        BusRoute[] rankedRoutes = BusRoute.rankRoutes(routes);

        System.out.println("\nRanked Routes:");

        for (BusRoute route : rankedRoutes) {
            System.out.println(
                    route.getRouteCode() + " - "
                    + route.getRouteName() + " - Priority: "
                    + route.getPriority()
            );
        }

        scanner.close();
    }
}
