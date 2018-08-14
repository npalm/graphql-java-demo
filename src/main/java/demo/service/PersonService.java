package demo.service;

import demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

@Component
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public Iterable<Person> findPerson(Person person) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();
        Example<Person> example = Example.of(person, matcher);
        return personRepository.findAll(example);
    }

}