package demo.resolvers;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import demo.model.Person;
import demo.model.Talk;
import demo.service.PersonRepository;
import demo.service.TalkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Query implements GraphQLRootResolver {


    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TalkRepository talkRepository;

    public List<Person> persons() {
        return personRepository.findAll();
    }

    public Person person(final String name) {
        return personRepository.findByName(name);
    }


    public List<Talk> talks() {
        return talkRepository.findAll();
    }

    public Talk talk(final String title) {
        return talkRepository.findByTitle(title);
    }


}
