package com.campus;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String QUEUE = "enrollment-events";

    @Bean
    public Queue enrollmentQueue() {
        return new Queue(QUEUE, true); // durable: survives a broker restart
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter(); // send messages as JSON
    }
}