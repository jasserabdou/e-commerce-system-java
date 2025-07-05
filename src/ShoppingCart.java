import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class representing a shopping cart in the e-commerce system
 */
public class ShoppingCart {
    private Map<Product, Integer> items;
    
    public ShoppingCart() {
        this.items = new HashMap<>();
    }
    
    /**
     * Add a product to the cart with the specified quantity
     * @param product the product to add
     * @param quantity the quantity to add
     * @throws IllegalArgumentException if the quantity is invalid or exceeds available stock
     */
    public void add(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        
        if (!product.isAvailable(quantity)) {
            throw new IllegalArgumentException("Requested quantity exceeds available stock");
        }
        
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }
    
    /**
     * Remove a product from the cart
     * @param product the product to remove
     */
    public void remove(Product product) {
        items.remove(product);
    }
    
    /**
     * Get all items in the cart
     * @return a map of products and their quantities
     */
    public Map<Product, Integer> getItems() {
        return new HashMap<>(items);
    }
    
    /**
     * Check if the cart is empty
     * @return true if the cart is empty
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    /**
     * Get all shippable items in the cart
     * @return a list of shippable items
     */
    public List<Shippable> getShippableItems() {
        List<Shippable> shippableItems = new ArrayList<>();
        
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            
            if (product instanceof Shippable) {
                for (int i = 0; i < quantity; i++) {
                    shippableItems.add((Shippable) product);
                }
            }
        }
        
        return shippableItems;
    }
    
    /**
     * Calculate the subtotal of all items in the cart
     * @return the subtotal
     */
    public double calculateSubtotal() {
        double subtotal = 0;
        
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            
            subtotal += product.getPrice() * quantity;
        }
        
        return subtotal;
    }
    
    /**
     * Clear the cart
     */
    public void clear() {
        items.clear();
    }
}
