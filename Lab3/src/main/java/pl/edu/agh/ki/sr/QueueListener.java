package pl.edu.agh.ki.sr;

import com.rabbitmq.client.*;

import java.io.IOException;

public class QueueListener extends Thread {

    private String key;
    private java.util.function.Consumer<String> messageHandler;
    private boolean autoAck;

    public QueueListener(String key, boolean autoAck, java.util.function.Consumer<String> messageHandler) {
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

            // queue
            channel.queueDeclare(key, false, false, false, null);

            // messageHandler (message handling)
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println("Received: " + message);
                    messageHandler.accept(message);
                }
            };

            channel.basicConsume(key, autoAck, consumer);
        } catch (Exception e) {

        }
    }
}
