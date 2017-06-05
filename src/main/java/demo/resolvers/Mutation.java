package demo.resolvers;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import demo.model.Person;
import demo.service.PersonRepository;
import demo.service.TalkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLRootResolver {


    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TalkRepository talkRepository;

    public Person addPerson(final String name) {
        return personRepository.save(new Person(name));
    }
}
