package pl.edu.agh.ki.sr.bookstore.actors;

import akka.actor.AbstractActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.ki.sr.bookstore.response.SearchResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SearchActorChild extends AbstractActor {

    private static final Logger logger = LoggerFactory.getLogger(SearchActor.class);
    private final File file;

    public SearchActorChild(String filename) throws FileNotFoundException {
        file = new File(filename);
        if(!file.exists()) {
            throw new FileNotFoundException();
        }
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, o -> {
                    String title = o.substring(o.indexOf(" ") + 1);
                    Scanner scanner = new Scanner(new FileInputStream(file));
                    while (scanner.hasNextLine()) {
                        String s = scanner.nextLine();
                        if (s.startsWith(title)) {
//                            context().actorSelection("akka.tcp://bookstore@127.0.0.1:2552/user/searchResult").tell("[found] " + s, getSender());
                            context().parent().forward(new SearchResponse(true, s), context());
                            return;
                        }
                    }
//                    context().actorSelection("akka.tcp://bookstore@127.0.0.1:2552/user/searchResult").tell("[not found] " + title, getSender());
                    context().parent().forward(new SearchResponse(false, title), context());
                }).build();
    }

}
