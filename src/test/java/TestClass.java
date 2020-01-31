import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyIterable;

public class TestClass {

    @Test
    public void shallReturnEmptyWhenInputIsEmpty() {
        assertThat(List.of(), emptyIterable());
    }
}
