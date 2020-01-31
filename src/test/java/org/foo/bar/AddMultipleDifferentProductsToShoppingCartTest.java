package org.foo.bar;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.foo.bar.TestFixtures.AxeDeo;
import static org.foo.bar.TestFixtures.DoveSoap;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("On adding different products to shopping cart")
public class AddMultipleDifferentProductsToShoppingCartTest {
    private final int doveSoapQuantity = 2;
    private final int axeDeoQuantity = 2;
    private ShoppingCart shoppingCart;

    @BeforeEach
    void setUpShoppingCartWithMultipleTypesOfProducts() {
        shoppingCart = ShoppingCart.empty()
                .add(doveSoapQuantity, DoveSoap)
                .add(axeDeoQuantity, AxeDeo)
                .withTaxRate(new BigDecimal("12.5"));
    }

    @Test
    public void shouldContainAllAddedProducts() {
        assertQuantityOfProduct(doveSoapQuantity, DoveSoap);
        assertQuantityOfProduct(axeDeoQuantity, AxeDeo);
    }

    private void assertQuantityOfProduct(int quantity, Product product) {
        assertThat("expected " + quantity + " times " + product,
                shoppingCart.contains(quantity, product), Matchers.is(true));
    }

    @Test
    public void shallCalculateTotalPriceIncludingTax() {
        assertThat(
                shoppingCart.getTotalPrice(), Matchers.is(new BigDecimal("314.96")));
    }

    @Nested
    @DisplayName("shall calculate total tax value")
    class CalculateTaxValue {

        //TODO probably makes sense to extract to parametrised test
        @Test
        public void shouldCalculateTotalSalesTax1() {
            ShoppingCart shoppingCart = ShoppingCart.empty().add(1, TestFixtures.DoveSoap)
                    .withTaxRate(new BigDecimal("10"));

            assertThat(
                    shoppingCart.getTotalSalesTax(), Matchers.is(new BigDecimal("4.00")));
        }

        @Test
        public void shouldCalculateTotalSalesTax2() {
            Product product = new Product.ProductBuilder().unitPrice(new BigDecimal(101)).build();

            ShoppingCart shoppingCart = ShoppingCart.empty().add(1, product)
                    .withTaxRate(new BigDecimal("30"));

            assertThat(
                    shoppingCart.getTotalSalesTax(), Matchers.is(new BigDecimal("30.30")));
        }

        @Test
        public void shouldCalculateTotalSalesTax3() {
            ShoppingCart shoppingCart = ShoppingCart.empty().add(8, DoveSoap)
                    .withTaxRate(new BigDecimal("12.5"));

            assertThat(
                    shoppingCart.getTotalSalesTax(), Matchers.is(DoveSoap.getUnitPrice()));
        }
    }


}
