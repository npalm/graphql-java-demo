package ofouro.code.graphql.demo.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import ofouro.code.graphql.demo.model.Comment;
import ofouro.code.graphql.demo.model.Conference;
import ofouro.code.graphql.demo.model.Person;
import ofouro.code.graphql.demo.model.Talk;
import ofouro.code.graphql.demo.publishers.CommentPublisher;
import ofouro.code.graphql.demo.service.CommentRepository;
import ofouro.code.graphql.demo.service.ConferenceRepository;
import ofouro.code.graphql.demo.service.PersonRepository;
import ofouro.code.graphql.demo.service.TalkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
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
        Comment result = null;
        Optional<Talk> talk = talkRepository.findById(comment.getTalkId());

        if (talk.isPresent()) {
            result = commentRepository.save(new Comment(comment.getComment(), ZonedDateTime.now(), comment.getAuthor(), talk.get()));
            commentPublisher.publish(result);
        }

        return result;
    }

}
