package com.samring.erp_message_consumer.listener;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * ERP message listener
 *
 * @author Atom
 */
@Component
public class ERPOrderStatusMessageListener {

    @RabbitListener(bindings = {@QueueBinding(value = @Queue(value = "${erp.queue}", durable = "true"),
            exchange = @Exchange(value = "${erp.exchange}", type = ExchangeTypes.TOPIC), key = "${erp.key}")},
            containerFactory = "erpContainerFactory"
    )
    public void onMessage(Message message) {
        String receivedMessage = new String(message.getBody());
        com.sarming.service.entity.BaseMessage baseMessage = JSON.parseObject(receivedMessage, com.sarming.service.entity.BaseMessage.class);
        HandlerInvoker.process(baseMessage);
    }

}
