package pl.edu.agh.ki.sr.bookstore.actors;

import akka.actor.AbstractActor;
import pl.edu.agh.ki.sr.bookstore.response.SearchResponse;

public class SearchActorResult extends AbstractActor {

    private SearchResponse previousResponse;

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(SearchResponse.class, o -> {
                        if(previousResponse != null) {
                            if(!previousResponse.isFound()){
                                getSender().tell(o, getSender());
                            }
                        } else {
                            previousResponse = o;
                            if(o.isFound()) {
                                getSender().tell(o, getSender());
                            }
                    }
                })
                .build();
    }
}
