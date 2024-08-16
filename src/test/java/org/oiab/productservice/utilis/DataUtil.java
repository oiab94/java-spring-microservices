package org.oiab.productservice.utilis;

import org.oiab.productservice.model.Product;

import java.math.BigDecimal;

public class DataUtil {
	public static Product createTestProductA() {
		return
			Product
				.builder()
				.name("Test A")
				.description("Desciption test A")
				.price(new BigDecimal("123.45"))
				.build();
	}

	public static Product createTestProductB() {
		return
			Product
				.builder()
				.name("Test B")
				.description("Desciption test B")
				.price(new BigDecimal("234.56"))
				.build();
	}
}
