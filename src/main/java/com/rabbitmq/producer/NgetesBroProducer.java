package com.rabbitmq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class NgetesBroProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publish() {
        try {
            rabbitTemplate.convertAndSend("tanpa.create.queue", "ngetes aja errornya apa bro");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
