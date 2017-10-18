package demo.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import demo.model.Person;
import demo.service.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {

    @Autowired
    private PersonRepository personRepository;

    public Person addPerson(final String name) {
        return personRepository.save(new Person(name));
    }

}
