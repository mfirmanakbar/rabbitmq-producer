package com.rabbitmq.producer.handling.ttl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.entity.Picture;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class MyPictureProducerTTL {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(Picture pic) throws Exception {
        String json = objectMapper.writeValueAsString(pic);
        rabbitTemplate.convertAndSend("x.mypicturettl", "", json);
    }

}
