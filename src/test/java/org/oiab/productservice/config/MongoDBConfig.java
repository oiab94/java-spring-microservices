package org.oiab.productservice.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@Testcontainers
@Slf4j
public class MongoDBConfig {
	@Container
	 static  MongoDBContainer MY_MONGO_CONTAINER = new MongoDBContainer("mongo:latest")
			.withExposedPorts(27017);

	@DynamicPropertySource
	static void containerProperties(DynamicPropertyRegistry registry) {
		log.info("ADD ContainerProperties");
		registry.add("spring.data.mongodb.uri", MY_MONGO_CONTAINER::getReplicaSetUrl);
		registry.add("spring.data.mongodb.database", () -> "products-test");

		log.info("URI: " + MY_MONGO_CONTAINER.getReplicaSetUrl());
	}

	@BeforeAll
	static void startMongoDB() {
		log.info("START MongoContainer: " + MY_MONGO_CONTAINER.getContainerName());
		MY_MONGO_CONTAINER.start();
	}
}
