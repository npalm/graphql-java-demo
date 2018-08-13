package demo.resolvers;


import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import demo.CommentUpdate;
import demo.publishers.CommentPublisher;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

@Component
class CommentSubscription implements GraphQLSubscriptionResolver {

    private CommentPublisher commentPublisher;

    CommentSubscription(CommentPublisher commentPublisher) {
        this.commentPublisher = commentPublisher;
    }

    Publisher<CommentUpdate> comments() {
        return commentPublisher.getPublisher(null);
    }
}
