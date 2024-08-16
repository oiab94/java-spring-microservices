package org.oiab.productservice.controllers;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.oiab.productservice.config.MongoDBConfig;
import org.oiab.productservice.model.Product;
import org.oiab.productservice.repositories.ProductRepository;
import org.oiab.productservice.utilis.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@Slf4j
public class ProductServiceApplicationTests extends MongoDBConfig {
	private final MockMvc mockMvc;
	private final ProductRepository underTest;
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	public ProductServiceApplicationTests(MockMvc mockMvc, ProductRepository underTest) {
		this.mockMvc = mockMvc;
		this.underTest = underTest;
	}

	@AfterEach
	void deleteTable() {
		underTest.deleteAll();
	}

	@Test
	void testThatSavedAndProductAndReturnHttpCreated() {
		try {
			Product product = DataUtil.createTestProductA();
			String productJson = objectMapper.writeValueAsString(product);

			mockMvc.perform(
				MockMvcRequestBuilders
					.post("/api/products")
					.contentType(MediaType.APPLICATION_JSON)
					.content(productJson)
			).andExpect(
				MockMvcResultMatchers.status().isCreated()
			);
			log.info("OK testThatSavedAndAuthorAndReturnHttpCreated");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	void testThatSavedAndProductAndReturnProductCreated() {
		try {
			Product product = DataUtil.createTestProductA();
			String productJson = objectMapper.writeValueAsString(product);

			mockMvc.perform(
				MockMvcRequestBuilders
					.post("/api/products")
					.contentType(MediaType.APPLICATION_JSON)
					.content(productJson)
			).andExpect(
				MockMvcResultMatchers.jsonPath("id").isString()
			).andExpect(
				MockMvcResultMatchers.jsonPath("price").isNumber()
			);
			log.info("OK testThatSavedAndProductAndReturnProductCreated");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	void testThatSavedAndListAllProductCreated() {
		try {
			Product productA = DataUtil.createTestProductA();
			Product productB = DataUtil.createTestProductB();

			underTest.save(productA);
			underTest.save(productB);

			mockMvc.perform(
				MockMvcRequestBuilders
					.get("/api/products")
			).andExpect(
				MockMvcResultMatchers.status().isOk()
			).andExpect(
				MockMvcResultMatchers.jsonPath("$[0].price").value(productA.getPrice())
			).andExpect(
				MockMvcResultMatchers.jsonPath("$[1].price").value(productB.getPrice())
			);

			log.info("OK testThatSavedAndListAllProductCreated");
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}
}
