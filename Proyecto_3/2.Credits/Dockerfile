FROM openjdk:11
COPY ./target/credits-0.0.1-SNAPSHOT.jar credits.jar
ARG CONFIG_SERVER_URL
ENV CONFIG_SERVER_URL=${CONFIG_SERVER_URL}
EXPOSE 8082
CMD ["java", "-jar", "credits.jar"]