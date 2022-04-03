package com.jason.Producer;

import com.jason.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeoutException;

//生产者 发送消息->给队列
public class Producer {
    public static final String QUEUE_NAME = "hello";

    public static void sendMessage(String message) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtils.getChanel();
        //生成一个队列
        //https://blog.csdn.net/jj546630576/article/details/102498032 参数作用去这里看
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //发布 参数分别为：1:发送到哪个交换机;2:路由的key值是哪个(本次是队列名) ; 3:其他参数;  4:消息体
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes(StandardCharsets.UTF_8));
        System.out.println("发送完毕");
    }
}
