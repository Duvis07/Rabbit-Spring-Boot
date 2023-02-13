package com.sofka.rabbitmq.controller;

import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private HeadersExchange exchange;


    // this is for direct exchange
//    @PostMapping("/producer")
//    public String sendMessage(@RequestBody Message message) {
//        rabbitTemplate.convertAndSend(exchange.getName(), "routing.A", message);
//        return "Message sent  Successfully";
//
//    }

    // this is for fanout exchange
//    @PostMapping("/producer")
//    public String sendMessage(@RequestBody Message message) {
//        rabbitTemplate.convertAndSend(exchange.getName(), "", message);
//        return "Message sent  Successfully";
//
//    }

    // this is for topic exchange
//    @PostMapping("/producer")
//    public String sendMessage(@RequestBody Message message) {
//        rabbitTemplate.convertAndSend(exchange.getName(), "routing.A", message);
//        return "Message sent  Successfully";
//
//    }

    // this is for header exchange
    @PostMapping("/producer/{message}")
    public String sendMessage(@PathVariable(value = "message") String message) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("color", message);
        MessageConverter messageConverter = new SimpleMessageConverter();
        Message messageConverter1 =messageConverter.toMessage(message, messageProperties);
        rabbitTemplate.convertAndSend(exchange.getName(), "", messageConverter1);
        return "Message sent  Successfully";

    }
}
