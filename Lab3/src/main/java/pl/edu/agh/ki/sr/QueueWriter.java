package pl.edu.agh.ki.sr;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class QueueWriter {

    private final Channel channel;
    private final String key;

    public QueueWriter(String key) throws IOException, TimeoutException {
        this.key = key;

        // connection & channel
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        channel = connection.createChannel();

        channel.queueDeclare(this.key, false, false, false, null);
    }

    public void send(String message) throws IOException {
        channel.basicPublish("", key, null, message.getBytes());
    }
}
