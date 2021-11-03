package com.marius.service.message;

import com.marius.businesslogic.weather.WeatherLogic;
import com.marius.config.TwilioConfig;
import com.marius.dto.user.UserDTO;
import com.marius.dto.weather.WeatherDTO;
import com.marius.service.user.UserService;
import com.marius.service.weather.WeatherService;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service("twilio")
public class TwilioSmsSender implements SmsSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(TwilioSmsSender.class);

    private final TwilioConfig twilioConfig;
    private final WeatherService weatherService;
    private final UserService userService;
    private final WeatherLogic weatherLogic;

    @Autowired
    public TwilioSmsSender(TwilioConfig twilioConfig, WeatherService weatherService, WeatherLogic weatherLogic,
                           UserService userService) {
        this.twilioConfig = twilioConfig;
        this.weatherService = weatherService;
        this.weatherLogic = weatherLogic;
        this.userService = userService;
    }

    @Override
    public void sendSms() {
        PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());

        List<UserDTO> users = userService.getUsers();

        users.stream().filter(UserDTO::getSendMessage).forEach((user) -> {
            try {
                PhoneNumber to = new PhoneNumber(user.getPhoneNumber());
                WeatherDTO weatherDTO = weatherService.getWeatherByCity(user.getCityName());
                List<WeatherDTO> weatherForecastDTO = weatherService.getDailyForecast(weatherDTO.getLat(),
                        weatherDTO.getLon(),
                        weatherDTO.getCityName()
                );

                MessageCreator creator = Message.creator(to,
                        from,
                        weatherLogic.createMessageOfForecastWeatherSmsToSend(weatherForecastDTO,
                                user.getCityName()
                        )
                );

                creator.create();
            } catch (NoSuchElementException e) {
                LOGGER.error("user not found", e);
            }
        });
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        // TODO: Implement phone number validator
        return true;
    }
}