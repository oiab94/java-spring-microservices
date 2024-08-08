package org.oiab.productservice.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.oiab.productservice.config.BaseMongoDB;
import org.oiab.productservice.model.Product;
import org.oiab.productservice.repositories.ProductRepository;
import org.oiab.productservice.utils.TestDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class ProductServiceApplicationTests extends BaseMongoDB {
	private final ProductRepository underTest;
	private MockMvc mockMvc;
	private ObjectMapper objectMapper;

	// * Inyección de dependencias
	@Autowired
	public ProductServiceApplicationTests(ProductRepository underTest, MockMvc mockMvc) {
		this.underTest = underTest;
		this.mockMvc = mockMvc;
		this.objectMapper = new ObjectMapper();
	}

	/**
	 * Antes de cada prueba, se eliminan todos los registros de la colección de productos.
	 */
	@BeforeEach
	void setUp() {
		underTest.deleteAll();
	}

	@Test
	@DisplayName("Should be create a product and return 201")
	void shouldBeCreateAProductSuccessfullyAndReturnHttp201() throws Exception {
		Product product = TestDataUtil.createProductA();
		String productJson = objectMapper.writeValueAsString(product);


		// Test
		mockMvc.perform(
				MockMvcRequestBuilders
					.post("/api/products")
					.contentType("application/json")
					.content(productJson)
		).andExpect(
				MockMvcResultMatchers.status().isCreated()
		);
	}

	@Test
	@DisplayName("Should be created a product and return the product created")
	void shouldBeCreateAProductSuccessfullyAndReturnTheProductCreated() throws Exception {
		Product product = TestDataUtil.createProductA();
		String productJson = objectMapper.writeValueAsString(product);

		// Test
		mockMvc.perform(
			MockMvcRequestBuilders
				.post("/api/products")
				.contentType("application/json")
				.content(productJson)
		).andExpect(
				MockMvcResultMatchers.status().isCreated()
		).andExpect(
				MockMvcResultMatchers.jsonPath("$.id").isString()
		).andExpect(
				MockMvcResultMatchers.jsonPath("$.name").value(product.getName())
		);
	}

	@Test
	@DisplayName("Should be return all products")
	void shouldBeReturnAllProducts() throws Exception {
		Product productA = TestDataUtil.createProductA();
		Product productB = TestDataUtil.createProductB();

		underTest.save(productA);
		underTest.save(productB);

		// Test
		mockMvc.perform(
				MockMvcRequestBuilders
					.get("/api/products")
					.contentType("application/json")
					.content("")
		).andExpect(
				MockMvcResultMatchers.status().isOk()
		).andExpect(
				MockMvcResultMatchers.jsonPath("$[0].name").value(productA.getName())
		).andExpect(
				MockMvcResultMatchers.jsonPath("$[1].name").value(productB.getName())
		);
	}
}
