package org.oiab.productservice.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@Testcontainers
@AllArgsConstructor
@Slf4j
public class MongoDBConfig {
	@Container
	 static final MongoDBContainer MY_MONGO_CONTAINER = new MongoDBContainer("mongo:latest");

	@BeforeAll
	static void startMongoDB() {
		log.info("START MongoTestContainer");
		MY_MONGO_CONTAINER.start();
	}

	@AfterAll
	static void stopMongoDB() {
		log.info("STOP MongoTestContainer");
		MY_MONGO_CONTAINER.stop();
	}
}
