package demo.service;

import demo.model.Talk;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface TalkRepository extends CrudRepository<Talk, Long> {

    Talk findById(Long id);

    Talk findByTitle(String title);

    List<Talk> findAll();
}
