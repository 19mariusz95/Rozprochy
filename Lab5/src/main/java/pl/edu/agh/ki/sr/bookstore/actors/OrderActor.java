package pl.edu.agh.ki.sr.bookstore.actors;

import akka.actor.AbstractActor;
import akka.actor.OneForOneStrategy;
import akka.actor.SupervisorStrategy;
import akka.japi.pf.DeciderBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.ki.sr.bookstore.BookStore;
import scala.concurrent.duration.Duration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class OrderActor extends AbstractActor {

    private static final Logger logger = LoggerFactory.getLogger(SearchActor.class);
    private final File file;

    public OrderActor() throws IOException {
        file = new File("orders.txt");
        if(!file.exists()) {
            file.createNewFile();
        }
    }

    @Override
    public AbstractActor.Receive createReceive() {
        return receiveBuilder()
                .match(String.class, o -> {
                    synchronized (BookStore.class) {
                        String title = o.substring(o.indexOf(" ") + 1);
                        PrintWriter printWriter = new PrintWriter(new FileOutputStream(file, true));
                        printWriter.append(title).append("\n");
                        printWriter.flush();
                        printWriter.close();
                        getSender().tell("[result] " + o + " accepted", getSelf());
                    }
                })
                .matchAny(o -> logger.info("received unknown message"))
                .build();
    }

    private static SupervisorStrategy strategy
            = new OneForOneStrategy(10, Duration.create("1 minute"), DeciderBuilder.
                    matchAny(o -> SupervisorStrategy.restart()).
                    build());

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }
}
