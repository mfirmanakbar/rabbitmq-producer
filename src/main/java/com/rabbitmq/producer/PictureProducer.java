package com.rabbitmq.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.entity.Employee;
import com.rabbitmq.entity.Picture;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


// 5. FOR DIRECT EXCHANGE (abc...)
@Service
public class PictureProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(Picture pic) {
        try {
            String json = objectMapper.writeValueAsString(pic);
            rabbitTemplate.convertAndSend("x.picture", pic.getType(), json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
