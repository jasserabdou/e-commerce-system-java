import java.time.LocalDate;

/**
 * Class representing a perishable product that requires shipping
 */
public class ShippablePerishableProduct extends PerishableProduct implements Shippable {
    private double weight;

    public ShippablePerishableProduct(String name, double price, int quantity, LocalDate expirationDate,
            double weight) {
        super(name, price, quantity, expirationDate);
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
