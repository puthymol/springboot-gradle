package com.softvider.provider.kafka.controller;

import com.softvider.provider.kafka.payload.Student;
import com.softvider.provider.kafka.producer.KafkaJsonProducer;
import com.softvider.provider.kafka.producer.KafkaProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.inject.Inject;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    @Inject
    private KafkaProducer kafkaProducer;

    @Inject
    private KafkaJsonProducer kafkaJsonProducer;

    @RequestMapping(value = "/send/message/technology", method = RequestMethod.POST)
    public ResponseEntity<Boolean> sendMessageTechnology(@RequestBody String message) {
        boolean result = kafkaProducer.sendMessageTechnology(message);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/send/message/science", method = RequestMethod.POST)
    public ResponseEntity<Boolean> sendMessageScience(@RequestBody String message) {
        boolean result = kafkaProducer.sendMessageScience(message);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/send/student", method = RequestMethod.POST)
    public ResponseEntity<Boolean> sendStudent(@RequestBody Student student) {
        boolean result = kafkaJsonProducer.sendStudent(student);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
