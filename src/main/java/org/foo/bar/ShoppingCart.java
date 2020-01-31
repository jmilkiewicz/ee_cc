package org.foo.bar;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ShoppingCart {
    private final int quantity;
    private final Product product;
    private final Optional<BigDecimal> taxRate;

    private final Map<Product, Integer> content;


    private ShoppingCart(int quantity, Product product, Map<Product, Integer> content, Optional<BigDecimal> taxRate) {
        this.quantity = quantity;
        this.product = product;
        this.content = content;
        this.taxRate = taxRate;
    }

    public static ShoppingCart empty() {
        return new ShoppingCart(0, null, Collections.emptyMap(), Optional.empty());
    }

    //TODO check for nulls
    //TODO what shall we do when quantity is negative ?
    public ShoppingCart add(int quantity, Product product) {
        if (quantity == 0) {
            return this;
        }
        Integer currentQuantity = this.content.getOrDefault(product, 0);
        Map<Product, Integer> newContent = new HashMap<>(this.content);
        newContent.put(product, currentQuantity + quantity);

        return new ShoppingCart(quantity + this.quantity, product, newContent, this.taxRate);
    }

    public boolean contains(int quantity, Product product) {
        Integer currentQuantity = this.content.get(product);
        return currentQuantity != null && currentQuantity.equals(quantity);
    }

    public BigDecimal getTotalPrice() {
        return BigDecimal.valueOf(quantity).multiply(product.getUnitPrice());
    }

    //TODO Shall we validate it is in particular range
    public ShoppingCart withTaxRate(BigDecimal taxRate) {
        return new ShoppingCart(this.quantity, this.product, this.content, Optional.ofNullable(taxRate));
    }

    public BigDecimal getTotalSalesTax() {

        return null;
    }
}
