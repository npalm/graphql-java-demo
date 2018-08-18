package ofouro.code.graphql.demo.publishers;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.observables.ConnectableObservable;
import lombok.extern.slf4j.Slf4j;
import ofouro.code.graphql.demo.model.Comment;
import ofouro.code.graphql.demo.model.Talk;
import ofouro.code.graphql.demo.service.TalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class CommentPublisher {


    @Autowired
    private TalkService talkService;

    private final Flowable<CommentUpdate> publisher;

    private ObservableEmitter<CommentUpdate> emitter;

    public CommentPublisher() {
        Observable<CommentUpdate> commentUpdateObservable = Observable.create(emitter -> {
            this.emitter = emitter;
        });

        ConnectableObservable<CommentUpdate> connectableObservable = commentUpdateObservable.share().publish();
        connectableObservable.connect();


        publisher = connectableObservable.toFlowable(BackpressureStrategy.BUFFER);
    }

    public void publish(final Comment comment) {
        emitter.onNext(new CommentUpdate(comment.getComment(), comment.getAuthor().getName(), comment.getTalk().getTitle(), comment.getCreatedOn().toString()));
    }


    public Flowable<CommentUpdate> getPublisher(Long talkId) {
        Flowable<CommentUpdate> result = publisher;
        if (talkId == null) {
            return result;
        }

        Optional<Talk> talk = talkService.findById(Long.valueOf(talkId));
        if (talk.isPresent()) {
            result = publisher.filter(commentUpdate -> commentUpdate.getTalkTitle().equals(talk.get().getTitle()));
        }
        return result;
    }

}
