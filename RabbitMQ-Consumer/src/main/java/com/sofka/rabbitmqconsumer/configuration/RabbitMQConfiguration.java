package com.sofka.rabbitmqconsumer.configuration;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.springframework.amqp.core.*;



@Configuration
public class RabbitMQConfiguration {

    //Routing key
    private static final String ROUTING_KEY_A = "routing.A";

    private static final String ROUTING_KEY_B = "routing.B";

    private static final String ROUTING_KEY_ALL = "routing.*";


    //Queue name

    private static final String QUEUE_A = "queue.A";

    private static final String QUEUE_B = "queue.B";

    private static final String QUEUE_ALL = "queue.All";


    //Exchange name

//    private static final String EXCHANGE_DIRECT = "exchange.direct";

//    private static final String EXCHANGE_FANOUT = "exchange.fanout";

//    private static final String EXCHANGE_TOPIC = "exchange.topic";

    private static final String EXCHANGE_HEADERS = "exchange.header";

    @Bean
    Queue queueA() {
        return new Queue(QUEUE_A, false);
    }

    @Bean
    Queue queueB() {
        return new Queue(QUEUE_B, false);
    }

    @Bean
    Queue queueAll() {return new Queue(QUEUE_ALL, false);
    }

    // this is for direct exchange
//    @Bean
//    DirectExchange exchange() {
//        return new DirectExchange(EXCHANGE_DIRECT);
//    }
//
//    @Bean
//    Binding binding(Queue queueA, DirectExchange exchange) {
//        return BindingBuilder.bind(queueA).to(exchange).with(ROUTING_KEY_A);
//    }
//
//    @Bean
//    Binding bindingB(Queue queueB, DirectExchange exchange) {
//        return BindingBuilder.bind(queueB).to(exchange).with(ROUTING_KEY_B);
//    }


    // this is for fanout exchange
//    @Bean
//    FanoutExchange exchange() {
//        return new FanoutExchange(EXCHANGE_FANOUT);
//    }
//
//    @Bean
//    Binding binding(Queue queueA, FanoutExchange exchange) {
//        return BindingBuilder.bind(queueA).to(exchange);
//    }
//
//    @Bean
//    Binding bindingB(Queue queueB, FanoutExchange exchange) {
//        return BindingBuilder.bind(queueB).to(exchange);
//    }

    // this is for topic exchange
//    @Bean
//    TopicExchange exchange() {
//        return new TopicExchange(EXCHANGE_TOPIC);
//    }
//
//    @Bean
//    Binding binding(Queue queueA, TopicExchange exchange) {
//        return BindingBuilder.bind(queueA).to(exchange).with(ROUTING_KEY_A);
//    }
//
//    @Bean
//    Binding bindingB(Queue queueB, TopicExchange exchange) {
//        return BindingBuilder.bind(queueB).to(exchange).with(ROUTING_KEY_B);
//    }
//
//    @Bean
//    Binding bindingAll(Queue queueAll, TopicExchange exchange) {
//        return BindingBuilder.bind(queueAll).to(exchange).with(ROUTING_KEY_ALL);
//    }

    // this is for header exchange
    @Bean
    HeadersExchange exchange() {
        return new HeadersExchange(EXCHANGE_HEADERS);
    }

    @Bean
    Binding binding(Queue queueA, HeadersExchange exchange) {
        return BindingBuilder.bind(queueA).to(exchange).where("color").matches("red");
    }

    @Bean
    Binding bindingB(Queue queueB, HeadersExchange exchange) {
        return BindingBuilder.bind(queueB).to(exchange).where("color").matches("blue");
    }

    @Bean
    Binding bindingAll(Queue queueAll, HeadersExchange exchange) {
        return BindingBuilder.bind(queueAll).to(exchange).where("color").matches("green");
    }


    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}

