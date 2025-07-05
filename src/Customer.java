/**
 * Class representing a customer in the e-commerce system
 */
public class Customer {
    private String name;
    private double balance;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    /**
     * Deduct the specified amount from the customer's balance
     * 
     * @param amount the amount to deduct
     * @throws InsufficientBalanceException if the customer's balance is
     *                                      insufficient
     */
    public void deductBalance(double amount) throws InsufficientBalanceException {
        if (balance < amount) {
            throw new InsufficientBalanceException(
                    "Insufficient balance. Current balance: " + balance + ", Required: " + amount);
        }
        balance -= amount;
    }
}
