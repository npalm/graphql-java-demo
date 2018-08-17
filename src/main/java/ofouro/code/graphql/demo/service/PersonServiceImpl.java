package ofouro.code.graphql.demo.service;

import ofouro.code.graphql.demo.model.Person;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> find(final Person person) {
        if (person == null) {
            return personRepository.findAll();
        }

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();

        return StreamSupport.stream(personRepository.findAll(Example.of(person, matcher)).spliterator(), false)
                .collect(Collectors.toList());
    }


    public Optional<Person> findById(final Long id) {
        return personRepository.findById(id);
    }


}