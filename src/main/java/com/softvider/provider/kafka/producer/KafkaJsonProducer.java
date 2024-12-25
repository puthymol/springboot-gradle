package com.softvider.provider.kafka.producer;

import com.softvider.provider.kafka.payload.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaJsonProducer {
    private static final Logger log = LogManager.getLogger(KafkaJsonProducer.class);

    @Autowired
    private KafkaTemplate<String, Student> kafkaTemplate;

    public boolean sendStudent(Student student) {
        Message<Student> message = MessageBuilder
                .withPayload(student)
                .setHeader(KafkaHeaders.TOPIC, "student")
                .build();
        log.info("Sending student json to topic student: {}", student.toString());
        kafkaTemplate.send(message);
        return true;
    }
}
