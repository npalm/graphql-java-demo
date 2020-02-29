package ofouro.code.graphql.demo.service;

import ofouro.code.graphql.demo.model.Talk;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@Repository
@RepositoryRestResource(collectionResourceRel = "talk", path = "talks")
public interface TalkRepository extends CrudRepository<Talk, Long>, QueryByExampleExecutor<Talk> {

    List<Talk> findAll();
}
