package org.foo.bar;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ShoppingCart {
    public static final ShoppingCart EMPTY = new ShoppingCart(Collections.emptyMap(), Optional.empty());
    private final Optional<BigDecimal> taxRate;
    private final Map<Product, Integer> content;
    private final BigDecimal itemsValue;


    private ShoppingCart(Map<Product, Integer> content, Optional<BigDecimal> taxRate) {
        this.content = content;
        this.taxRate = taxRate;
        this.itemsValue = calculateItemsValue(content);
    }

    private static BigDecimal calculateItemsValue(Map<Product, Integer> content) {
        return content.entrySet().stream().map(entry -> entry.getKey().getUnitPrice().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    //TODO check for nulls
    //TODO what shall we do when quantity is negative ?
    public ShoppingCart add(int quantity, Product product) {
        if (quantity == 0) {
            return this;
        }
        return new ShoppingCart(buildNewContent(quantity, product), this.taxRate);
    }

    private Map<Product, Integer> buildNewContent(int quantity, Product product) {
        Integer currentQuantity = this.content.getOrDefault(product, 0);
        Map<Product, Integer> newContent = new HashMap<>(this.content);
        newContent.put(product, currentQuantity + quantity);
        return newContent;
    }

    public boolean contains(int quantity, Product product) {
        Integer currentQuantity = this.content.get(product);
        return currentQuantity != null && currentQuantity.equals(quantity);
    }

    public BigDecimal getTotalPrice() {
        return itemsValue.add(getTotalSalesTax());
    }

    //TODO Shall we validate it is in particular range and precision
    public ShoppingCart withTaxRate(BigDecimal taxRate) {
        BigDecimal tax = taxRate.divide(BigDecimal.valueOf(100));
        return new ShoppingCart(this.content, Optional.ofNullable(tax));
    }

    public BigDecimal getTotalSalesTax() {
        return taxRate.map(tr -> tr.multiply(itemsValue).setScale(2, RoundingMode.HALF_UP)).orElse(BigDecimal.ZERO);
    }
}
