#-- JAVA --#
FROM openjdk:17
LABEL authors="Oscar"

# Add the jar file to the container
ADD target/product-service.jar product-service.jar

# Expose the port the service runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "/product-service.jar"]