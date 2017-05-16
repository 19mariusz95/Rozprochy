package pl.edu.agh.ki.sr.bookstore.actors;

import akka.NotUsed;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.OneForOneStrategy;
import akka.actor.SupervisorStrategy;
import akka.japi.pf.DeciderBuilder;
import akka.stream.ActorMaterializer;
import akka.stream.OverflowStrategy;
import akka.stream.ThrottleMode;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.collection.Iterator;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;
import scala.io.Codec;
import scala.collection.JavaConversions.*;
import java.util.concurrent.TimeUnit;

public class BookTextActor extends AbstractActor {

    private static final Logger logger = LoggerFactory.getLogger(SearchActor.class);

    @Override
    public AbstractActor.Receive createReceive() {
        return receiveBuilder()
                .match(String.class, message -> {
                    String fileName = message.substring(message.indexOf(" ") + 1);
                    ActorMaterializer materializer = ActorMaterializer.create(context());
                    ActorRef run = Source.actorRef(1000, OverflowStrategy.dropNew())
                            .throttle(1, FiniteDuration.create(1, TimeUnit.SECONDS), 10, ThrottleMode.shaping())
                            .to(Sink.actorRef(getSender(), NotUsed.getInstance()))
                            .run(materializer);
                    Iterator<String> lines = scala.io.Source.fromFile(fileName, Codec.UTF8()).getLines();
                    scala.collection.JavaConversions.asJavaIterator(lines).forEachRemaining(line -> {
                        run.tell(line, getSelf());
                    });
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
