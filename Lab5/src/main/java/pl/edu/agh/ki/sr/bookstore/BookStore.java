package pl.edu.agh.ki.sr.bookstore;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import pl.edu.agh.ki.sr.bookstore.actors.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class BookStore {

    public static void main(String[] args) throws IOException {

        File configFile = new File("bookstore_app.conf");
        Config config = ConfigFactory.parseFile(configFile);

        final ActorSystem bookstore = ActorSystem.create("bookstore", config);
        ActorRef search = bookstore.actorOf(Props.create(SearchListener.class), "search");
        ActorRef order = bookstore.actorOf(Props.create(OrderActor.class), "order");
        ActorRef text = bookstore.actorOf(Props.create(BookTextActor.class), "text");
        ActorRef searchResult = bookstore.actorOf(Props.create(SearchActorResult.class), "searchResult");

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            if(scanner.nextLine().equals("q")) {
                bookstore.terminate();
                break;
            }
        }
    }
}
