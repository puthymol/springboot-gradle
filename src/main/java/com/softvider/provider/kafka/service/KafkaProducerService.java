package com.softvider.provider.kafka.service;

import java.util.List;
import java.util.Map;

public interface KafkaProducerService {
    List<Map<String, Object>> random();
}
