/**
 * Exception thrown when a product is unavailable (out of stock or expired)
 */
public class ProductUnavailableException extends Exception {
    public ProductUnavailableException(String message) {
        super(message);
    }
}
