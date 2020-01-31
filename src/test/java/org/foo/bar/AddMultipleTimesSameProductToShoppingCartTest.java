package org.foo.bar;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.foo.bar.TestFixtures.DoveSoap;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("On adding multiple times same products to shopping cart")
public class AddMultipleTimesSameProductToShoppingCartTest {
    private final int firstQuantity = 5;
    private final int secondQuantity = 3;
    private ShoppingCart shoppingCart;

    @BeforeEach
    void setUp() {
        shoppingCart = ShoppingCart.empty().add(firstQuantity, DoveSoap).add(secondQuantity, DoveSoap);
    }

    @Test
    public void shouldContainExactlyWhatWasAdded() {
        int overallDoveSoapQuantity = firstQuantity + secondQuantity;
        assertThat("expected " + overallDoveSoapQuantity + " times" + DoveSoap,
                shoppingCart.contains(overallDoveSoapQuantity, DoveSoap), Matchers.is(true));
    }

    //To reviewer: this test was not created in the TDD way and was only added as safety net
    @Test
    public void shallReturnTotalPriceAfterAllAdditions() {
        assertThat(shoppingCart.getTotalPrice(), Matchers.is(new BigDecimal("319.92")));
    }

}
