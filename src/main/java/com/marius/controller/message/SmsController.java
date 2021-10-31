package com.marius.controller.message;

import com.marius.dto.message.SmsRequestDTO;
import com.marius.service.message.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/sms")
public class SmsController{

    private final SmsService smsService;

    @Autowired
    public SmsController(SmsService smsService) {
        this.smsService = smsService;
    }

    @PostMapping(path = "/send")
    @Scheduled(cron = "@daily")
    public void sendSms(@Valid @RequestBody SmsRequestDTO dto) {
        smsService.sendSms(dto);
    }
}
