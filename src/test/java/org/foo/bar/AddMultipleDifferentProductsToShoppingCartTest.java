package org.foo.bar;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.foo.bar.TestFixtures.AxeDeo;
import static org.foo.bar.TestFixtures.DoveSoap;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("On adding different products to shopping cart")
public class AddMultipleDifferentProductsToShoppingCartTest {

    @Test
    public void shouldContainAllAddedProducts() {
        ShoppingCart shoppingCart = ShoppingCart.empty().add(2, TestFixtures.DoveSoap).add(2, TestFixtures.AxeDeo);

        assertThat("expected " + 2 + " times" + DoveSoap,
                shoppingCart.contains(2, DoveSoap), Matchers.is(true));

        assertThat("expected " + 2 + " times" + AxeDeo,
                shoppingCart.contains(2, DoveSoap), Matchers.is(true));

    }

    @Test
    public void shouldNotContainProductEvenWhenAddedWithQuantityZero() {
        ShoppingCart shoppingCart = ShoppingCart.empty().add(0, TestFixtures.DoveSoap);

        assertThat("not expected to contain" +   DoveSoap,
                shoppingCart.contains(0, DoveSoap), Matchers.is(false));
    }

    @Test
    public void shouldCalculateTotalSalesTaxWhenDefined() {
        ShoppingCart shoppingCart = ShoppingCart.empty().add(2, TestFixtures.DoveSoap)
                .withTaxRate(new BigDecimal("10"));

        assertThat(
                shoppingCart.getTotalSalesTax(), Matchers.is(new BigDecimal("8.00")));
    }

}
