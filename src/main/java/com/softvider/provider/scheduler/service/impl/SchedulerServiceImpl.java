package com.softvider.provider.scheduler.service.impl;

import com.softvider.provider.scheduler.service.SchedulerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class SchedulerServiceImpl implements SchedulerService {
    private static final Logger log = LogManager.getLogger(SchedulerServiceImpl.class);

    @Override
    public void executeScheduler(String param) {
        log.info("Execute Scheduler - {}", param);
    }

}
