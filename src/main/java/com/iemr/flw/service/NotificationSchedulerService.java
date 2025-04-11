package com.iemr.flw.service;

import com.iemr.flw.service.impl.ChildCareServiceImpl;
import com.iemr.flw.service.impl.MaternalHealthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class NotificationSchedulerService {
    @Autowired
    private MaternalHealthServiceImpl maternalHealthService;

    @Autowired
   private ChildCareServiceImpl childCareService;

    @Scheduled(cron = "0 0 9 * * *") // every day at 9 AM
    public void triggerAncRemindersForAllAsha() {
        maternalHealthService.sendAncDueTomorrowNotifications("1205");
    }

    @Scheduled(cron = "0 0 9 * * *")
    public void trigerTomorrowImmunizationReminders() {
        childCareService.getTomorrowImmunizationReminders();
    }

}