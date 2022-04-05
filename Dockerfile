FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/ms-wallet-transaction-*-SNAPSHOT.jar
COPY ${JAR_FILE} ms-wallet-transaction.jar
RUN addgroup -S bootcampgroup && adduser -S bootcampuser -G bootcampgroup
RUN mkdir -p /opt/logs/ms-wallet-transaction
RUN chown -R bootcampuser:bootcampgroup /opt/logs/ms-wallet-transaction
USER bootcampuser:bootcampgroup
ENTRYPOINT ["java", "-jar", "/ms-wallet-transaction.jar"]