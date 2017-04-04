package pl.edu.agh.ki.sr;

import com.rabbitmq.client.*;
import com.rabbitmq.client.Consumer;

import java.io.IOException;

public class TopicListener extends Thread{

    private static final String EXCHANGE_NAME = "hospital";
    private String key;
    private java.util.function.Consumer<String> messageHandler;
    private boolean autoAck;

    public TopicListener(String key, boolean autoAck, java.util.function.Consumer<String> messageHandler) {
        this.key = key;
        this.autoAck = autoAck;
        this.messageHandler = messageHandler;
    }

    public void run() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            // exchange
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

            // queue & bind
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, EXCHANGE_NAME, key);

            // messageHandler (message handling)
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println("Received: " + message);
                    messageHandler.accept(message);

                }
            };

            channel.basicConsume(queueName, autoAck, consumer);
        }catch (Exception e) {

        }
    }
}
