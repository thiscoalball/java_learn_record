package com.jason.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMqUtils {
    public static final String QUEUE_NAME = "hello";
    //接收消息
    public static Channel getChanel() throws IOException, TimeoutException {
        //创建工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置工厂ip连接rabbitmq队列
        connectionFactory.setHost("192.168.17.128");
        //设置用户名和密码
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("123");
        //获取连接
        Connection connection = connectionFactory.newConnection();
        //获取信道
        return connection.createChannel();
    }
}
