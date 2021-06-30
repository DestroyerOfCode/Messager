package com.marius.service.message;

import com.marius.dto.message.SmsRequestDTO;

public interface SmsSender {

    void sendSms(SmsRequestDTO smsRequest);
}
