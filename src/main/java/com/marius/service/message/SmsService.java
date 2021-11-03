package com.marius.service.message;

import org.springframework.stereotype.Service;

@Service
public class SmsService {

    private final SmsSender smsSender;

    public SmsService(TwilioSmsSender smsSender) {
        this.smsSender = smsSender;
    }

    public void sendSms() {
        smsSender.sendSms();
    }
}
