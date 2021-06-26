package com.marius.businesslogic;

import com.marius.dto.WeatherDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

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
