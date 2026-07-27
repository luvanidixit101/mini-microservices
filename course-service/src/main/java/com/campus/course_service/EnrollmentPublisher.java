package com.campus.course_service;

import com.campus.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentPublisher {

    private static final Logger log = LoggerFactory.getLogger(EnrollmentPublisher.class);
    private final RabbitTemplate rabbit;

    public EnrollmentPublisher(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    public void publish(EnrollmentEvent event) {
        try {
            // fire-and-forget: we do NOT wait for anyone to read it
            rabbit.convertAndSend(RabbitConfig.QUEUE, event);
        } catch (Exception e) {
            log.warn("Could not send enrollment event to RabbitMQ (RabbitMQ may be offline): {}", e.getMessage());
        }
    }
}