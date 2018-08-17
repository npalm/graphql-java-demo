package ofouro.code.graphql.demo.service;

import ofouro.code.graphql.demo.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findByTalkId(Long id);

    Page<Comment> findAll(Pageable pageable);
}
