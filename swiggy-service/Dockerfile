# Use a base image with Java 17
FROM openjdk:19-jdk-alpine
# Expose the port your application runs on
EXPOSE 8080
# Copy the jar file from the target directory into the container
ADD ./build/libs/*.jar app.jar
# Start the application
ENTRYPOINT ["java", "-jar", "/app.jar"]