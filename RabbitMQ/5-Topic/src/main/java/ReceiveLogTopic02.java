import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ReceiveLogTopic02 {
    public static String EXCHANGE_NAME = "topic_logs";
    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtils.getChannel();
        //声明一个交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        //声明一个队列
        channel.queueDeclare("Q2",false,false,false,null);
        //绑定
        channel.queueBind("Q2", EXCHANGE_NAME, "*.*.rabbit");
        channel.queueBind("Q2", EXCHANGE_NAME, "lazy.#");

        System.out.println("Q2等待接收消息......");
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println(new String(message.getBody()));
        };
        CancelCallback cancelCallback = (consumerTag) -> {
        };
        channel.basicConsume("Q2", true, deliverCallback, cancelCallback);
    }
}
