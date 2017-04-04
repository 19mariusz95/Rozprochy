package pl.edu.agh.ki.sr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

public class Technician {

    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println("Enter two specializations");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String first = br.readLine();
        String second = br.readLine();
        TopicWriter topicWriter = new TopicWriter();
        Consumer<String> consumer = s -> {
            String[] split = s.split(":");
            try {
                Thread.sleep(2000);
                String message = split[1] + " " + split[2];
                topicWriter.send(message, "hosp." + split[0]);
                topicWriter.send(message, "log");
                System.out.println("Sent: " + message);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        };
        new QueueListener("hosp.exam." + first, true, consumer).start();
        new QueueListener("hosp.exam." + second, true, consumer).start();
        new TopicListener("info", true, System.out::println).start();

    }
}
