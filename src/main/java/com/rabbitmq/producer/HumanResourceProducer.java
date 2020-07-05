package com.rabbitmq.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.entity.Employee;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


// 4. FOR FANOUT EXCHANGE (Send 1 the same queue for 2 or more consumer using rabbitMq Fanout Exchange)
//@Service
public class HumanResourceProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(Employee emp) {
        try {
            String json = objectMapper.writeValueAsString(emp);
            rabbitTemplate.convertAndSend("x.hr", "", json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
