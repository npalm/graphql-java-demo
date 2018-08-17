package ofouro.code.graphql.demo.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import ofouro.code.graphql.demo.model.Comment;
import ofouro.code.graphql.demo.model.Conference;
import ofouro.code.graphql.demo.model.Person;
import ofouro.code.graphql.demo.model.Talk;
import ofouro.code.graphql.demo.service.CommentRepository;
import ofouro.code.graphql.demo.service.ConferenceRepository;
import ofouro.code.graphql.demo.service.PersonService;
import ofouro.code.graphql.demo.service.TalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {


    @Autowired
    private TalkService talkService;

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private CommentRepository commentRepository;

    public List<Person> persons(final InputPerson filter) {
        return personService.find(InputPerson.convert(filter));
    }

    public Optional<Person> person(final Long id) {
        return personService.findById(id);
    }

    public List<Talk> talks(final InputTalk filter) {
        return talkService.find(InputTalk.convert(filter));
    }

    public Optional<Talk> talk(final Long id) {
        return talkService.findById(id);
    }

    public List<Conference> conferences() {
        return conferenceRepository.findAll();
    }

    public Optional<Conference> conference(final Long id) {
        return conferenceRepository.findById(id);
    }

    public CommentPageableResponse comments(final InputPage inputPage) {
        Page<Comment> comments = commentRepository.findAll(InputPage.convert(inputPage));
        return new CommentPageableResponse(comments);
    }
}
