package Model;
import org.apache.commons.lang3.SerializationUtils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;

public class WebSender {

    private final static String QUEUE_NAME = "QQ";


    public void Send(Move message) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, SerializationUtils.serialize(message));
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}
