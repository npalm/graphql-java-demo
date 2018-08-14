package demo.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import demo.model.Comment;
import demo.model.Conference;
import demo.model.Person;
import demo.model.Talk;
import demo.publishers.CommentPublisher;
import demo.service.CommentRepository;
import demo.service.ConferenceRepository;
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
    private ConferenceRepository conferenceRepository;

    @Autowired
    private CommentPublisher commentPublisher;

    public Person addPerson(final InputPerson person) {
        return this.personRepository.save(person.convert());
    }

    public Talk addTalk(final InputTalk talk) {
        return this.talkRepository.save(talk.convert());
    }

    public Conference addConference(final InputConference conference) {
        return this.conferenceRepository.save(conference.convert());
    }

    public Conference addTalkToConference(final Long confenceId, final Long talkId) {
        Conference conference = conferenceRepository.findById(confenceId).get();
        conference.getTalks().add(talkRepository.findById(talkId).get());
        return conferenceRepository.save(conference);
    }

    public Talk addSpeakerToTalk(final Long talkId, final Long speakerId) {
        Talk talk = talkRepository.findById(talkId).get();
        talk.getSpeakers().add(personRepository.findById(speakerId).get());
        return talkRepository.save(talk);
    }

    public Comment addComment(final InputComment comment) {
        Optional<Talk> talk = talkRepository.findById(comment.getTalkId());
        Optional<Person> person = personRepository.findById(comment.getAuthorId());

        Comment savedComment = commentRepository.save(new Comment(comment.getComment(), person.get(), talk.get()));
        commentPublisher.publish(savedComment);

        return savedComment;
    }

}
