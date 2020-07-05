package com.rabbitmq;

import com.rabbitmq.entity.Employee;
import com.rabbitmq.entity.Picture;
import com.rabbitmq.producer.EmployeeJsonProducer;
import com.rabbitmq.producer.HelloRabbitProducer;
import com.rabbitmq.producer.HumanResourceProducer;
import com.rabbitmq.producer.PictureProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
//@EnableScheduling // 2. if this active FOR FIX RATED (produce the data every half second)
public class RabbitmqProducerApplication implements CommandLineRunner {

    //@Autowired
    //private HelloRabbitProducer helloRabbitProducer;

    //@Autowired
    //private EmployeeJsonProducer employeeJsonProducer;

    //@Autowired
    //private HumanResourceProducer humanResourceProducer;

    /*@Autowired
    private PictureProducer pictureProducer;

    private final List<String> SOURCES = List.of("mobile", "web");

    private final List<String> TYPES = List.of("jpg", "png", "svg");*/

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        /**
         * 1. FOR SIMPLE QUEUE
         * */
        //helloRabbitProducer.sendHello("Firman " + Math.random());

        /**
         * 3. FOR consume JSON format
         * */
        /*for (int i = 0; i < 5; i++) {
            Employee emp = new Employee("emp-" + i, "emp-firman-" + i, LocalDate.now());
            employeeJsonProducer.sendMessage(emp);
        }*/

        /**
         * 4. FOR FANOUT EXCHANGE (send the same data queue for 2 or more consumer)
         * - Let's say there are 2 consumers --> `Accounting` and `Marketing`
         * - I have 10 employee data, then I send 10 data for Accounting and also Marketing
         * */
        /*for (int i = 0; i < 5; i++) {
            Employee empHr = new Employee("emp-hr-" + i, "emp-hr-firman-" + i, LocalDate.now());
            humanResourceProducer.sendMessage(empHr);
        }*/

        /**
         * 4. FOR DIRECT EXCHANGE (send the same data queue for 2 or more consumer with Validated on RabbitMQ)
         * - Let's say there are 2 consumers --> `ImageConsumer` and `VectorConsumer`
         * - I put validation on RabbitMQ :
         *   # if the data are `JPG` and `PGN` type then it will consumed for `ImageConsumer`
         *   # if the data are `SVG` type then it will consumed for `VectorConsumer`
         * - Let's say I have 10 Pictures. There are 5 JPG, 2 PNG, and 3 SVG type.
         *   # ImageConsumer will consumed 7 Pictures (5 JPG, 2 PNG)
         *   # VectorConsumer will consumed 3 Pictures (3 SVG)
         * */
        /*for (int i = 0; i < 10; i++) {
            Picture p = new Picture();
            p.setName("Picture " + i);
            p.setSize(ThreadLocalRandom.current().nextLong(1, 10001));
            p.setSource(SOURCES.get(i % SOURCES.size()));
            p.setType(TYPES.get(i % TYPES.size()));

            pictureProducer.sendMessage(p);
        }*/

    }
}
