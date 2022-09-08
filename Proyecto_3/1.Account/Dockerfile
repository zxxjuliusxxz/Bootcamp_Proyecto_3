FROM openjdk:11
COPY ./target/Account-0.0.1-SNAPSHOT.jar Account.jar
ARG CONFIG_SERVER_URL
ENV CONFIG_SERVER_URL=${CONFIG_SERVER_URL}
EXPOSE 8081
CMD ["java", "-jar", "Account.jar"]