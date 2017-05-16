package pl.edu.agh.ki.sr.client;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import pl.edu.agh.ki.sr.client.actor.LocalActor;

import java.io.File;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        File configFile = new File("client_app.conf");
        Config config = ConfigFactory.parseFile(configFile);

        final ActorSystem system = ActorSystem.create("local_system", config);
        final ActorRef local = system.actorOf(Props.create(LocalActor.class),"local");
        System.out.println("search, order, text");

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("q")) {
                system.terminate();
                return;
            }
            local.tell(line, null);
        }
    }
}
