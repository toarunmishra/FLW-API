package com.iemr.flw.controller;

import com.iemr.flw.service.SmsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sms")
public class SmsController {

    private final SmsService smsService;

    public SmsController(SmsService smsService) {
        this.smsService = smsService;
    }

    @PostMapping("/send")
    public String sendSms(@RequestParam String phone, @RequestParam String appId) {
        return smsService.sendSms(phone, appId);
    }
}
