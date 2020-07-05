package com.rabbitmq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

// 2. FOR FIX RATED (produce the data every half second)
//@Service
public class FixedRateProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private int i = 0;

    private Logger logger = LoggerFactory.getLogger(FixedRateProducer.class);

    @Scheduled(fixedRate = 500)
    private void sendMessage() {
        i++;
        logger.info("i is {}", i);
        rabbitTemplate.convertAndSend("test.fixedrate", "Fixed Rate " + i);
    }

}
