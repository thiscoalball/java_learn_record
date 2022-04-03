package com.example.confirm.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyCallback implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {
    /**
     * 交换机确认回调方法
     * 1 发消息 交换机接收到了 回调不管是否成功
     * 1.1:CorrelationData 保存回调消息的ID以及相关消息
     * 1.2:ack 接收成功 true， 接收失败 false
     * 1.3:失败原因,成功为null
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String id = correlationData != null ? correlationData.getId() : "";
        if (ack) {
            log.info("交换机已经收到了id为{}的消息", id);
        } else {
            log.info("交换机还没收到id为{}的消息，由于{}", id, cause);
        }
    }

    //可以在消息传递过程中无法到达目的地的时候把消息返回给生产者
    @Override
    public void returnedMessage(ReturnedMessage returned) {
        log.error("消息:{},被交换机:{}给退回,退回原因:{},路由key:{}",
                new String(returned.getMessage().getBody()),
                returned.getExchange(),
                returned.getReplyText(),
                returned.getRoutingKey());
    }
}
