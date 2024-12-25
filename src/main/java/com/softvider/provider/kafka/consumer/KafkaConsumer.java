package com.softvider.provider.kafka.consumer;

import com.softvider.provider.kafka.payload.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "softvider.kafka.enabled", havingValue = "true", matchIfMissing = true)
public class KafkaConsumer {
    private static final Logger log = LogManager.getLogger(KafkaConsumer.class);

    /* --- consuming string message only ---
    @KafkaListener(topics = "technology", groupId = "softvider")
    public void consumeMessageTechnology(String message) {
        log.info("Consuming message from topic technology: {}", message);
    }

    @KafkaListener(topics = "science", groupId = "softvider")
    public void consumeMessageScience(String message) {
        log.info("Consuming message from topic science: {}", message);
    }
     */

    @KafkaListener(topics = "student", groupId = "softvider")
    public void consumeStudent(Student student) {
        log.info("Consuming student json from topic student: {}", student.toString());
    }

}


