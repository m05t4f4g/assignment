#FROM openjdk:21-jdk as builder
#WORKDIR /application
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} application.jar
#RUN java -Djarmode=layertools -jar application.jar extract && ls -R application/
#
#
#
#FROM openjdk:21-jdk
#WORKDIR /application
#COPY --from=builder application/dependencies/ ./
#COPY --from=builder application/spring-boot-loader/ ./
#COPY --from=builder application/snapshot-dependencies/ ./
#COPY --from=builder application/application/ ./
#ENTRYPOINT ["java", "-cp", "application", "org.springframework.boot.loader.JarLauncher"]


## Build the application using a multi-stage build
#FROM openjdk:21-jdk AS builder
#
## Specify the JAR file location
#ARG JAR_FILE=target/*.jar
## Copy the JAR file into the container
#COPY ${JAR_FILE} application.jar
#
## Extract the layers
#RUN java -Djarmode=tools -jar application.jar extract --layers --launcher && ls -R application/
#
## Use a clean image for the runtime
#FROM openjdk:21-jdk
#
## Copy the extracted dependencies and the spring-boot-loader to the final image
#COPY --from=builder dependencies/ ./
#COPY --from=builder snapshot-dependencies/ ./
#COPY --from=builder spring-boot-loader/ ./
#COPY --from=builder application/ ./
#
## Start the application with the Spring Boot loader
#ENTRYPOINT ["java", "-cp", "spring-boot-loader/*", "org.springframework.boot.loader.JarLauncher"]


# Use a base image with Java installed
FROM openjdk:21

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY target/*.jar app.jar

# Specify the command to run your application
CMD ["java", "-jar", "app.jar"]