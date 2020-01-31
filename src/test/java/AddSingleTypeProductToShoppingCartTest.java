import org.foo.bar.Product;
import org.foo.bar.ShoppingCart;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("On adding single type products to shopping cart")
public class AddSingleTypeProductToShoppingCartTest {
    private final Product doveSoap = new Product("Dove Soap", new BigDecimal("39.99"));
    private final int doveSoapQuantity = 5;
    private ShoppingCart shoppingCart;

    @BeforeEach
    void setUp() {
        shoppingCart = ShoppingCart.empty().add(doveSoapQuantity, doveSoap);
    }

    @Test
    public void shouldContainExactlyWhatWasAdded() {
        assertThat("expected " + doveSoapQuantity + " times" + doveSoap,
                shoppingCart.contains(doveSoapQuantity, doveSoap), Matchers.is(true));
    }

    @Test
    public void shouldNotContainWhenQuantityIsDifferentToWhatWasAdded() {
        int moreThanAdded = doveSoapQuantity + 1;
        assertThat("not expected to have " + moreThanAdded + " times" + doveSoap, shoppingCart.contains(moreThanAdded, doveSoap), Matchers.is(false));
    }

    @Test
    public void shouldNotContainWhenProductIsDifferentToWhatWasAdded() {
        Product doveSoapOnBargain = new Product(doveSoap.getName(), doveSoap.getUnitPrice().subtract(BigDecimal.ONE));
        assertThat("not expected to have " + doveSoapQuantity + " times" + doveSoapOnBargain, shoppingCart.contains(doveSoapQuantity, doveSoapOnBargain), Matchers.is(false));
    }

    @Test
    public void shallReturnTotalPrice() {
        assertThat(shoppingCart.getTotalPrice(), Matchers.is(new BigDecimal("199.95")));
    }

}
