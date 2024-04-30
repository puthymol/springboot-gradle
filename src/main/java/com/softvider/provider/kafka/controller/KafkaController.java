package com.softvider.provider.kafka.controller;

import com.softvider.provider.kafka.service.KafkaConsumerService;
import com.softvider.provider.kafka.service.KafkaProducerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    @Inject
    private KafkaProducerService kafkaProducerService;

    @Inject
    private KafkaConsumerService kafkaConsumerService;

    @RequestMapping(value = "random", method = RequestMethod.GET)
    public ResponseEntity<List<Map<String, Object>>> random() {
        List<Map<String, Object>> result = kafkaProducerService.random();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "wakeup", method = RequestMethod.GET)
    public ResponseEntity<String> wakeup() {
        String result = kafkaConsumerService.wakeup();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "close", method = RequestMethod.GET)
    public ResponseEntity<String> close() {
        String result = kafkaConsumerService.close();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
