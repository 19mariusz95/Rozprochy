package pl.edu.agh.ki.sr.client.actor;

import akka.actor.AbstractActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.ki.sr.bookstore.actors.SearchActor;

public class LocalActor extends AbstractActor {

    private static final Logger logger = LoggerFactory.getLogger(SearchActor.class);
    private static final String BOOKSTORE_PATH = "akka.tcp://bookstore@127.0.0.1:2552/user/";

    @Override
    public AbstractActor.Receive createReceive() {

        return receiveBuilder()
                .match(String.class, s -> {
                    String[] cmd = s.split(" ");
                    switch (cmd[0]) {
                        case "search":
                            getContext().actorSelection(BOOKSTORE_PATH + "search").tell(s, getSelf());
                            break;
                        case "order":
                            getContext().actorSelection(BOOKSTORE_PATH+"order").tell(s, getSelf());
                            break;
                        case "text":
                            getContext().actorSelection(BOOKSTORE_PATH+"text").tell(s, getSelf());
                            break;
                        case "[result]":
                            System.out.println(s);
                        default:
                            System.out.println(s);
                            break;

                    }
                })
                .matchAny(o -> logger.info("received unknown message"))
                .build();
    }
}
