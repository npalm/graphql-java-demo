[![Build Status](https://travis-ci.com/npalm/graphql-java-demo.svg?branch=master)](https://travis-ci.com/npalm/graphql-java-demo)
[![Maintainability](https://api.codeclimate.com/v1/badges/f569acb75ecae1cff403/maintainability)](https://codeclimate.com/github/npalm/graphql-java-demo/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/f569acb75ecae1cff403/test_coverage)](https://codeclimate.com/github/npalm/graphql-java-demo/test_coverage)

# GraphQL demo service

This repo contains a simple spring boot services to showcase an GraphQL implementation. The implementation is based on graphql-java-tools a GraphQL java library inspired by Apollo.

- [graphql-java-common](https://github.com/graphql-java/graphql-java-tools) - GraphQL implementation which is a schema first implementation of GraphQL.
- [graphql-spring-boot](https://github.com/graphql-java/graphql-spring-boot) - A Sprinb Boot Starter for GraphQL.
- Spring Boot 2.x

## Application.
The application implements GraphQL on top of JPA repositories. The application provides basic functionality to store Conferences, for a conferences a set of Talks, for each Talk a set of speakers. And for each Talk comments can be made. 

The graphQL API contains queries, mutations and subscriptions. For the Conference a very basic query is available. For Person and Talk a basic filter is implemented and for Comments an experiment with pagination is available. For more details have a look at the [schema](src/main/resources/demo.graphqls)


## Build and Run

### Gradle
```
./gradlew bootRun
```
Open en browser on http://localhost:8080/graphiql, for play around with GraphiQL interface.

### Docker
Build image
```
docker build -t grahpql .
```
Run
```
docker run -it --rm -p 8080:8080 graphql 
```
