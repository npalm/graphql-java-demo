[![Build Status](https://travis-ci.com/npalm/graphql-java-demo.svg?branch=master)](https://travis-ci.com/npalm/graphql-java-demo)
[![Maintainability](https://api.codeclimate.com/v1/badges/f569acb75ecae1cff403/maintainability)](https://codeclimate.com/github/npalm/graphql-java-demo/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/f569acb75ecae1cff403/test_coverage)](https://codeclimate.com/github/npalm/graphql-java-demo/test_coverage)

# GraphQL demo service

Simple Java project to play around with spring boot starter for GraphQL including annotations.

## Build and Run
```
./gradlew bootRun
```
Open en browser on http://localhost:8080, for play around with GraphiQL

## Docker
Build image
```
docker build -t grahpql .
```
Run
```
docker run -it --rm -p 8080:8080 graphql 
```
