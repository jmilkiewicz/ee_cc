package org.foo.bar;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.foo.bar.TestFixtures.DoveSoap;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("On adding single type products to shopping cart")
public class AddSingleTypeProductToShoppingCartTest {
    private final int doveSoapQuantity = 5;
    private ShoppingCart shoppingCart;

    @BeforeEach
    void setUp() {
        shoppingCart = ShoppingCart.empty().add(doveSoapQuantity, DoveSoap);
    }

    @Test
    public void shouldContainExactlyWhatWasAdded() {
        assertThat("expected " + doveSoapQuantity + " times" + DoveSoap,
                shoppingCart.contains(doveSoapQuantity, DoveSoap), Matchers.is(true));
    }

    @Test
    public void shouldNotContainWhenQuantityIsDifferentToWhatWasAdded() {
        int moreThanAdded = doveSoapQuantity + 1;
        assertThat("not expected to have " + moreThanAdded + " times" + DoveSoap,
                shoppingCart.contains(moreThanAdded, DoveSoap), Matchers.is(false));
    }

    @Test
    public void shouldNotContainWhenProductIsDifferentToWhatWasAdded() {
        Product doveSoapOnBargain = new Product(DoveSoap.getName(), DoveSoap.getUnitPrice().subtract(BigDecimal.ONE));
        //TODO small duplication with message
        assertThat("not expected to have " + doveSoapQuantity + " times" + doveSoapOnBargain,
                shoppingCart.contains(doveSoapQuantity, doveSoapOnBargain), Matchers.is(false));
    }

    @Test
    public void shallReturnTotalPrice() {
        assertThat(shoppingCart.getTotalPrice(), Matchers.is(new BigDecimal("199.95")));
    }

    @Test
    public void shouldNotContainProductEvenWhenAddedWithQuantityZero() {
        ShoppingCart shoppingCart = ShoppingCart.empty().add(0, TestFixtures.DoveSoap);

        assertThat("not expected to contain" + DoveSoap,
                shoppingCart.contains(0, DoveSoap), Matchers.is(false));
    }

}
