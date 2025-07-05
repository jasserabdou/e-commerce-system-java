import java.util.List;
import java.util.Map;

/**
 * Class representing a checkout service for the e-commerce system
 */
public class CheckoutService {

    /**
     * Process a checkout for a customer with the specified cart
     * 
     * @param customer the customer
     * @param cart     the shopping cart
     * @throws EmptyCartException           if the cart is empty
     * @throws InsufficientBalanceException if the customer's balance is
     *                                      insufficient
     * @throws ProductUnavailableException  if a product is out of stock or expired
     */
    public static void checkout(Customer customer, ShoppingCart cart)
            throws EmptyCartException, InsufficientBalanceException, ProductUnavailableException {

        if (cart.isEmpty()) {
            throw new EmptyCartException("Cart is empty");
        }

        // Verify all products are available and not expired
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            if (!product.isAvailable(quantity)) {
                throw new ProductUnavailableException(product.getName() + " is out of stock");
            }

            if (product.isExpired()) {
                throw new ProductUnavailableException(product.getName() + " is expired");
            }
        }

        // Calculate subtotal
        double subtotal = cart.calculateSubtotal();

        // Calculate shipping fee
        List<Shippable> shippableItems = cart.getShippableItems();
        double shippingFee = ShippingService.calculateShippingFee(shippableItems);

        // Calculate total amount
        double totalAmount = subtotal + shippingFee;

        // Deduct amount from customer's balance
        customer.deductBalance(totalAmount);

        // Update product quantities
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            product.setQuantity(product.getQuantity() - quantity);
        }

        // Print receipt
        System.out.println(generateReceipt(cart, subtotal, shippingFee, totalAmount, customer.getBalance()));

        // Ship items if needed
        if (!shippableItems.isEmpty()) {
            System.out.println(ShippingService.ship(shippableItems));
        }

        // Clear the cart
        cart.clear();
    }

    /**
     * Generate a receipt for the checkout
     * 
     * @param cart             the shopping cart
     * @param subtotal         the subtotal
     * @param shippingFee      the shipping fee
     * @param totalAmount      the total amount
     * @param remainingBalance the customer's remaining balance
     * @return the receipt
     */
    private static String generateReceipt(ShoppingCart cart, double subtotal, double shippingFee, double totalAmount,
            double remainingBalance) {
        StringBuilder receipt = new StringBuilder();
        receipt.append("** Checkout receipt **\n");

        // List all items
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            receipt.append(quantity + "x " + product.getName() + " " + (int)(product.getPrice() * quantity) + "\n");
        }

        receipt.append("----------------------\n");
        receipt.append("Subtotal " + (int)subtotal + "\n");
        receipt.append("Shipping " + (int)shippingFee + "\n");
        receipt.append("Amount " + (int)totalAmount + "\n");
        receipt.append("Remaining Balance " + (int)remainingBalance + "\n");

        return receipt.toString();
    }
}
