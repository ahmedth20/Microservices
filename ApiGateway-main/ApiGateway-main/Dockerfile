# Use official OpenJDK 17 (Debian-based) image
FROM openjdk:17-buster

# Set working directory
WORKDIR /app

# Expose the port your app runs on (adjust if needed)
EXPOSE 8082

# Install necessary utilities (curl, bash)
RUN apt-get update && apt-get install -y \
    curl \
    bash

# Download wait-for-it script
RUN curl -sSL https://github.com/vishnubob/wait-for-it/raw/master/wait-for-it.sh -o /usr/local/bin/wait-for-it.sh && \
    chmod +x /usr/local/bin/wait-for-it.sh

# Add the JAR file to the container
ADD target/Gateway-0.0.1-SNAPSHOT.jar Gateway.jar

# Set the entry point to wait for Eureka to be ready before starting the Gateway
ENTRYPOINT ["/bin/sh", "-c", "wait-for-it.sh eurekaserver:8761 -- java -jar Gateway.jar"]
