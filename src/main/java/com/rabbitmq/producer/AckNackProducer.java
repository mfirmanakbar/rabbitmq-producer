package com.rabbitmq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class AckNackProducer {

    Logger logger = LoggerFactory.getLogger(AckNackProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publish(String msg, String key) {
        //rabbitTemplate.convertAndSend("x.dummy", "", msg);
        rabbitTemplate.convertAndSend("x.dummy.direct", key, msg);
        logger.info("PUBLISH MSG `{}` and key `{}`", msg, key);
    }

}
