package ofouro.code.graphql.demo.service;

import ofouro.code.graphql.demo.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {


    List<Person> find(Person person);

    Optional<Person> findById(Long id);

    Person save(Person person);
}