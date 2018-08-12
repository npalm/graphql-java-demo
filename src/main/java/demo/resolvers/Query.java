package demo.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import demo.model.Comment;
import demo.model.Conference;
import demo.model.Person;
import demo.model.Talk;
import demo.service.CommentRepository;
import demo.service.ConferenceRepository;
import demo.service.PersonRepository;
import demo.service.TalkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public List<Conference> conferences() {
        return conferenceRepository.findAll();
    }

    public Conference conference(final String name) {
        return conferenceRepository.findByName(name);
    }

    public List<Comment> comments(final String author) {
        return this.commentRepository.findByTalkId(personRepository.findByName(author).getId());
    }
}
