import lombok.val;
import org.foo.bar.Product;
import org.foo.bar.ShoppingCart;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("On adding single type products")
public class AddSingleTypeProductToShoppingCartTest {

    @Test
    public void shouldCheckIfProductsWereAdded() {
        Product product = new Product("Dove Soap", new BigDecimal("39.99"));

        val shoppingCart = ShoppingCart.empty().add(5, product);

        assertThat("expected " + 5 + " times" + product, shoppingCart.contains(5, product), Matchers.is(true));
    }

    @Test
    public void shouldNotContainWhenQuantityIsDifferent() {
        Product product = new Product("Dove Soap", new BigDecimal("39.99"));

        val shoppingCart = ShoppingCart.empty().add(5, product);

        assertThat("not expected to have " + 6 + " times" + product, shoppingCart.contains(6, product), Matchers.is(false));
    }

}
