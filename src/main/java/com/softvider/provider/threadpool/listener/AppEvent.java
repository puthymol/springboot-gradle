package com.softvider.provider.threadpool.listener;

import org.springframework.context.ApplicationEvent;

public class AppEvent extends ApplicationEvent {

    public AppEvent(Object source) {
        super(source);
    }
}
