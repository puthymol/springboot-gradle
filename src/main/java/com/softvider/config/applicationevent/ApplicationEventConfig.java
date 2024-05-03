package com.softvider.config.applicationevent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ApplicationEventConfig {

    @Bean
    public ApplicationEventMulticaster applicationEventMulticaster() {
        SimpleApplicationEventMulticaster applicationEventMulticaster = new SimpleApplicationEventMulticaster();
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.initialize();
        applicationEventMulticaster.setTaskExecutor(threadPoolTaskExecutor);
        return applicationEventMulticaster;
    }
}
