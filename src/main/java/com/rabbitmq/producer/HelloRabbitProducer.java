package com.rabbitmq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 1. Simple Queue
 * */
//@Service
public class HelloRabbitProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendHello(String name) {
        rabbitTemplate.convertAndSend("testproducer.hello", "hello " + name);
    }

}
