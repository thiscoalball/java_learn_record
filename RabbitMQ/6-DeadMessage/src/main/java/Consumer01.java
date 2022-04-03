import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

//消费者1
public class Consumer01 {
    //普通交换机
    public static String NORMAL_EXCHANGE = "normal_exchange";
    //死信交换机
    public static String DEAD_EXCHANGE = "dead_exchange";
    //普通队列
    public static String NORMAL_QUEUE = "normal_queue";
    //死信队列
    public static String DEAD_QUEUE = "dead_queue";
    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtils.getChannel();
        //声明一个普通交换机
        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
        //声明一个死信交换机
        channel.exchangeDeclare(DEAD_EXCHANGE,BuiltinExchangeType.DIRECT);

        //这时要用到queueDeclare的最后一个参数,一般是一个map 然后我往里面设置参数
        Map<String,Object> argument = new HashMap<>();
        //往map里填入死信交换机名称
        argument.put("x-dead-letter-exchange",DEAD_EXCHANGE);
        //设置死信RoutingKey
        argument.put("x-dead-letter-routing-key","deadKey");
        //设置死信过期时间 单位是ms,也可以不在这设置，一般是要在生产方发生的时候设置
        //argument.put("x-message-ttl",10000);
        //设置队列的最大长度，模拟长度超过后进入死信队列
        //argument.put("x-max-length",6);

        //声明一个普通队列，并放入死信交换机信息
        channel.queueDeclare(NORMAL_QUEUE,false,false,false,argument);
        //声明一个死信队列
        channel.queueDeclare(DEAD_QUEUE,false,false,false,null);

        //绑定普通交换机和普通队列
        channel.queueBind(NORMAL_QUEUE,NORMAL_EXCHANGE,"normalKey");
        //绑定死信交换机和死信队列
        channel.queueBind(DEAD_QUEUE,DEAD_EXCHANGE,"deadKey");

        System.out.println("C1等待接收消息......");
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println(new String(message.getBody()));
        };
        CancelCallback cancelCallback = (consumerTag) -> {
        };
        channel.basicConsume(NORMAL_QUEUE, true, deliverCallback, cancelCallback);
    }
}
