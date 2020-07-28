package com.rabbitmq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class StopStartProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publish(String msg) {
        rabbitTemplate.convertAndSend("x.dummy", "", msg);
    }

}
