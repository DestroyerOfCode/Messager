package com.marius.service.weather;

import com.marius.dto.WeatherDTO;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class WeatherService {

    private static final String URL = "https://tvoje-pocasie.herokuapp.com/retrieve/fromDb";
    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<WeatherDTO> getCurrentWeather() {
        Map<String, Object> requestBody = new HashMap<String, Object>(){{
            put("filters", new HashMap<String, String>(){{
                put("cityName", "Nitra");
            }});
            put("sortBy", Boolean.TRUE);
            put("itemsPerPage", 100);
            put("pageNumber", 1);
        }};
        ResponseEntity<WeatherDTO> responseEntity = restTemplate.exchange(URL,
                HttpMethod.POST,
                setOpenWeatherApiHeaders(requestBody),
                WeatherDTO.class
        );
        Optional<WeatherDTO> weatherDTOOptional = Optional.ofNullable(responseEntity.getBody());
        return weatherDTOOptional;
    }

    HttpEntity<Map<String, Object>> setOpenWeatherApiHeaders(Map<String, Object> requestBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String, Object>>(requestBody, headers);
        return entity;
    }
}
