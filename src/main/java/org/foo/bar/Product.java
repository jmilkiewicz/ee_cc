package org.foo.bar;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Product {
    private final String name;
    private final BigDecimal unitPrice;
}
