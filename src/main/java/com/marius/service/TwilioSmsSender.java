package com.marius.service;

import com.marius.config.TwilioConfig;
import com.marius.dto.WeatherDTO;
import com.marius.service.weather.WeatherService;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import com.marius.dto.SmsRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("twilio")
public class TwilioSmsSender implements SmsSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(TwilioSmsSender.class);

    private final TwilioConfig twilioConfig;
    private final WeatherService weatherService;

    @Autowired
    public TwilioSmsSender(TwilioConfig twilioConfig, WeatherService weatherService) {
        this.twilioConfig = twilioConfig;
        this.weatherService = weatherService;
    }

    @Override
    public void sendSms(SmsRequestDTO smsRequest) {
        if (isPhoneNumberValid(smsRequest.getPhoneNumber())) {
            PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());
            Optional<WeatherDTO> weatherDTO = weatherService.getCurrentWeather();
            MessageCreator creator = Message.creator(to, from, weatherDTO.toString());
            creator.create();
            LOGGER.info("Send sms {}", smsRequest);
        } else {
            throw new IllegalArgumentException(
                    String.format( "Phone number [%s] is not a valid number", smsRequest.getPhoneNumber())
            );
        }
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        // TODO: Implement phone number validator
        return true;
    }
}