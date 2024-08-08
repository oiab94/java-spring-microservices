package org.oiab.productservice.utils;

import org.oiab.productservice.model.Product;

import java.math.BigDecimal;

public class TestDataUtil {
  public static Product createProductA() {
    return Product
            .builder()
            .name("Product A")
            .description("Product A description")
            .price(BigDecimal.valueOf(123.45))
            .build();
  }

  public static Product createProductB() {
    return Product
        .builder()
        .name("Product B")
        .description("Product B description")
        .price(BigDecimal.valueOf(234.56))
        .build();
  }

  public static Product createProductC() {
    return Product
        .builder()
        .name("Product C")
        .description("Product C description")
        .price(BigDecimal.valueOf(345.67))
        .build();
  }
}
