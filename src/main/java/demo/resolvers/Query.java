package demo.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import demo.model.Conference;
import demo.model.Person;
import demo.model.Talk;
import demo.service.CommentRepository;
import demo.service.ConferenceRepository;
import demo.service.PersonRepository;
import demo.service.TalkRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {


    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TalkRepository talkRepository;

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Autowired
    private CommentRepository commentRepository;

    public List<Person> persons(final String name) {
        return StringUtils.isNoneBlank(name) ? personRepository.findByName(name) : personRepository.findAll();
    }

    public Optional<Person> person(final Long id) {
        return personRepository.findById(id);
    }

    public List<Talk> talks() {
        return talkRepository.findAll();
    }

    public Optional<Talk> talk(final Long id) {
        return talkRepository.findById(id);
    }

    public List<Conference> conferences() {
        return conferenceRepository.findAll();
    }

    public Optional<Conference> conference(final Long id) {
        return conferenceRepository.findById(id);
    }

}
