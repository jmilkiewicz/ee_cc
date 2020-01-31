package org.foo.bar;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private final int quantity;
    private final Product product;

    private final Map<Product, Integer> content;


    private ShoppingCart(int quantity, Product product, Map<Product, Integer> content) {
        this.quantity = quantity;
        this.product = product;
        this.content = content;
    }

    public static ShoppingCart empty() {
        return new ShoppingCart(0, null, Collections.emptyMap());
    }

    //TODO check for nulls
    public ShoppingCart add(int quantity, Product product) {
        if (quantity == 0) {
            return this;
        }
        Integer currentQuantity = this.content.getOrDefault(product, 0);
        Map<Product, Integer> newContent = new HashMap<>(this.content);
        newContent.put(product, currentQuantity + quantity);

        return new ShoppingCart(quantity + this.quantity, product, newContent);
    }

    public boolean contains(int quantity, Product product) {
        Integer currentQuantity = this.content.get(product);
        return currentQuantity != null && currentQuantity.equals(quantity);
    }

    public BigDecimal getTotalPrice() {
        return BigDecimal.valueOf(quantity).multiply(product.getUnitPrice());
    }
}
