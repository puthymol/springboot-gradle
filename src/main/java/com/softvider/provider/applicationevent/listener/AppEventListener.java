package com.softvider.provider.applicationevent.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppEventListener implements ApplicationListener<AppEvent> {
    private static final Logger log = LoggerFactory.getLogger(AppEventListener.class);

    /*--- Event will precess sequentially ---*/
    @Override
    public void onApplicationEvent(AppEvent appEvent) {
        try {
            log.info("Starting do something background for event {}", appEvent.getSource());
            Thread.sleep(10000);
            log.info("Finished do something background for event {}", appEvent.getSource());
        } catch (InterruptedException e) {
            log.error("InterruptedException: ", e);
        }
    }
}
