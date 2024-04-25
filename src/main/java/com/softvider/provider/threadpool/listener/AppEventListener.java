package com.softvider.provider.threadpool.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppEventListener implements ApplicationListener<AppEvent> {
    private static final Logger log = LoggerFactory.getLogger(AppEventListener.class);

    @Override
    public void onApplicationEvent(AppEvent appEvent) {
        try {
            Thread.sleep(10000);
            log.info("Doing thread pool {}", appEvent.getSource());
        } catch (InterruptedException e) {
            log.error("InterruptedException: ", e);
        }
    }
}
