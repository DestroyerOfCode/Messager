package com.marius.service.weather;

import com.marius.converter.weather.MapToWeatherDTOConverter;
import com.marius.dto.weather.WeatherDTO;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    private static final String BASE_URL = System.getenv("WEATHER_APP_URL");
    private static final String RETRIEVE_URL = "/weather/current/retrieve/fromDb";
    private static final String GET_CITY_URL = "/weather/current";
    private static final String FORECAST_DAILY_URL = "/weather/forecast/daily";
    private final RestTemplate restTemplate;
    private final MapToWeatherDTOConverter weatherDTOConverter;

    public WeatherService(RestTemplate restTemplate, MapToWeatherDTOConverter weatherDTOConverter) {
        this.restTemplate = restTemplate;
        this.weatherDTOConverter = weatherDTOConverter;
    }

    public List<WeatherDTO> getCurrentWeather() {
        Map<String, Object> requestBody = createRequestBody();
        ResponseEntity<Map> responseEntity = restTemplate.exchange(BASE_URL + RETRIEVE_URL,
                HttpMethod.POST,
                setOpenWeatherApiHeaders(requestBody),
                Map.class
        );
        List<Map<String, Object>> weatherDTOList =  (List) ((List) (responseEntity.getBody()).get("content"));
        return weatherDTOList.stream().map(weatherDTOConverter::mapToWeatherDTO).collect(Collectors.toList());

    }

    public WeatherDTO getWeatherByCity(String cityName) {
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(BASE_URL + GET_CITY_URL)
                .queryParam("cityName", "{cityName}")
                .encode()
                .toUriString();

        Map<String, String> params = new HashMap<>();
        params.put("cityName", cityName);

        ResponseEntity<Map> responseEntity = restTemplate.exchange(urlTemplate,
                HttpMethod.GET,
                setForecastDailyApiHeaders(),
                Map.class,
                params
        );
        Map<String, Object> weatherDTO =  (Map) (responseEntity.getBody());
        return weatherDTOConverter.mapToWeatherDTO(weatherDTO);
    }

    public List<WeatherDTO> getDailyForecast(BigDecimal lat, BigDecimal lon, String excludedForecasts) {
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(BASE_URL + FORECAST_DAILY_URL)
                .queryParam("lat", "{lat}")
                .queryParam("lon", "{lon}")
                .queryParam("exclude", "{exclude}")
                .encode()
                .toUriString();

        Map<String, String> params = new HashMap<>();
        params.put("lat", lat.toString());
        params.put("lon", lon.toString());
        params.put("exclude", excludedForecasts);

        ResponseEntity<Map> responseEntity = restTemplate.exchange(urlTemplate,
                HttpMethod.GET,
                setForecastDailyApiHeaders(),
                Map.class,
                params
        );
        List<Map<String, Object>> weatherDTOList =  (List) ((List) (responseEntity.getBody()).get("daily"));
        return weatherDTOList.stream().map(weatherDTOConverter::mapToDailyForecastDTO).collect(Collectors.toList());
    }

    private HttpEntity<Map<String, Object>> setOpenWeatherApiHeaders(Map<String, Object> requestBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return new HttpEntity<>(requestBody, headers);
    }

    private HttpEntity<Map<String, Object>> setForecastDailyApiHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return new HttpEntity<>(headers);

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
