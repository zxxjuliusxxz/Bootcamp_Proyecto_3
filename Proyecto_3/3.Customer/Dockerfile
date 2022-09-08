FROM openjdk:11
COPY ./target/Customer-0.0.1-SNAPSHOT.jar Customer.jar
ARG CONFIG_SERVER_URL
ENV CONFIG_SERVER_URL=${CONFIG_SERVER_URL}
EXPOSE 8083
CMD ["java", "-jar", "Customer.jar"]