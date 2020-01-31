package org.foo.bar;

public class ShoppingCart {
    private final int quantity;
    private final Product product;

    public ShoppingCart(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public static ShoppingCart empty() {
        return new ShoppingCart(0, null);
    }

    public ShoppingCart add(int quantity, Product product) {
        return new ShoppingCart(quantity, product);
    }

    public boolean contains(int quantity, Product product) {
        return true;
    }
}
