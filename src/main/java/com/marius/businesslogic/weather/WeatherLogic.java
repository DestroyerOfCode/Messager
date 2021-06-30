package com.marius.businesslogic.weather;

import com.marius.dto.weather.WeatherDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class WeatherLogic {

    public String createMessageOfSmsToSend(WeatherDTO dto) {
        String europeanDatePattern = "dd.MM.yyyy";
        DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
        String message = String.format(
            "Teplota v meste %s je v aktuálny deň %s %s celziových stupňov vysoká", dto.getCityName(),
                europeanDateFormatter.format(LocalDate.now()),
                dto.getTemperature()
        );
        return message;
    }
}
