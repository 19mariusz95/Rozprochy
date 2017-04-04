package pl.edu.agh.ki.sr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeoutException;

public class Administrator {

    public static void main(String[] args) throws IOException, TimeoutException {

        new TopicListener("log", true, (e)->{}).start();

        TopicWriter topicWriter = new TopicWriter();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            String line = br.readLine();
            if(line.equals("exit")){
                break;
            }
            topicWriter.send(line,"info");
            System.out.println("Sent: " + line);
        }
    }
}
