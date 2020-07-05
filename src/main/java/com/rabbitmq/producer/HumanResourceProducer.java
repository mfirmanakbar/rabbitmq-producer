package com.rabbitmq.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.entity.Employee;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 4. FOR FANOUT EXCHANGE (send the same data queue for 2 or more consumer)
 * - Let's say there are 2 consumers --> `Accounting` and `Marketing`
 * - I have 10 employee data, then I send 10 data for Accounting and also Marketing
 * */
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
