package com.softvider.provider.kafka.service.impl;

import com.softvider.provider.kafka.service.KafkaProducerService;
import com.softvider.utils.AppUtil;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class KafkaProducerServiceImpl implements InitializingBean, KafkaProducerService {
    private static final Logger log = LogManager.getLogger(KafkaProducerServiceImpl.class);
    private static final int NUMBER_OF_RANDOM = 100;
    private final Random rand = new Random();
    private Producer<String, String> producer;

    @Value("${softvider.kafka.bootstrap.servers}")
    private String bootstrapServers;

    @Value("${softvider.kafka.topic}")
    private String kafkaTopic;

    @Override
    public void afterPropertiesSet() {
        Properties props = new Properties();
        props.put("bootstrap.servers", this.bootstrapServers);
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());
        this.producer = new KafkaProducer<>(props);
    }

    @Override
    public List<Map<String, Object>> random() {
        List<Map<String, Object>> response = new ArrayList<>();
        for(int i = 0; i < NUMBER_OF_RANDOM; i++) {
            Map<String, Object> order = new HashMap<>();
            int randNumber = this.rand.nextInt(9) + 1;
            String date = String.valueOf(LocalDateTime.now());
            order.put("order_sequence", String.valueOf(i + 1));
            order.put("order_menu_id", String.valueOf(randNumber));
            order.put("order_date", date);
            response.add(order);
            this.sendToKafka(AppUtil.toJSON(order));
        }
        return response;
    }

    private void sendToKafka(String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>(this.kafkaTopic, message);
        this.producer.send(record, (metadata, exception) -> {
            if (exception == null) {
                log.info("Order sent successfully to partition "
                        + metadata.partition() +
                        " with offset " + metadata.offset());
            } else {
                log.error("Error sending order: " + exception.getMessage());
            }
        });
    }

}
