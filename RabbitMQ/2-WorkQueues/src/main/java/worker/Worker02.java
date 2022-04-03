package worker;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import utils.RabbitMqUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Worker02 {
    public static final String QUEUE_NAME = "hello";

    public static void getMessage() throws IOException, TimeoutException, InterruptedException {
        Channel channel = RabbitMqUtils.getChannel();
        //接收消息的回调
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println(new String(message.getBody()));
        };
        //取消消息的回调
        CancelCallback cancelCallback = consumerTag -> {
            System.out.println(consumerTag + "消息这取消消费接口回调逻辑");
        };
        //1:消费哪个队列 2:消费成功是否要自动应答 3:消费的回调 4:取消消费的回调
        System.out.println("C2等待接收消息......");
        channel.basicConsume(QUEUE_NAME, false, deliverCallback, cancelCallback);
        Thread.sleep(2000000);
    }
}
