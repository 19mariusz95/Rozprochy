package pl.edu.agh.ki.sr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

public class Doctor {

    public static void main(String[] args) throws IOException, TimeoutException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter name");
        String id = br.readLine();

        Consumer<String> consumer = s -> {

        };
        new TopicListener("hosp."+id, true, consumer).start();
        new TopicListener("info", true, System.out::println).start();

        TopicWriter infoWriter = new TopicWriter();
        Map<String, QueueWriter> map = new HashMap<>();
        map.put("knee", new QueueWriter("hosp.exam.knee"));
        map.put("ankle", new QueueWriter("hosp.exam.ankle"));
        map.put("elbow", new QueueWriter("hosp.exam.elbow"));

        while(true) {
            String line = br.readLine();
            if(line.equals("exit")){
                break;
            }
            String[] split = line.split(":");
            if(split.length!=2){
                System.out.println("Bad command");
            }
            Optional.ofNullable(map.get(split[0])).ifPresent( writer -> {
                try {
                    String message = String.format("%s:%s:%s", id, split[0], split[1]);
                    writer.send(message);
                    infoWriter.send(message, "log");
                    System.out.println("Sent: " + message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
