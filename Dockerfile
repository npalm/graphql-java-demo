FROM node:10.9-alpine AS web-build

WORKDIR /build
ADD react-web /build

RUN apk add yarn
RUN yarn && yarn build
RUN find build

FROM openjdk:8u171-jre-alpine3.8

WORKDIR /build
COPY --from=web-build /build/build /build/src/main/resources/public
ADD . /build
RUN ./gradlew build

FROM openjdk:8u171-jre-alpine3.8
COPY --from=java-build /build/build/libs/graphql-demo-service.jar /app/graphql-demo-service.jar

EXPOSE 8080
CMD java -jar /app/graphql-demo-service.jar