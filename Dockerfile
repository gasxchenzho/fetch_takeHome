# Use an official Java Runtime Environment (JRE) base image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container to /app
WORKDIR /app

# Copy the jar file into the /app directory in your Docker image
COPY target/fetch-take-home-0.0.1-SNAPSHOT.jar /app

# Make port 8080 available outside the container
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java","-jar","/app/fetch-take-home-0.0.1-SNAPSHOT.jar"]
