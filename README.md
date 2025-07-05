# E-Commerce System

This Java project implements a simple e-commerce system with product management, shopping cart functionality, checkout processing, and shipping capabilities.

## Features

- **Product Management**
  - Products with name, price, and quantity
  - Perishable products (with expiration dates) like Cheese and Biscuits
  - Non-perishable products like TVs and Scratch Cards
  - Shippable and non-shippable product categorization

- **Shopping Cart**
  - Add products with quantity validation
  - Remove products
  - Calculate subtotal
  - Clear cart

- **Checkout Service**
  - Process customer checkouts
  - Validate product availability and expiration
  - Calculate shipping fees
  - Deduct amount from customer balance
  - Generate detailed receipts

- **Shipping Service**
  - Calculate shipping fees based on weight
  - Generate shipping receipts

- **Error Handling**
  - Empty cart validation
  - Insufficient balance validation
  - Product availability validation (out of stock or expired)

## Class Structure

- **Product Hierarchy**
  - `Product` (abstract base class)
    - `PerishableProduct`
      - `ShippablePerishableProduct`
    - `NonPerishableProduct`
      - `ShippableNonPerishableProduct`

- **Interfaces**
  - `Shippable`

- **Services**
  - `ShoppingCart`
  - `CheckoutService`
  - `ShippingService`

- **Exception Classes**
  - `EmptyCartException`
  - `InsufficientBalanceException`
  - `ProductUnavailableException`

## Usage Example

```java
// Create products
ShippablePerishableProduct cheese = new ShippablePerishableProduct(
        "Cheese", 100, 10, LocalDate.now().plusDays(30), 0.2);
ShippableNonPerishableProduct tv = new ShippableNonPerishableProduct(
        "TV", 5000, 5, 10.0);
NonPerishableProduct scratchCard = new NonPerishableProduct(
        "Scratch Card", 50, 100);

// Create customer
Customer customer = new Customer("John Doe", 10000);

// Create shopping cart
ShoppingCart cart = new ShoppingCart();
cart.add(cheese, 2);
cart.add(tv, 1);
cart.add(scratchCard, 1);

// Process checkout
try {
    CheckoutService.checkout(customer, cart);
} catch (Exception e) {
    System.err.println("Checkout failed: " + e.getMessage());
}
```

## Sample Output

```
** Checkout receipt **
1x Scratch Card 50
2x Cheese 200
1x TV 5000
----------------------
Subtotal 5250
Shipping 312
Amount 5562
Remaining Balance 4438

** Shipment notice **
1x TV 10000.0g
2x Cheese 200.0g
Total package weight 10.4kg
```

## How to Run

1. Compile the Java files:
   ```
   javac -d out src/*.java
   ```

2. Run the application:
   ```
   java -cp out Main
   ```

## Error Handling Examples

The `Main` class demonstrates various error scenarios:
- Checkout with insufficient balance
- Checkout with expired products
- Checkout with out-of-stock products
- Checkout with an empty cart
