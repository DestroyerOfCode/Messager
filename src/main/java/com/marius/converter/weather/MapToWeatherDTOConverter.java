package com.marius.converter.weather;

import com.marius.converter.common.KelvinToCelsiusConverter;
import com.marius.dto.weather.WeatherDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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
        dto.setLat( new BigDecimal(((Map<String, Object>) weatherMap.get("coord")).get("lat").toString()));
        dto.setLon( new BigDecimal(((Map<String, Object>) weatherMap.get("coord")).get("lon").toString()));
    }

    public WeatherDTO mapToWeatherDTO(Map<String, Object> weatherMap) {
        WeatherDTO dto = new WeatherDTO();
        dto.setCityName((String) weatherMap.get("name"));
        Double kelvins = (Double) ((Map<String, Object>) weatherMap.get("weatherMain")).get("temp");
        dto.setTemperature(kelvinToCelsiusConverter.convertKelvinToCelsius(kelvins));

        dto.setLat( new BigDecimal(((Map<String, Object>) weatherMap.get("coord")).get("lat").toString()));
        dto.setLon( new BigDecimal(((Map<String, Object>) weatherMap.get("coord")).get("lon").toString()));

        return dto;
    }

    public WeatherDTO mapToDailyForecastDTO(Map<String, Object> weatherMap) {
        WeatherDTO dto = new WeatherDTO();
        Double kelvins = (Double) ((Map<String, Double>) weatherMap.get("temp")).get("day");
        dto.setTemperature(kelvinToCelsiusConverter.convertKelvinToCelsius(kelvins));
        return dto;
    }
}
