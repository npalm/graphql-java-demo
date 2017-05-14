package demo.service;

import com.oembedler.moon.graphql.engine.stereotype.GraphQLField;
import com.oembedler.moon.graphql.engine.stereotype.GraphQLIgnore;
import com.oembedler.moon.graphql.engine.stereotype.GraphQLObject;
import demo.model.Person;
import demo.model.Talk;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@GraphQLObject
public class RootQueries {

    @GraphQLIgnore
    @Autowired
    private PersonRepository personRepository;

    @GraphQLIgnore
    @Autowired
    private TalkRepository talkRepository;

    @GraphQLField("persons")
    public List<Person> persons() {
        return personRepository.findAll();
    }

    @GraphQLField("talks")
    public List<Talk> talks() {
        return talkRepository.findAll();
    }
}