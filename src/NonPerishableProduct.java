/**
 * Class representing a non-perishable product that doesn't expire
 */
public class NonPerishableProduct extends Product {

    public NonPerishableProduct(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    @Override
    public boolean isExpired() {
        return false; // Non-perishable products never expire
    }
}
