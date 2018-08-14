package demo.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import demo.model.Conference;
import demo.model.Person;
import demo.model.Talk;
import demo.service.ConferenceRepository;
import demo.service.PersonRepository;
import demo.service.PersonService;
import demo.service.TalkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class Query implements GraphQLQueryResolver {


    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TalkRepository talkRepository;

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Autowired
    private PersonService personService;

    public List<Person> persons(final InputPerson filter) {
        return StreamSupport.stream(personService.findPerson(filter.convert()).spliterator(), false).collect(Collectors.toList());
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
