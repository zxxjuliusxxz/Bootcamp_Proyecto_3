FROM openjdk:11
COPY ./target/Eureka-Server-0.0.1-SNAPSHOT.jar Eureka-Server.jar
ARG CONFIG_SERVER_URL
ENV CONFIG_SERVER_URL=${CONFIG_SERVER_URL}
EXPOSE 8761
CMD ["java", "-jar", "Eureka-Server.jar"]
