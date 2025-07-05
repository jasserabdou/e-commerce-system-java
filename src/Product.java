/**
 * Abstract class representing a product in the e-commerce system
 */
public abstract class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Check if the product is available in the specified quantity
     * 
     * @param requestedQuantity the quantity to check
     * @return true if the product is available in the specified quantity
     */
    public boolean isAvailable(int requestedQuantity) {
        return quantity >= requestedQuantity;
    }

    /**
     * Check if the product is expired
     * 
     * @return true if the product is expired
     */
    public abstract boolean isExpired();
}
