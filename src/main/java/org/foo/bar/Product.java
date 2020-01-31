package org.foo.bar;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private final String name;
    private final BigDecimal unitPrice;
}
