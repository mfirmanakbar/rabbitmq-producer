package com.rabbitmq.producer.invoice;

import com.rabbitmq.entity.InvoiceCancelledMessage;
import com.rabbitmq.entity.InvoiceCreatedMessage;
import com.rabbitmq.entity.InvoicePaidMessage;
import com.rabbitmq.entity.InvoiceRejectedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceProducerCh {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE = "x.ch-invoice";

    public void sendInvoiceCreated(InvoiceCreatedMessage message) {
        rabbitTemplate.convertAndSend(EXCHANGE, message.getInvoiceNumber(), message);
    }

    public void sendInvoicePaid(InvoicePaidMessage message) {
        rabbitTemplate.convertAndSend(EXCHANGE, "", message);
    }

    public void sendInvoiceCancelled(InvoiceCancelledMessage message) {
        rabbitTemplate.convertAndSend(EXCHANGE, "", message);
    }

    public void sendInvoiceRejected(InvoiceRejectedMessage message) {
        rabbitTemplate.convertAndSend(EXCHANGE, "", message);
    }

}
