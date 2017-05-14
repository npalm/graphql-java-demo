package demo.service;

import demo.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface PersonRepository extends CrudRepository<Person, Long> {

    Person findById(Long id);

    Person findByName(String name);

    Page<Person> findAll(Pageable page);
}
