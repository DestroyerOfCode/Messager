package com.marius.controller;

import com.marius.dto.WeatherDTO;
import com.marius.service.weather.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping(value = "/weather", produces = MediaType.APPLICATION_JSON_VALUE)
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping(value = "/current")
    public ResponseEntity<WeatherDTO> getCurrentWeather() {
        Optional<WeatherDTO> weatherDTOOptional = weatherService.getCurrentWeather();
        return new ResponseEntity<>(weatherDTOOptional.orElseThrow(RuntimeException::new), HttpStatus.OK);
    }
}
