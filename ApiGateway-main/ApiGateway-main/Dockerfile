# Use official OpenJDK 17 image
FROM openjdk:17

# Set working directory
WORKDIR /app

# Expose the port your app runs on (adjust if needed)
EXPOSE 8082

# Add the JAR file to the container
ADD target/Gateway-0.0.1-SNAPSHOT.jar Gateway.jar

# Set the entry point
ENTRYPOINT ["java", "-jar", "Gateway.jar"]
