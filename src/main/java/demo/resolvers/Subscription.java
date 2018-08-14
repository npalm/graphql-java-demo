package demo.resolvers;


import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import demo.publishers.CommentPublisher;
import demo.publishers.CommentUpdate;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

@Component
class Subscription implements GraphQLSubscriptionResolver {

    private CommentPublisher commentPublisher;

    Subscription(CommentPublisher commentPublisher) {
        this.commentPublisher = commentPublisher;
    }

    Publisher<CommentUpdate> comments() {
        return commentPublisher.getPublisher(null);
    }
}
