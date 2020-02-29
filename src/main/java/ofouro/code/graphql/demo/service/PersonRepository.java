package ofouro.code.graphql.demo.service;

import ofouro.code.graphql.demo.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@Repository
@RepositoryRestResource(collectionResourceRel = "person", path = "persons")
public interface PersonRepository extends CrudRepository<Person, Long>, QueryByExampleExecutor<Person> {

    List<Person> findAll();
}
