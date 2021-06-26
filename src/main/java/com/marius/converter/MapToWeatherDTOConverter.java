package com.marius.converter;

import com.marius.dto.WeatherDTO;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MapToWeatherDTOConverter {

    public void mapToWeatherDTO(Map<String, Object> weatherMap, WeatherDTO dto) {
        dto.setCityName((String) weatherMap.get("name"));
        dto.setTemperature((Double) ((Map<String, Object>) weatherMap.get("weatherMain")).get("temp"));
    }
}
