package com.softvider.config.scheduler;

import com.softvider.provider.scheduler.service.SchedulerService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@ConditionalOnProperty(name = "softvider.scheduler.enable", havingValue = "true", matchIfMissing = true)
public class Scheduler {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    @Inject
    SchedulerService schedulerService;

    /*--- [second] [minute] [hour] [day] [month] [day of week] ---*/

    @Scheduled(fixedRate = 60000)
    public void runEveryOneMinuteTask() {
        schedulerService.executeScheduler (LocalDateTime.now().format(formatter) + " Run every one minute start from application started");
    }

    @Scheduled(cron = "0 * * * * *")
    public void runStartOfMinuteTask() {
        schedulerService.executeScheduler (LocalDateTime.now().format(formatter) + " Runs at the start of every minute");
    }

    @Scheduled(cron = "0 0 * * * *")
    public void runStartOfHourTask() {
        schedulerService.executeScheduler (LocalDateTime.now().format(formatter) + " Runs at the start of every hour");
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void runStartOfDayTask() {
        schedulerService.executeScheduler (LocalDateTime.now().format(formatter) + " Runs at the start of every day");
    }

    @Scheduled(cron = "59 59 23 * * *")
    public void runEndOfDayTask() {
        schedulerService.executeScheduler (LocalDateTime.now().format(formatter) + " Runs at the end of every day");
    }

    @Scheduled(cron = "0 0 7 * * *")
    public void runAtSevenAMTask() {
        schedulerService.executeScheduler (LocalDateTime.now().format(formatter) + " Runs at 7:00 AM every day");
    }

    @Scheduled(cron = "0 0 17 * * *")
    public void runAtSeventeenTask() {
        schedulerService.executeScheduler (LocalDateTime.now().format(formatter) + " Runs at time 5:00 PM every day");
    }

    @Scheduled(cron = "0 0 8 * * SAT")
    public void runSaturdayTask() {
        schedulerService.executeScheduler (LocalDateTime.now().format(formatter) + " Runs at 8:00 AM every Saturday");
    }

    @Scheduled(cron = "0/60 * 16-17 * * ?")
    public void runDailyTask() {
        schedulerService.executeScheduler (LocalDateTime.now().format(formatter) + " Runs every 60 seconds from 4:00 PM to 5:59 PM every day");
    }

    @Scheduled(cron = "59 59 23 L * *")
    public void runEndOfMonthTask() {
        schedulerService.executeScheduler (LocalDateTime.now().format(formatter) + " Runs at the end of the last day of every month");
    }

    @Scheduled(cron = "0 0 0 1 * *")
    public void runStartOfMonthTask() {
        schedulerService.executeScheduler (LocalDateTime.now().format(formatter) + " Runs at the start of first day of every month");
    }

    @Scheduled(cron = "0 0 0 1,16 * ?")
    public void runFirstAndSixteenthTask() {
        schedulerService.executeScheduler (LocalDateTime.now().format(formatter) + " Runs at the start on 1st and 16th of every month");
    }

}
