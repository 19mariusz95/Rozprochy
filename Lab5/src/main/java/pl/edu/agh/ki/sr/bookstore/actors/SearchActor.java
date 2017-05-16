package pl.edu.agh.ki.sr.bookstore.actors;

import akka.actor.AbstractActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SearchActor extends AbstractActor {

    private static final Logger logger = LoggerFactory.getLogger(SearchActor.class);
    private final ExecutorService executors = Executors.newFixedThreadPool(2);

    @Override
    public AbstractActor.Receive createReceive() {

        return receiveBuilder()
                .match(String.class, o -> {
                    Future<String> stringFuture = find("file1.txt", o);
                    Future<String> stringFuture1 = find("file2.txt", o);
                    String s1 = stringFuture.get();
                    if(s1 != null) {
                        getSender().tell("[result] " + s1, getSelf());
                        return;
                    }
                    s1 = stringFuture1.get();
                    if(s1 != null) {
                        getSender().tell("[result] " + s1, getSelf());
                        return;
                    }
                    getSender().tell("[result] " + o + " not found", getSelf());
                })
                .matchAny(o -> logger.info("received unknown message"))
                .build();
    }

    private Future<String> find(String fileName, String o) throws FileNotFoundException {
        String title = o.substring(o.indexOf(" ") + 1);
        File file = new File(fileName);
        Scanner scanner = new Scanner(new FileInputStream(file));
        return executors.submit(() -> {
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                if (s.startsWith(title)) {
                    return s;
                }
            }
            return null;
        });
    }

}
