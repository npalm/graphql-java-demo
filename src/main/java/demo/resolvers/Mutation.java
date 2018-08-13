package demo.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import demo.CommentUpdate;
import demo.model.Comment;
import demo.model.Person;
import demo.model.Talk;
import demo.publishers.CommentPublisher;
import demo.service.CommentRepository;
import demo.service.PersonRepository;
import demo.service.TalkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TalkRepository talkRepository;

    @Autowired
    private CommentRepository commentRepository;


    @Autowired
    private CommentPublisher commentPublisher;

    public Person addPerson(final String name) {
        return personRepository.save(new Person(name));
    }

    public Comment addComment(final InputComment comment) {
        Optional<Talk> talk = talkRepository.findById(comment.getTalkId());
        Person person = personRepository.findByName(comment.getAuthorName());

        if (!talk.isPresent() || person == null) {
            return null;
        }

        Comment savedComment = commentRepository.save(new Comment(comment.getComment(), person, talk.get()));
        commentPublisher.emitter.onNext(new CommentUpdate(savedComment.getComment(), savedComment.getAuthor().getName(), savedComment.getTalk().getTitle(), ""));

        return savedComment;
    }

}
