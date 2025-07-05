import java.util.List;

/**
 * Class representing a shipping service for the e-commerce system
 */
public class ShippingService {
    private static final double SHIPPING_RATE_PER_KG = 30.0;
    
    /**
     * Ship the specified items
     * @param items the items to ship
     * @return the shipping receipt
     */
    public static String ship(List<Shippable> items) {
        if (items.isEmpty()) {
            return "No items to ship";
        }
        
        StringBuilder receipt = new StringBuilder();
        receipt.append("** Shipment notice **\n");
        
        // Count items by name and weight
        java.util.Map<String, Integer> itemCounts = new java.util.HashMap<>();
        java.util.Map<String, Double> itemWeights = new java.util.HashMap<>();
        
        for (Shippable item : items) {
            String name = item.getName();
            itemCounts.put(name, itemCounts.getOrDefault(name, 0) + 1);
            itemWeights.put(name, item.getWeight());
        }
        
        double totalWeight = 0;
        
        for (java.util.Map.Entry<String, Integer> entry : itemCounts.entrySet()) {
            String name = entry.getKey();
            int count = entry.getValue();
            double weight = itemWeights.get(name);
            
            receipt.append(count + "x " + name + " " + (weight * 1000) + "g\n");
            totalWeight += count * weight;
        }
        
        receipt.append("Total package weight " + String.format("%.1f", totalWeight) + "kg\n");
        
        return receipt.toString();
    }
    
    /**
     * Calculate the shipping fee for the specified items
     * @param items the items to ship
     * @return the shipping fee
     */
    public static double calculateShippingFee(List<Shippable> items) {
        double totalWeight = 0;
        
        for (Shippable item : items) {
            totalWeight += item.getWeight();
        }
        
        return totalWeight * SHIPPING_RATE_PER_KG;
    }
}
