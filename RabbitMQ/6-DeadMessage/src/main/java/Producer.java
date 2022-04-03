import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtils.getChannel();
        //死信消息设置过期时间expiration
        AMQP.BasicProperties properties =
                new AMQP.BasicProperties().builder().expiration("10000").build();

        for (int i = 0; i < 10; i++) {
            String message = "info"+i;
            channel.basicPublish("normal_exchange","normalKey",properties,message.getBytes(StandardCharsets.UTF_8));
            System.out.println("发送成功"+i);
        }
    }
}
