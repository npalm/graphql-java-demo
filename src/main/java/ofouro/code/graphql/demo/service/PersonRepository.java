package ofouro.code.graphql.demo.service;

import ofouro.code.graphql.demo.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long>, QueryByExampleExecutor<Person> {

    List<Person> findAll();
}
