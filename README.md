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
