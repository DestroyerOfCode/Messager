package com.marius.service.weather;

import com.marius.converter.MapToWeatherDTOConverter;
import com.marius.dto.WeatherDTO;
import com.marius.service.SmsService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class WeatherService {

    private static final String URL = "https://tvoje-pocasie-api.herokuapp.com/weather/current/retrieve/fromDb";
    private final RestTemplate restTemplate;
    private final MapToWeatherDTOConverter weatherDTOConverter;

    public WeatherService(RestTemplate restTemplate, MapToWeatherDTOConverter weatherDTOConverter) {
        this.restTemplate = restTemplate;
        this.weatherDTOConverter = weatherDTOConverter;
    }

    public Optional<WeatherDTO> getCurrentWeather() {
        Map<String, Object> requestBody = new HashMap<String, Object>(){{
            put("filters", new HashMap<String, Object>(){{
                put("name", new HashMap<String, Object>() {{
                    put("$eq", "Nitra");
                }});
//                put("country", new HashMap<String, Object>(){{
//                    put("in", List.of("SK"));
//                }});
            }});
            put("isAscending", Boolean.TRUE);
            put("sortBy", "name");
            put("itemsPerPage", 100);
            put("pageNumber", 0);
        }};
        ResponseEntity<Map> responseEntity = restTemplate.exchange(URL,
                HttpMethod.POST,
                setOpenWeatherApiHeaders(requestBody),
                Map.class
        );
        WeatherDTO weatherDTO = new WeatherDTO();
        weatherDTOConverter.mapToWeatherDTO((Map) ((ArrayList) responseEntity.getBody().get("content")).get(0), weatherDTO);

        return Optional.ofNullable(weatherDTO);
    }

    HttpEntity<Map<String, Object>> setOpenWeatherApiHeaders(Map<String, Object> requestBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String, Object>>(requestBody, headers);
        return entity;
    }
}
