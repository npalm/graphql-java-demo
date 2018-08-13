package demo.publishers;

import demo.CommentUpdate;
import demo.model.Talk;
import demo.service.TalkRepository;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.observables.ConnectableObservable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class CommentPublisher {

    private static final Logger LOG = LoggerFactory.getLogger(CommentPublisher.class);


    @Autowired
    private TalkRepository talkRepository;

    final Flowable<CommentUpdate> publisher;


    public ObservableEmitter<CommentUpdate> emitter;

    public CommentPublisher() {
        Observable<CommentUpdate> commentUpdateObservable = Observable.create(emitter -> {
            this.emitter = emitter;
            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
            executorService.scheduleAtFixedRate(newComment(emitter), 0, 2, TimeUnit.SECONDS);

        });

        ConnectableObservable<CommentUpdate> connectableObservable = commentUpdateObservable.share().publish();
        connectableObservable.connect();


        publisher = connectableObservable.toFlowable(BackpressureStrategy.BUFFER);
    }

    public Runnable newComment(ObservableEmitter<CommentUpdate> emitter) {
        return () -> {
            List<CommentUpdate> update = getUpdates();
            if (update != null) {
                emitStocks(emitter, update);
            }
        };
    }

    private void emitStocks(ObservableEmitter<CommentUpdate> emitter, List<CommentUpdate> stockPriceUpdates) {
        for (CommentUpdate stockPriceUpdate : stockPriceUpdates) {
            try {
                emitter.onNext(stockPriceUpdate);
            } catch (RuntimeException e) {
                LOG.error("Cannot send StockUpdate", e);
            }
        }
    }


    public Flowable<CommentUpdate> getPublisher(Long talkId) {
        Flowable<CommentUpdate> result = publisher;
        if (talkId == null) {
            return result;
        }

        Optional<Talk> talk = talkRepository.findById(Long.valueOf(talkId));
        if (talk.isPresent()) {
            result = publisher.filter(commentUpdate -> commentUpdate.getTalkTitle().equals(talk.get().getTitle()));
        }
        return result;
    }

    private List<CommentUpdate> getUpdates() {
        List<CommentUpdate> updates = new ArrayList<>();

        while (new Random().nextInt(4) < 2) {
            updates.add(createComment());
        }
        LOG.info("publish: " + updates.size());
        return updates;
    }


    private CommentUpdate createComment() {

        return new CommentUpdate("test",
                "Niek", "grapql" + new Random().nextInt(100),
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
    }


}
