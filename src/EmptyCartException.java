/**
 * Exception thrown when a cart is empty during checkout
 */
public class EmptyCartException extends Exception {
    public EmptyCartException(String message) {
        super(message);
    }
}
