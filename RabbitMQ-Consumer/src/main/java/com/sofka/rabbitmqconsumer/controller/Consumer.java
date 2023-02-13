package com.sofka.rabbitmqconsumer.controller;


import com.sofka.rabbitmqconsumer.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {

    // EXCHANGE DIRECT, FANOUT, TOPIC

//    @RabbitListener(queues = "queue.A")
//    public void receiveMessageA(Message message) {
//        log.info("Received message from queue A: {}", message);
//    }
//
//    @RabbitListener(queues = "queue.B")
//    public void receiveMessageB(Message message) {
//        log.info("Received message from queue B: {}", message);
//    }
//
//    @RabbitListener(queues = "queue.All")
//    public void receiveMessageAll(Message message) {
//        log.info("Received message from queue All: {}", message);
//    }

    // EXCHANGE HEADER
    @RabbitListener(queues = "queue.A")
    public void receiveMessageA(String message) {
        log.info("Received message from queue A: {}", message);
    }

    @RabbitListener(queues = "queue.B")
    public void receiveMessageB(String message) {
        log.info("Received message from queue B: {}", message);
    }

    @RabbitListener(queues = "queue.All")
    public void receiveMessageAll(String message) {
        log.info("Received message from queue All: {}", message);
    }


}
