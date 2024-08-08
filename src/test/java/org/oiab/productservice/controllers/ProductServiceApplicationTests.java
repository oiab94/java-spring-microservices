package org.oiab.productservice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.oiab.productservice.config.BaseMongoDB;
import org.oiab.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ProductServiceApplicationTests extends BaseMongoDB {
	private final ProductRepository underTest;

	@Autowired
	public ProductServiceApplicationTests(ProductRepository underTest) {
		this.underTest = underTest;
	}


	@Test
	@DisplayName("Should be create a product")
	void shouldBeCreateAProduct() {
		underTest.findAll();
	}
}
