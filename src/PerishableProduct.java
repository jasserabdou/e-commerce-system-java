import java.time.LocalDate;

/**
 * Class representing a perishable product that has an expiration date
 */
public class PerishableProduct extends Product {
    private LocalDate expirationDate;

    public PerishableProduct(String name, double price, int quantity, LocalDate expirationDate) {
        super(name, price, quantity);
        this.expirationDate = expirationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
    }
}
