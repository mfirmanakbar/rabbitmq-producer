package com.rabbitmq.producer.exchange.topic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.entity.Picture;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 6. For Topic Exchange (send the same data queue for 2 or more consumer with Validated on RabbitMQ)
 * - is like Direct Exchange
 * - the different is on Topic Exchange we can able to use pattern of wildcard on the RoutingKey of RabbitMQ
 * - the pattern like `mobile.#` or `#.jpg` or `*.*.png` or `*.large.svg` or `*.*.svg`
 */
//@Service
public class PictureTopicProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(Picture pic) {
        try {
            StringBuilder sb = new StringBuilder();

            sb.append(pic.getSource());
            sb.append(".");

            if (pic.getSize() > 4000) {
                sb.append("large");
            } else {
                sb.append("small");
            }
            sb.append(".");

            sb.append(pic.getType());

            String json = objectMapper.writeValueAsString(pic);
            String routingKey = sb.toString();
            // routingKey with using TopicExchange, it make able to read the pattern of wildcard,
            // like `mobile.#` or `#.jpg` or `*.*.png` or `*.large.svg` or `*.*.svg`
            rabbitTemplate.convertAndSend("x.picturetopic", routingKey, json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
