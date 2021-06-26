package com.marius.service;

import com.marius.dto.SmsRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    private final SmsSender smsSender;

    public SmsService(TwilioSmsSender smsSender) {
        this.smsSender = smsSender;
    }

    public void sendSms(SmsRequestDTO dto) {
        smsSender.sendSms(dto);
    }
}
