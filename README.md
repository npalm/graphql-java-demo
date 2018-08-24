[![Build Status](https://travis-ci.com/npalm/graphql-java-demo.svg?branch=master)](https://travis-ci.com/npalm/graphql-java-demo)
[![Maintainability](https://api.codeclimate.com/v1/badges/f569acb75ecae1cff403/maintainability)](https://codeclimate.com/github/npalm/graphql-java-demo/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/f569acb75ecae1cff403/test_coverage)](https://codeclimate.com/github/npalm/graphql-java-demo/test_coverage)
[![Docker](https://img.shields.io/docker/automated/npalm/graphql-java-demo.svg)](https://hub.docker.com/r/npalm/graphql-java-demo/)


# GraphQL demo service

This repo contains a simple spring boot services to showcase an GraphQL implementation. The implementation is based on graphql-java-tools a GraphQL java library inspired by Apollo.

- [graphql-java-common](https://github.com/graphql-java/graphql-java-tools) - GraphQL implementation which is a schema first implementation of GraphQL.
- [graphql-spring-boot](https://github.com/graphql-java/graphql-spring-boot) - A Sprinb Boot Starter for GraphQL.
- Spring Boot 2.x

## Application.
The application implements GraphQL on top of JPA repositories. The application provides basic functionality to store Conferences, for a conferences a set of Talks, for each Talk a set of speakers. And for each Talk comments can be made.

![model](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/npalm/graphql-java-demo/master/doc/model.plantuml)

The graphQL API contains queries, mutations and subscriptions. For the Conference a very basic query is available. For Person and Talk a basic filter is implemented and for Comments an experiment with pagination is available. For more details have a look at the [schema](src/main/resources/demo.graphqls)

## Run with docker
Pull and run the image.

```
docker run -d --name grapql-demo -p 8088:8080 npalm/graphql-java-demo
```

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

## Example usages

Once the application is running point a browser to [http://localhost:8080/graphiql](http://localhost:8080/graphiql). Which will open the GraphiQL editor. Here you can simple enter GraphQL queries. Since GraphQL is based on a schema you have completion features and documentation directly available in the browser.

The system contains out-of-the box some basic test date. So why not start exploring by entering some queries. So let's get for all the conferences a list of talks with the speakers name.
```graphql
query {
  conferences {
    name
    city
    talks {
      id
      title
      speakers {
        name
      }
    }
  }
}
```

Data can be added via mutations, so next we add John to the databse.
```graphql
mutation {
  addPerson(person: {name: "John Doe"}) {
    id
  }
}
```

Via subscriptions you can get updates via a web socket about new Notes is. So first we subscribe to the notes, enter the following query.
```graphql
subscription {
  comments {
    comment
    author
    talk {
      title
    }
    createdOn
  }
}
```
The response should like as follow.
```
"Your subscription data will appear here after server publication!"
```

Finally make a comment via a mutation in a *new* browser window. Find a talkId in the result of the first query. And the authorId of John in the previous mutation.
```graphql
mutation {
  addComment(comment: {comment: "Cool", author: "Me", talkId: 12}) {
    createdOn
    id
  }
}
```
Have now a look on the subscription window, here the update should be visible.
