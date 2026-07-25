package com.campus.notification_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentListener {

    private static final Logger log = LoggerFactory.getLogger(EnrollmentListener.class);

    @RabbitListener(queues = "enrollment-events") // wake up when a message arrives
    public void onEnrollment(EnrollmentEvent event) {
        // a real app would send an email/SMS; we just log it
        log.info("📧 EMAIL SENT → Dear {}, you are enrolled in course #{}!",
                event.studentName(), event.courseId());
    }
}