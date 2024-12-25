package com.softvider.provider.kafka.producer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private static final Logger log = LogManager.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public boolean sendMessageTechnology(String message) {
        log.info("Sending message to topic technology: {}", message);
        kafkaTemplate.send("technology", message);
        return true;
    }

    public boolean sendMessageScience(String message) {
        log.info("Sending message to topic science: {}", message);
        kafkaTemplate.send("science", message);
        return true;
    }

}
