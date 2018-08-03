package demo.service;

import demo.model.Person;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface PersonRepository extends CrudRepository<Person, Long> {

    Person findByName(String name);

    List<Person> findAll();
}
