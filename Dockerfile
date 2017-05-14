FROM openjdk:8u121-jdk-alpine AS build

WORKDIR /build-env
ADD . /build-env
RUN ./gradlew build

FROM openjdk:8u121-jre-alpine
COPY --from=build /build-env/build/libs/graphql-demo-service.jar /app/graphql-demo-service.jar

EXPOSE 8080
CMD java -jar /app/graphql-demo-service.jar
