package com.marius.controller;

import com.marius.dto.WeatherDTO;
import com.marius.service.weather.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/weather", produces = MediaType.APPLICATION_JSON_VALUE)
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping(value = "/current")
    public ResponseEntity<List<WeatherDTO>> getCurrentWeather() {
        List<WeatherDTO> weathersDtoList = weatherService.getCurrentWeather();
        if (weathersDtoList != null && !weathersDtoList.isEmpty())
            return new ResponseEntity<>(weathersDtoList, HttpStatus.OK);
        return new ResponseEntity<>(weathersDtoList, HttpStatus.NOT_FOUND);
    }
}
