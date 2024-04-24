package com.softvider.config.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Component("random")
public class HealthCheckIndicator implements HealthIndicator {

    @Override
    public Health health() {
        double chance = ThreadLocalRandom.current().nextDouble();
        Health.Builder status = Health.up();
        Map<String, Object> details = new HashMap<>();
        details.put("chance", chance);
        details.put("strategy", "thread-local");
        return status.withDetails(details).build();
    }
}
