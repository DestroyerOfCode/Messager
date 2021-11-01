package com.marius.controller.message;

import com.marius.dto.message.SmsRequestDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SendSmsIT {

    @Autowired
    private SmsController smsController;

    @Disabled
    @Test
    void sendSmsWithForecast() {
        smsController.sendSms(createSmsRequestDTO());
    }

    private SmsRequestDTO createSmsRequestDTO() {

        SmsRequestDTO smsRequestDTO = new SmsRequestDTO("+421950898744",  "Nitra");
        return smsRequestDTO;
    }
}
