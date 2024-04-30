package com.softvider.provider.kafka.service.impl;

import com.softvider.provider.kafka.service.KafkaConsumerService;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Service
public class KafkaConsumerServiceImpl implements InitializingBean, KafkaConsumerService {
    private static final Logger log = LogManager.getLogger(KafkaConsumerServiceImpl.class);
    private Consumer<String, String> consumer;

    @Value("${softvider.kafka.bootstrap.servers}")
    private String kafkaServers;

    @Value("${softvider.kafka.groupId}")
    private String kafkaGroupId;

    @Value("${softvider.kafka.topic}")
    private String kafkaTopic;

    @Override
    public void afterPropertiesSet() {
        Properties props = new Properties();
        props.put("bootstrap.servers", this.kafkaServers);
        props.put("group.id", this.kafkaGroupId);
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        this.consumer = new KafkaConsumer<>(props);
        this.run();
    }

    @Override
    public String wakeup() {
        consumer.wakeup();
        this.run();
        return "Wake Up Successfully.";
    }

    @Override
    public String close() {
        consumer.close();
        return "Close Successfully.";
    }

    private void run() {
        try {
            consumer.subscribe(Collections.singletonList(this.kafkaTopic));
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    log.info("Key: " + record.key() + ", Value: " + record.value());
                    log.info("Partition: " + record.partition() + ", Offset:" + record.offset());
                }
            }
        } catch (WakeupException e) {
            log.error("Wake up exception: ", e);
        } catch (Exception e) {
            log.error("Unexpected exception: ", e);
        } finally {
            consumer.close();
            log.info("The consumer is now gracefully closed.");
        }
    }

}
