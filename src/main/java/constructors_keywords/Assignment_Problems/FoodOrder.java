package main.java.constructors_keywords.Assignment_Problems;

public class FoodOrder {

    private String studentName;
    private String dishName;
    private boolean delivered;

    public FoodOrder(String studentName, String dishName) {

        if (studentName == null || studentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be blank");
        }

        if (dishName == null || dishName.trim().isEmpty()) {
            throw new IllegalArgumentException("Dish name cannot be blank");
        }

        this.studentName = studentName;
        this.dishName = dishName;
        this.delivered = false;
    }

    public void markDelivered() {

        if (delivered) {
            System.out.println("Order is already delivered.");
        } else {
            delivered = true;
            System.out.println("Order marked as delivered.");
        }
    }

    public static void processBatch(String[][] rawOrders) {

        int valid = 0;
        int rejected = 0;

        for (String[] order : rawOrders) {

            try {
                new FoodOrder(order[0], order[1]);
                valid++;

            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid + " | Rejected: " + rejected);
    }

    public static void main(String[] args) {

        String[][] rawOrders = {
            {"Ravi", "Paneer Butter Masala"},
            {"", "Chole Bhature"},
            {"Meera", " "},
            {"Divya", "Veg Biryani"}
        };

        processBatch(rawOrders);

        FoodOrder order = new FoodOrder("Ravi", "Paneer Butter Masala");

        order.markDelivered();
        order.markDelivered();
    }
}
