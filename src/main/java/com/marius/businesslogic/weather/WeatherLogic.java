package com.marius.businesslogic.weather;

import com.marius.dto.weather.WeatherDTO;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class WeatherLogic {

    public String createMessageOfCurrentWeatherSmsToSend(WeatherDTO dto) {
        String europeanDatePattern = "dd.MM.yyyy";
        DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
        String message = String.format(
            "Teplota v meste %s je v aktuálny deň %s %s celziových stupňov vysoká", dto.getCityName(),
                europeanDateFormatter.format(LocalDate.now()),
                dto.getTemperature()
        );
        return message;
    }
    public String createMessageOfForecastWeatherSmsToSend(List<WeatherDTO> weatherForecast, String cityName) {
        String message = String.format(
                """
                Predpoveď počasia v meste %s:
                %s %s
                %s %s
                %s %s
                %s %s
                %s %s
                %s %s
                %s %s""",
                cityName,
                translateDay(LocalDate.now().plusDays(1).getDayOfWeek()), weatherForecast.get(0).getTemperature() + "°C",
                translateDay(LocalDate.now().plusDays(2).getDayOfWeek()), weatherForecast.get(1).getTemperature() + "°C",
                translateDay(LocalDate.now().plusDays(3).getDayOfWeek()), weatherForecast.get(2).getTemperature() + "°C",
                translateDay(LocalDate.now().plusDays(4).getDayOfWeek()), weatherForecast.get(3).getTemperature() + "°C",
                translateDay(LocalDate.now().plusDays(5).getDayOfWeek()), weatherForecast.get(4).getTemperature() + "°C",
                translateDay(LocalDate.now().plusDays(6).getDayOfWeek()), weatherForecast.get(5).getTemperature() + "°C",
                translateDay(LocalDate.now().plusDays(7).getDayOfWeek()), weatherForecast.get(6).getTemperature() + "°C"
        );
        return message;
    }

    private String translateDay(DayOfWeek dayOfWeek) {

        return switch (dayOfWeek) {
            case MONDAY -> "Pondelok";
            case TUESDAY -> "Utorok";
            case WEDNESDAY -> "Streda";
            case THURSDAY -> "Štvrtok";
            case FRIDAY -> "Piatok";
            case SATURDAY -> "Sobota";
            case SUNDAY -> "Nedeľa";
            default -> "";
        };
    }
}
