package pl.edu.agh.ki.sr.bookstore.actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.ki.sr.bookstore.response.SearchResponse;

public class SearchActor extends AbstractActor {

    private static final Logger logger = LoggerFactory.getLogger(SearchActor.class);

    @Override
    public AbstractActor.Receive createReceive() {
        return receiveBuilder()
                .match(String.class, o -> {
                    context().child("search1").get().forward(o, context());
                    context().child("search2").get().forward(o, context());
                })
                .match(SearchResponse.class, o -> {
                    context().child("result").get().forward(o, context());
                })
                .match(Integer.class, i -> {
                    context().stop(self());
                })
                .matchAny(o -> logger.info("received unknown message"))
                .build();
    }

    @Override
    public void preStart() throws Exception {
        context().actorOf(Props.create(SearchActorChild.class, "file1.txt"), "search1");
        context().actorOf(Props.create(SearchActorChild.class, "file2.txt"), "search2");
        context().actorOf(Props.create(SearchActorResult.class), "result");
    }

}
