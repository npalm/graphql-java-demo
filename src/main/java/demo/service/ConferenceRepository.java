package demo.service;

import demo.model.Conference;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ConferenceRepository extends CrudRepository<Conference, Long> {

    Conference findByName(String name);

    List<Conference> findAll();

}
