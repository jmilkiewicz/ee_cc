package org.foo.bar;

import java.math.BigDecimal;

public class ShoppingCart {
    private final int quantity;
    private final Product product;

    //TODO check for nulls
    public ShoppingCart(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public static ShoppingCart empty() {
        return new ShoppingCart(0, null);
    }

    public ShoppingCart add(int quantity, Product product) {
        return new ShoppingCart(quantity + this.quantity, product);
    }

    public boolean contains(int quantity, Product product) {
        return quantity == this.quantity && this.product.equals(product);
    }

    public BigDecimal getTotalPrice() {
        return BigDecimal.valueOf(quantity).multiply(product.getUnitPrice());
    }
}
