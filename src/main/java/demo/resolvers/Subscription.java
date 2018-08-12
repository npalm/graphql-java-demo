package demo.resolvers;


import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import demo.CommentUpdate;
import demo.publishers.CommentPublisher;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

import demo.publishers.StockTickerPublisher;

import java.util.List;

@Component
class Subscription implements GraphQLSubscriptionResolver {

    private StockTickerPublisher stockTickerPublisher;

    Subscription(StockTickerPublisher stockTickerPublisher) {
        this.stockTickerPublisher = stockTickerPublisher;
    }

    Publisher<StockPriceUpdate> stockQuotes(List<String> stockCodes) {
        return stockTickerPublisher.getPublisher(stockCodes);
    }


}
