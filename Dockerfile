FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/order-sibs-0.0.1-SNAPSHOT.jar sibs.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/sibs.jar"]