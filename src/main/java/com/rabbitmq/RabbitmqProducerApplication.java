package com.rabbitmq;

import com.rabbitmq.entity.InvoiceCancelledMessage;
import com.rabbitmq.entity.InvoiceCreatedMessage;
import com.rabbitmq.entity.InvoicePaidMessage;
import com.rabbitmq.entity.InvoiceRejectedMessage;
import com.rabbitmq.producer.invoice.InvoiceProducer;
import com.rabbitmq.producer.invoice.InvoiceProducerCh;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @EnableScheduling ==> if this active FOR FIXRATED Scheduled
 */
@SpringBootApplication
//@EnableScheduling
public abstract class RabbitmqProducerApplication implements CommandLineRunner {

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

//    @Autowired
//    private MyPictureProducer myPictureProducer;
//    private final List<String> SOURCES_DLX = List.of("mobile", "web");
//    private final List<String> TYPES_DLX = List.of("jpg", "png", "svg");

//    @Autowired
//    private MyPictureProducerTTL myPictureProducerTTL;
//    private final List<String> SOURCES_TTL = List.of("mobile", "web");
//    private final List<String> TYPES_TTL = List.of("jpg", "png", "svg");

//    @Autowired
//    private RetryPictureProducer retryPictureProducer;
//    private final List<String> SOURCES_TRY = List.of("mobile", "web");
//    private final List<String> TYPES_TRY = List.of("jpg", "png", "svg");

//    @Autowired
//    StopStartProducer stopStartProducer;

//    @Autowired
//    AckNackProducer ackNackProducer;

//    @Autowired
//    private InvoiceProducer invoiceProducer;

    @Autowired
    private InvoiceProducerCh invoiceProducerCh;

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        simpleQueue();
//        consumingJson();
//        FanoutExchangeSample();
//        DirectExchangeSample();
//        TopicExchangeSample();
//        errorHandlingDlxSample();
//        errorHandlingTtlSample();
//        retryMechanismDirect();
//        stopStartRabbit();
//        ngetesBro();
//        ackNackRabbit();
//        invoices();
//        invoicesRabbitConsistentHashA();
        invoicesRabbitConsistentHashB();
    }

    private void invoicesRabbitConsistentHashB() {
        for (int i = 1; i <= 100; i++) {
            invoiceProducerCh.sendInvoiceCreated(simpleInvoice("INV-" + i));
            invoiceProducerCh.sendInvoicePaid(simplePayment("INV-" + i, "PAY-" + i));
        }
    }

    private InvoicePaidMessage simplePayment(String invoiceNumber, String paymentNumber) {
        InvoicePaidMessage invoicePaidMessage = new InvoicePaidMessage();
        invoicePaidMessage.setInvoiceNumber(invoiceNumber);
        invoicePaidMessage.setPaidDate(LocalDate.now());
        invoicePaidMessage.setPaymentNumber(paymentNumber);
        return invoicePaidMessage;
    }

    private InvoiceCreatedMessage simpleInvoice(String invoiceNumber) {
        InvoiceCreatedMessage invoice = new InvoiceCreatedMessage();
        invoice.setInvoiceNumber(invoiceNumber);
        invoice.setCreatedDate(LocalDate.now());
        invoice.setAmount(ThreadLocalRandom.current().nextInt(100));
        invoice.setCurrency("Rp");
        return invoice;
    }

    private void invoicesRabbitConsistentHashA() {
//        for (int i = 0; i < 25; i++) {
//            String invoiceNumber = "invoice-" + (i % 6);
//            InvoiceCreatedMessage invoice = new InvoiceCreatedMessage();
//            invoice.setAmount(ThreadLocalRandom.current().nextInt(100));
//            invoice.setCreatedDate(LocalDate.now());
//            invoice.setCurrency("USD");
//            invoice.setInvoiceNumber(invoiceNumber);
//            invoiceProducerCh.sendInvoiceCreated(invoice);
//        }
    }


    /**
     * one queue + multiple messages
     */
    private void invoices() {
//        String randomInvoiceNumber = "INV-" + ThreadLocalRandom.current().nextInt(100, 200);
//        InvoiceCreatedMessage invoiceCreatedMessage = new InvoiceCreatedMessage();
//        invoiceCreatedMessage.setAmount(154.25);
//        invoiceCreatedMessage.setCreatedDate(LocalDate.now());
//        invoiceCreatedMessage.setCurrency("Rp");
//        invoiceProducer.sendInvoiceCreated(invoiceCreatedMessage);
//
//        randomInvoiceNumber = "INV-" + ThreadLocalRandom.current().nextInt(200, 300);
//        String randomPaymentNumber = "PAY-" + ThreadLocalRandom.current().nextInt(20000, 30000);
//        InvoicePaidMessage invoicePaidMessage = new InvoicePaidMessage();
//        invoicePaidMessage.setInvoiceNumber(randomInvoiceNumber);
//        invoicePaidMessage.setPaidDate(LocalDate.now());
//        invoicePaidMessage.setPaymentNumber(randomPaymentNumber);
//        invoiceProducer.sendInvoicePaid(invoicePaidMessage);
//
//        randomInvoiceNumber = "INV-" + ThreadLocalRandom.current().nextInt(300, 400);
//        InvoiceCancelledMessage invoiceCancelledMessage = new InvoiceCancelledMessage();
//        invoiceCancelledMessage.setCancelDate(LocalDate.now());
//        invoiceCancelledMessage.setInvoiceNumber(randomInvoiceNumber);
//        invoiceCancelledMessage.setReason("Just cancel it!");
//        invoiceProducer.sendInvoiceCancelled(invoiceCancelledMessage);
//
//        randomInvoiceNumber = "INV-" + ThreadLocalRandom.current().nextInt(400, 500);
//        InvoiceRejectedMessage invoiceRejectedMessage = new InvoiceRejectedMessage();
//        invoiceRejectedMessage.setCancelDate(LocalDate.now());
//        invoiceRejectedMessage.setInvoiceNumber(randomInvoiceNumber);
//        invoiceRejectedMessage.setReason("I want to reject this one");
//        invoiceProducer.sendInvoiceRejected(invoiceRejectedMessage);
    }

    private void ackNackRabbit() {
//        for (int i = 0; i < 8; i++) {
//            if (i % 2 == 0) {
//                ackNackProducer.publish("Sync Outlet " + i, "b");
//            } else {
//                ackNackProducer.publish("Sync Outlet " + i, "a");
//            }
//        }
    }

    private void stopStartRabbit() {
//        for (int i = 0; i < 10; i++) {
//            stopStartProducer.publish("number-" + i);
//        }
    }

    private void retryMechanismDirect() throws Exception {
//        for (int i = 0; i < 1; i++) {
//            Picture p = new Picture();
//
//            p.setName("Picture " + i);
//            p.setSize(ThreadLocalRandom.current().nextLong(9001, 10001));
//            p.setSource(SOURCES_TRY.get(i % SOURCES_TRY.size()));
//            p.setType(TYPES_TRY.get(i % TYPES_TRY.size()));
//
//            retryPictureProducer.sendMessage(p);
//        }
    }

    private void ngetesBro() {
        //System.out.println("IS WORKER ? " + System.getenv("AUTO_DAILY_SYNC"));
        //NgetesBroProducer test = new NgetesBroProducer();
        //test.publish();
    }

    /**
     * 8. Error Handling with TTL (Time to Live)
     */
    private void errorHandlingTtlSample() throws Exception {
//        for (int i = 0; i < 1; i++) {
//            Picture p = new Picture();
//
//            p.setName("Picture " + i);
//            p.setSize(ThreadLocalRandom.current().nextLong(9001, 10001));
//            p.setSource(SOURCES_TTL.get(i % SOURCES_TTL.size()));
//            p.setType(TYPES_TTL.get(i % TYPES_TTL.size()));
//
//            myPictureProducerTTL.sendMessage(p);
//        }
    }

    /**
     * 7. Error Handling with DLX (Dead Letter Exchange)
     * if we have some error from consumer validation
     * we can reject the queue and move it to another queue
     */
    private void errorHandlingDlxSample() throws Exception {
//        for (int i = 0; i < 1; i++) {
//            Picture p = new Picture();
//
//            p.setName("Picture " + i);
//            p.setSize(ThreadLocalRandom.current().nextLong(9001, 10001));
//            p.setSource(SOURCES_DLX.get(i % SOURCES_DLX.size()));
//            p.setType(TYPES_DLX.get(i % TYPES_DLX.size()));
//
//            myPictureProducer.sendMessage(p);
//        }
    }

    /**
     * 6. Exchange Topic Type (send the same data queue for 2 or more consumer with Validated on RabbitMQ)
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
     * 5. Exchange Direct Type (send the same data queue for 2 or more consumer with Validated on RabbitMQ)
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
     * 4. Exchange FanOut Type (send the same data queue for 2 or more consumer)
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
     * 3. Consuming JSON Format
     */
    private void consumingJson() {
//        for (int i = 0; i < 5; i++) {
//            Employee emp = new Employee("emp-" + i, "emp-firman-" + i, LocalDate.now());
//            employeeJsonProducer.sendMessage(emp);
//        }
    }

    /**
     * 1. Simple Queue
     */
    private void simpleQueue() {
//        helloRabbitProducer.sendHello("Firman " + Math.random());
    }
}
