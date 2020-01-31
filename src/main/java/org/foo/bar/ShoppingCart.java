package org.foo.bar;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ShoppingCart {
    private final Optional<BigDecimal> taxRate;

    private final Map<Product, Integer> content;


    private ShoppingCart(Map<Product, Integer> content, Optional<BigDecimal> taxRate) {
        this.content = content;
        this.taxRate = taxRate;
    }

    public static ShoppingCart empty() {
        return new ShoppingCart(Collections.emptyMap(), Optional.empty());
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

        return new ShoppingCart(newContent, this.taxRate);
    }

    public boolean contains(int quantity, Product product) {
        Integer currentQuantity = this.content.get(product);
        return currentQuantity != null && currentQuantity.equals(quantity);
    }

    public BigDecimal getTotalPrice() {
        return itemsValue().add(getTotalSalesTax());
    }

    private BigDecimal itemsValue() {
        return this.content.entrySet().stream().map(entry -> entry.getKey().getUnitPrice().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    //TODO Shall we validate it is in particular range and precision
    public ShoppingCart withTaxRate(BigDecimal taxRate) {
        BigDecimal tax = taxRate.divide(BigDecimal.valueOf(100));
        return new ShoppingCart(this.content, Optional.ofNullable(tax));
    }

    public BigDecimal getTotalSalesTax() {
        return taxRate.map(tr -> tr.multiply(itemsValue()).setScale(2, RoundingMode.HALF_UP)).orElse(BigDecimal.ZERO);
    }
}
