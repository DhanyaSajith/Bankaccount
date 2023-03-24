FROM openjdk:8-jdk-alpine
MAINTAINER baeldung.com
EXPOSE 8080
COPY target/bank-account.jar bank-account.jar
ENTRYPOINT ["java","-jar","/bank-account.jar"]