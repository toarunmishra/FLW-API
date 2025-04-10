package com.iemr.flw.controller;

import com.iemr.flw.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notify")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public String sendNotification(@RequestHeader String  auth) {
        return notificationService.sendNotification(auth);
    }
}
