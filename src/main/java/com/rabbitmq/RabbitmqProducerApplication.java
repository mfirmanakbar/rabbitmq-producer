package com.rabbitmq;

import com.rabbitmq.entity.Employee;
import com.rabbitmq.entity.Picture;
import com.rabbitmq.producer.*;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @EnableScheduling ==> if this active FOR FIXRATED Scheduled
 */
@SpringBootApplication
//@EnableScheduling
public class RabbitmqProducerApplication implements CommandLineRunner {

//    @Autowired
//    private HelloRabbitProducer helloRabbitProducer;
//
//    @Autowired
//    private EmployeeJsonProducer employeeJsonProducer;
//
//    @Autowired
//    private HumanResourceProducer humanResourceProducer;
//
//    @Autowired
//    private PictureProducer pictureProducer;
//    private final List<String> SOURCES = List.of("mobile", "web");
//    private final List<String> TYPES = List.of("jpg", "png", "svg");

//    @Autowired
//    private PictureTopicProducer pictureTopicProducer;
//    private final List<String> SOURCES_TOPIC = List.of("mobile", "web");
//    private final List<String> TYPES_TOPIC = List.of("jpg", "png", "svg");

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //simpleQueue();
        //consumingJson();
        //FanoutExchangeSample();
        //DirectExchangeSample();
        //TopicExchangeSample();
    }

    /**
     * 6. For Topic Exchange (send the same data queue for 2 or more consumer with Validated on RabbitMQ)
     * - is like Direct Exchange
     * - the different is on Topic Exchange we can able to use pattern of wildcard on the RoutingKey of RabbitMQ
     * - the pattern like `mobile.#` or `#.jpg` or `*.*.png` or `*.large.svg` or `*.*.svg`
     */
    private void TopicExchangeSample() {
//        for (int i = 0; i < 10; i++) {
//            Picture p = new Picture();
//            p.setName("Picture " + i);
//            p.setSize(ThreadLocalRandom.current().nextLong(1, 10001));
//            p.setSource(SOURCES_TOPIC.get(i % SOURCES_TOPIC.size()));
//            p.setType(TYPES_TOPIC.get(i % TYPES_TOPIC.size()));
//
//            pictureTopicProducer.sendMessage(p);
//        }
    }

    /**
     * 5. FOR DIRECT EXCHANGE (send the same data queue for 2 or more consumer with Validated on RabbitMQ)
     * - Let's say there are 2 consumers --> `ImageConsumer` and `VectorConsumer`
     * - I put validation on RabbitMQ :
     * # if the data are `JPG` and `PGN` type then it will consumed for `ImageConsumer`
     * # if the data are `SVG` type then it will consumed for `VectorConsumer`
     * - Let's say I have 10 Pictures. There are 5 JPG, 2 PNG, and 3 SVG type.
     * # ImageConsumer will consumed 7 Pictures (5 JPG, 2 PNG)
     * # VectorConsumer will consumed 3 Pictures (3 SVG)
     */
    private void DirectExchangeSample() {
//        for (int i = 0; i < 10; i++) {
//            Picture p = new Picture();
//            p.setName("Picture " + i);
//            p.setSize(ThreadLocalRandom.current().nextLong(1, 10001));
//            p.setSource(SOURCES.get(i % SOURCES.size()));
//            p.setType(TYPES.get(i % TYPES.size()));
//
//            pictureProducer.sendMessage(p);
//        }
    }

    /**
     * 4. FOR FANOUT EXCHANGE (send the same data queue for 2 or more consumer)
     * - Let's say there are 2 consumers --> `Accounting` and `Marketing`
     * - I have 10 employee data, then I send 10 data for Accounting and also Marketing
     */
    private void FanoutExchangeSample() {
//        for (int i = 0; i < 5; i++) {
//            Employee empHr = new Employee("emp-hr-" + i, "emp-hr-firman-" + i, LocalDate.now());
//            humanResourceProducer.sendMessage(empHr);
//        }
    }

    /**
     * 3. FOR consume JSON format
     */
    private void consumingJson() {
//        for (int i = 0; i < 5; i++) {
//            Employee emp = new Employee("emp-" + i, "emp-firman-" + i, LocalDate.now());
//            employeeJsonProducer.sendMessage(emp);
//        }
    }

    /**
     * 1. FOR SIMPLE QUEUE
     */
    private void simpleQueue() {
//        helloRabbitProducer.sendHello("Firman " + Math.random());
    }
}
