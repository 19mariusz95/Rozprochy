package pl.edu.agh.ki.sr.bookstore.actors;

import akka.actor.AbstractActor;
import akka.actor.Props;

public class SearchListener extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, o -> {
                    context().system().actorOf(Props.create(SearchActor.class)).forward(o, context());
                })
                .build();
    }
}
