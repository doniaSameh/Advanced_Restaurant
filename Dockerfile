# Use the official OpenJDK 19 image as the base image
FROM openjdk:19

# Set the working directory inside the container to /app
WORKDIR /app

# Copy all files and directories from the current directory (the directory containing the Dockerfile) to /app inside the container
COPY . /app

# Run the Maven Wrapper script with the clean and package goals to compile and package the Spring Boot application into a JAR file, skipping unit tests
RUN ./mvnw clean package -DskipTests

# Set the executable permission on the JAR file
RUN chmod +x /app/target/demo-0.0.1-SNAPSHOT.jar

# Set the entrypoint for the container to run the Spring Boot application as a JAR file using the java -jar command
ENTRYPOINT ["java","-jar","/app/target/demo-0.0.1-SNAPSHOT.jar"]
