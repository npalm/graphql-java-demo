FROM node:10.9-alpine AS web-build

WORKDIR /home
ADD react-web /home

RUN apk add yarn
RUN yarn && yarn build
RUN find build

FROM openjdk:11.0.3-jdk-stretch AS java-build

WORKDIR /home
COPY --from=web-build /home/build /home/src/main/resources/public
ADD . /home
RUN ./gradlew build

FROM openjdk:11.0.3-jdk-stretch
COPY --from=java-build /home/build/libs/graphql-demo-service.jar /app/graphql-demo-service.jar

EXPOSE 8080
CMD java -jar /app/graphql-demo-service.jar