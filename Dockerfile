FROM openjdk:8-jdk-alpine
COPY ./target/product-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app/
ENTRYPOINT ["java","-jar","product-0.0.1-SNAPSHOT.jar"]