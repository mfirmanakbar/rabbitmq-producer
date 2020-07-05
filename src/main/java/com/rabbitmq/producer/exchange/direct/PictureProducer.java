package com.rabbitmq.producer.exchange.direct;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.entity.Employee;
import com.rabbitmq.entity.Picture;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 5. FOR DIRECT EXCHANGE (send the same data queue for 2 or more consumer with Validated on RabbitMQ)
 * - Let's say there are 2 consumers --> `ImageConsumer` and `VectorConsumer`
 * - I put validation on RabbitMQ :
 *   # if the data are `JPG` and `PGN` type then it will consumed for `ImageConsumer`
 *   # if the data are `SVG` type then it will consumed for `VectorConsumer`
 * - Let's say I have 10 Pictures. There are 5 JPG, 2 PNG, and 3 SVG type.
 *   # ImageConsumer will consumed 7 Pictures (5 JPG, 2 PNG)
 *   # VectorConsumer will consumed 3 Pictures (3 SVG)
 * */
//@Service
public class PictureProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(Picture pic) {
        try {
            String json = objectMapper.writeValueAsString(pic);
            // pic.getType() for RoutingKey example like JPG / PNG / SVG
            rabbitTemplate.convertAndSend("x.picture", pic.getType(), json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
