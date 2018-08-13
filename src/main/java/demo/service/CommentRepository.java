package demo.service;

import demo.model.Comment;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findByTalkId(Long id);

    List<Comment> findAll();
}
