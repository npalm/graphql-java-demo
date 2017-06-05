package demo.service;

import demo.model.Person;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface PersonRepository extends CrudRepository<Person, String> {

    Person findById(Long id);

    Person findByName(String name);

    List<Person> findAll();
}
