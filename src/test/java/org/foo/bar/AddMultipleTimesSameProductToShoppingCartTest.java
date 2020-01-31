package org.foo.bar;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("On adding multiple times same products to shopping cart")
public class AddMultipleTimesSameProductToShoppingCartTest {
    private final Product doveSoap = new Product("Dove Soap", new BigDecimal("39.99"));
    private final int firstQuantity = 5;
    private final int secondQuantity = 3;
    private ShoppingCart shoppingCart;

    @BeforeEach
    void setUp() {
        shoppingCart = ShoppingCart.empty().add(firstQuantity, doveSoap).add(secondQuantity, doveSoap);
    }

    @Test
    public void shouldContainExactlyWhatWasAdded() {
        int overallDoveSoapQuantity = firstQuantity + secondQuantity;
        assertThat("expected " + overallDoveSoapQuantity + " times" + doveSoap,
                shoppingCart.contains(overallDoveSoapQuantity, doveSoap), Matchers.is(true));
    }

}
