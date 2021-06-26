package com.marius.service;

import com.marius.dto.SmsRequestDTO;

public interface SmsSender {

    void sendSms(SmsRequestDTO smsRequest);
}
