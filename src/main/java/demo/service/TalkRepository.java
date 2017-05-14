package demo.service;

import demo.model.Talk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface TalkRepository extends CrudRepository<Talk, Long> {

    Talk findById(Long id);

    Talk findByTitle(String title);

    Page<Talk> findAll(Pageable page);
}
