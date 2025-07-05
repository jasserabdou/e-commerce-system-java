/**
 * Exception thrown when a customer's balance is insufficient for a transaction
 */
public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
