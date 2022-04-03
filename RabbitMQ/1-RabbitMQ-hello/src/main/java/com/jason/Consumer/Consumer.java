package com.jason.Consumer;

import com.jason.utils.RabbitMqUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

//消费者 接收消息
public class Consumer {
    //队列名
    public static final String QUEUE_NAME = "hello";
    //接收消息
    public static void getMessage() throws IOException, TimeoutException {
        Channel channel = RabbitMqUtils.getChanel();

        //接收消息的回调
        DeliverCallback deliverCallback = (consumerTag,message) -> {
            System.out.println(new String(message.getBody()));
        };
        //取消消息的回调
        CancelCallback cancelCallback = consumerTag -> {
            System.out.println("消息消费被中断");
        };
        //1:消费哪个队列 2:消费成功是否要自动应答 3:消费的回调 4:取消消费的回调
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);
        System.out.println("接收成功");
    }
}
