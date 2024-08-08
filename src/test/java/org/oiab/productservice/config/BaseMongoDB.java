package org.oiab.productservice.config;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * Permite la configuración de un contenedor de MongoDB para pruebas unitarias.
 */
@Testcontainers
public class BaseMongoDB {
  private static final Logger log = LoggerFactory.getLogger(BaseMongoDB.class);
  @Container
  static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

  @BeforeAll
  static void startMongoDBContainer() {
    log.info("Starting MongoDB container...");
    mongoDBContainer.start();
  }

  @AfterAll
  static void stopMongoDBContainer() {
    log.info("Stopping MongoDB container...");
    mongoDBContainer.stop();
  }

  @DynamicPropertySource
  static void mongoDBProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
  }
}
