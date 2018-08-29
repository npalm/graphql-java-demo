package ofouro.code.graphql.demo.resolvers;


import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import ofouro.code.graphql.demo.model.Comment;
import ofouro.code.graphql.demo.publishers.CommentPublisher;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

@Component
class Subscription implements GraphQLSubscriptionResolver {

    private CommentPublisher commentPublisher;

    Subscription(CommentPublisher commentPublisher) {
        this.commentPublisher = commentPublisher;
    }

    Publisher<Comment> comments() {
        return commentPublisher.getPublisher();
    }
}
