import java.time.LocalDate;

/**
 * Main class to demonstrate the e-commerce system
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("E-commerce System Starting...");
        try {
            // Create products
            ShippablePerishableProduct cheese = new ShippablePerishableProduct(
                    "Cheese", 100, 10, LocalDate.now().plusDays(30), 0.2);

            ShippablePerishableProduct biscuits = new ShippablePerishableProduct(
                    "Biscuits", 150, 20, LocalDate.now().plusDays(90), 0.7);

            ShippableNonPerishableProduct tv = new ShippableNonPerishableProduct(
                    "TV", 5000, 5, 10.0);

            NonPerishableProduct scratchCard = new NonPerishableProduct(
                    "Scratch Card", 50, 100);

            // Create customer with a balance of 10000
            Customer customer = new Customer("John Doe", 10000);

            // Create shopping cart
            ShoppingCart cart = new ShoppingCart();

            // Example 1: Successful checkout
            System.out.println("=== Example 1: Successful Checkout ===");
            cart.add(cheese, 2);
            cart.add(tv, 1);
            cart.add(scratchCard, 1);

            try {
                CheckoutService.checkout(customer, cart);
            } catch (Exception e) {
                System.err.println("Checkout failed: " + e.getMessage());
            }

            // Example 2: Checkout with insufficient balance
            System.out.println("\n=== Example 2: Insufficient Balance ===");
            cart.add(tv, 3);

            try {
                CheckoutService.checkout(customer, cart);
            } catch (Exception e) {
                System.err.println("Checkout failed: " + e.getMessage());
            }

            // Example 3: Checkout with expired product
            System.out.println("\n=== Example 3: Expired Product ===");
            // Create an expired product
            ShippablePerishableProduct expiredMilk = new ShippablePerishableProduct(
                    "Expired Milk", 80, 5, LocalDate.now().minusDays(1), 1.0);

            cart.clear();
            cart.add(expiredMilk, 1);

            try {
                CheckoutService.checkout(customer, cart);
            } catch (Exception e) {
                System.err.println("Checkout failed: " + e.getMessage());
            }

            // Example 4: Checkout with out of stock product
            System.out.println("\n=== Example 4: Out of Stock Product ===");

            cheese.setQuantity(0);

            cart.clear();
            try {
                cart.add(cheese, 1);
            } catch (Exception e) {
                System.err.println("Adding to cart failed: " + e.getMessage());
            }

            // Example 5: Empty cart checkout
            System.out.println("\n=== Example 5: Empty Cart ===");
            cart.clear();

            try {
                CheckoutService.checkout(customer, cart);
            } catch (Exception e) {
                System.err.println("Checkout failed: " + e.getMessage());
            }

            // Example 6: Multiple shippable items
            System.out.println("\n=== Example 6: Multiple Shippable Items ===");
            cart.clear();

            cheese.setQuantity(10);

            cart.add(cheese, 2);
            cart.add(biscuits, 1);

            try {
                CheckoutService.checkout(customer, cart);
            } catch (Exception e) {
                System.err.println("Checkout failed: " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
