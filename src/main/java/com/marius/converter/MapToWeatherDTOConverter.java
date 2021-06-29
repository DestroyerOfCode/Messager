package com.marius.converter;

import com.marius.dto.WeatherDTO;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MapToWeatherDTOConverter {

    private final KelvinToCelsiusConverter kelvinToCelsiusConverter;

    public MapToWeatherDTOConverter(KelvinToCelsiusConverter kelvinToCelsiusConverter) {
        this.kelvinToCelsiusConverter = kelvinToCelsiusConverter;
    }

    public void mapToWeatherDTO(Map<String, Object> weatherMap, WeatherDTO dto) {
        dto.setCityName((String) weatherMap.get("name"));
        Double kelvins = (Double) ((Map<String, Object>) weatherMap.get("weatherMain")).get("temp");
        dto.setTemperature(kelvinToCelsiusConverter.convertKelvinToCelsius(kelvins));
    }
    public WeatherDTO mapToWeatherDTO(Map<String, Object> weatherMap) {
        WeatherDTO dto = new WeatherDTO();
        dto.setCityName((String) weatherMap.get("name"));
        Double kelvins = (Double) ((Map<String, Object>) weatherMap.get("weatherMain")).get("temp");
        dto.setTemperature(kelvinToCelsiusConverter.convertKelvinToCelsius(kelvins));
        return dto;
    }
}
