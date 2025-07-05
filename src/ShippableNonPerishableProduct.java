/**
 * Class representing a non-perishable product that requires shipping
 */
public class ShippableNonPerishableProduct extends NonPerishableProduct implements Shippable {
    private double weight;

    public ShippableNonPerishableProduct(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
