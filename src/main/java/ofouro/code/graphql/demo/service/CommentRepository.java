package ofouro.code.graphql.demo.service;

import ofouro.code.graphql.demo.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Repository
@RepositoryRestResource(collectionResourceRel = "conference", path = "conferences")
public interface CommentRepository extends CrudRepository<Comment, Long> {

    Page<Comment> findAll(Pageable pageable);
}
