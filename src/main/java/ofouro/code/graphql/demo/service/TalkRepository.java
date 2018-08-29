package ofouro.code.graphql.demo.service;

import ofouro.code.graphql.demo.model.Talk;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TalkRepository extends CrudRepository<Talk, Long>, QueryByExampleExecutor<Talk> {

    List<Talk> findAll();
}
