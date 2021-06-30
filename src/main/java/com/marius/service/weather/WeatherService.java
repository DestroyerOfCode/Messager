package com.marius.service.weather;

import com.marius.converter.weather.MapToWeatherDTOConverter;
import com.marius.dto.weather.WeatherDTO;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    private static final String BASEURL = System.getenv("WEATHER_APP_URL");
    private static final String RETRIEVEURL = "weather/current/retrieve/fromDb";
    private final RestTemplate restTemplate;
    private final MapToWeatherDTOConverter weatherDTOConverter;

    public WeatherService(RestTemplate restTemplate, MapToWeatherDTOConverter weatherDTOConverter) {
        this.restTemplate = restTemplate;
        this.weatherDTOConverter = weatherDTOConverter;
    }

    public List<WeatherDTO> getCurrentWeather() {
        Map<String, Object> requestBody = createRequestBody();
        ResponseEntity<Map> responseEntity = restTemplate.exchange(BASEURL + RETRIEVEURL,
                HttpMethod.POST,
                setOpenWeatherApiHeaders(requestBody),
                Map.class
        );
        List<Map<String, Object>> weatherDTOList =  (List) ((List) (responseEntity.getBody()).get("content"));
        return weatherDTOList.stream().map(weatherDTOConverter::mapToWeatherDTO).collect(Collectors.toList());

    }

    private HttpEntity<Map<String, Object>> setOpenWeatherApiHeaders(Map<String, Object> requestBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        return entity;
    }

    private Map<String, Object> createRequestBody() {
        Map<String, Object> requestBody = new HashMap<>(){{
            put("filters", new HashMap<String, Object>());
            put("isAscending", Boolean.TRUE);
            put("sortBy", "name");
            put("itemsPerPage", 100);
            put("pageNumber", 0);
        }};

        return requestBody;
    }
}
