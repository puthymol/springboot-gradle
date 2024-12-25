package com.softvider.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@ConditionalOnProperty(name = "softvider.kafka.enabled", havingValue = "true", matchIfMissing = true)
public class KafkaTopicConfig {

    @Bean
    public NewTopic topicTechnology() {
        return TopicBuilder.name("technology")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic topicScience() {
        return TopicBuilder.name("science")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic topicStudent() {
        return TopicBuilder.name("student")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
