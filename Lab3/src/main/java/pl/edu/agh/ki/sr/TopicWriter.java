package pl.edu.agh.ki.sr;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TopicWriter {

    private static final String EXCHANGE_NAME = "hospital";
    private final Channel channel;

    public TopicWriter() throws IOException, TimeoutException {

        // connection & channel
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        channel = connection.createChannel();

        // exchange
        String EXCHANGE_NAME = "exchange1";
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
    }

    public void send(String message, String routingKey) throws IOException {
        channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
    }
}
